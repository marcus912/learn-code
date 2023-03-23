package convertor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertJsonToCSV {

  //  Map<String, String> keyValue = new HashMap<>();
  List<String> keys = new ArrayList<>();
  List<String> en_values = new ArrayList<>();

  static public void main(String[] args) {
    new ConvertJsonToCSV().convert();
  }

  public void convert() {
    JSONParser parser = new JSONParser();
    try {
      JSONObject json = (JSONObject) parser.parse(new FileReader("/Users/marcus/javaDev/workspaces/LearnCode/tester-maven-proj/src/main/java/convertor/coming-soon.json"));
//      System.out.println(json.toJSONString());

      JsonNode jsonNodeTree = new ObjectMapper().readTree(json.toJSONString());
      // save it as YAML
      YAMLMapper yamlMapper = new YAMLMapper();

      String jsonAsYaml = yamlMapper.writeValueAsString(jsonNodeTree);
//      System.out.println(jsonAsYaml);

      Iterator<JsonNode> iterator = jsonNodeTree.elements();
      iterator.forEachRemaining((node) -> {
//        System.out.println(node);
      });

      keys.add("language_code");
      en_values.add("en");
      findKeyValues(jsonNodeTree);
      System.out.println(keys.toString());
      System.out.println(en_values.toString());
//      Collections.reverse(keys);
//      Collections.reverse(en_values);
      writeCSVFile();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public void findKeyValues(JsonNode jsonNodeTree) {
    jsonNodeTree.fields().forEachRemaining((node) -> {
      if (node.getValue() instanceof TextNode) {
        keys.add(node.getKey());
        en_values.add(node.getValue().textValue());
      } else if (node.getValue() instanceof ArrayNode) {
        loopArray((ArrayNode) node.getValue(), node.getKey());
      }
      System.out.println(node);
    });

  }

  public void loopArray(ArrayNode arrayNode, String keyString) {
    for (int i = 0; i < arrayNode.size(); i++) {
      JsonNode node_arr = arrayNode.get(i);
      Iterator<Map.Entry<String, JsonNode>> it = node_arr.fields();
      while (it.hasNext()) {
        Map.Entry<String, JsonNode> node = it.next();
        if (node.getValue() instanceof TextNode) {
          keys.add(String.format("%s[%s].%s", keyString, i, node.getKey()));
          en_values.add(node.getValue().textValue());
        } else if (node.getValue() instanceof ArrayNode) {
          loopArray((ArrayNode) node.getValue(), String.format("%s[%s].%s", keyString, i, node.getKey()));
        }
      }
    }
  }

  private void writeCSVFile() {
    List<String[]> dataLines = new ArrayList<>();
    dataLines.add(keys.toArray(new String[]{}));
    dataLines.add(en_values.toArray(new String[]{}));
    File csvOutputFile = new File("/Users/marcus/javaDev/workspaces/LearnCode/tester-maven-proj/src/main/java/convertor/localization.csv");
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      dataLines.stream()
               .map(this::convertToCSV)
               .forEach(pw::println);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public String convertToCSV(String[] data) {
    return Stream.of(data)
                 .map(this::escapeSpecialCharacters)
                 .collect(Collectors.joining(","));
  }

  public String escapeSpecialCharacters(String data) {
    String escapedData = data.replaceAll("\\R", " ");
    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
      data = data.replace("\"", "\"\"");
      escapedData = "\"" + data + "\"";
    }
    return escapedData;
  }

}
