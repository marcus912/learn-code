package learn.code.marcus.binary;

public class BinarySearch {

  public static void main(String[] args) {
    BinarySearch bs = new BinarySearch();
    int i = bs.search(new int[]{-1, 0, 3, 5, 9, 12}, 2);
    System.out.println(String.format("index %s", i));
  }

  public int search(int[] nums, int target) {
    if (nums.length == 0) {
      return -1;
    }
    return find(nums, target, 0, nums.length - 1);
  }

  int find(int[] nums, int target, int left, int right) {
    int midIndex = (left + right) / 2;
    int value = nums[midIndex];
    if (value == target) {
      return midIndex;
    } else if (value > target) {
      right = midIndex - 1;
    } else {
      left = midIndex + 1;
    }
    if (right < left) {
      return -1;
    }
    return find(nums, target, left, right);
  }

}
