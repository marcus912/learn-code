package learn.code.marcus.binary;

public class SearchInsertPosition {

  static public void main(String[] args) {
    System.out.println(new SearchInsertPosition()
      .searchInsert(new int[]{1, 5, 8, 11}, 13));
  }

  public int searchInsert(int[] nums, int target) {
    int left, right, middle;
    left = 0;
    right = nums.length;
    //         9
    // 1, 5, 8, 11
    //   3
    while (right > left) {
      middle = left + (right - left) / 2;
      if (nums[middle] == target) {
        return middle;
      } else if (nums[middle] > target) {
        right = middle;
      } else {
        // nums[middle] < target
        left = middle + 1;
      }
    }

    return left;
  }
}
