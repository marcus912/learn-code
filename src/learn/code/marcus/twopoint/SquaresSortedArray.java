package learn.code.marcus.twopoint;

public class SquaresSortedArray {
  public static void main(String args[]) {
    System.out.println(new SquaresSortedArray()
      .sortedSquares(new int[]{-11, -4, 3, 7, 8, 12}));
  }

  public int[] sortedSquares(int[] nums) {
//    return Arrays.stream(nums).boxed().map(n-> n*n).sorted().mapToInt(Integer::intValue).toArray();
    int[] arr = new int[nums.length];
    int left = 0, right = nums.length - 1, index = nums.length - 1;
    // -11, -4, 3, 7, 8, 12
    while (right >= left) {
      if (Math.abs(nums[right]) > Math.abs(nums[left])) {
        arr[index] = nums[right] * nums[right];
        right--;
      } else {
        arr[index] = nums[left] * nums[left];
        left++;
      }
      index--;
    }

    return arr;
  }
}
