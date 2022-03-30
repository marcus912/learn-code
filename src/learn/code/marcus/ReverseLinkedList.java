package learn.code.marcus;

public class ReverseLinkedList {
  static public void main(String[] arg) {
    Solution solution = new Solution();
//    ListNode node = solution.addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))),
//      new ListNode(5, new ListNode(6, new ListNode(4))));
    ListNode node = solution.addTwoNumbers(new ListNode(9),
      new ListNode(1,
        new ListNode(9,
          new ListNode(9,
            new ListNode(9,
              new ListNode(9,
                new ListNode(9,
                  new ListNode(9,
                    new ListNode(9,
                      new ListNode(9,
                        new ListNode(9)))))))))));
    System.out.println(node);
  }

  static class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      // 107 + 221
      // 328
      int total = sumNodeValue(l1) + sumNodeValue(l2);
      ListNode node = new ListNode();
      recursiveNode(total, node);
      return node;
    }

    private void recursiveNode(int total, ListNode node) {
      // 807
      // 7 = 807 % 10
      node.val = total % 10;
      total = total / 10;
      if (total > 0) {
        node.next = new ListNode();
        recursiveNode(total, node.next);
      }
    }

    private int sumNodeValue(ListNode l) {
      ListNode tempNode = l;
      int sum = 0;
      int count = 0;
      do {
        sum += tempNode.val * Math.pow(10, count);
        tempNode = tempNode.next;
        count++;
      } while (tempNode != null);
      return sum;
    }

  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}

