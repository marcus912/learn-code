package learn.code.marcus;

public class ReverseInteger {

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.reverse(1534236469));
    System.out.println(solution.reverse(123));
    System.out.println(solution.reverse(-123));
    System.out.println(solution.reverse(120));
  }


}

class Solution {
  public int reverse(int x) {
    int minus = 1;
    int finalNumber = 0;
    if (x < 0) {
      minus = -1;
      x = x * -1;
    }
    while (x > 0) {

      if (finalNumber > Integer.MAX_VALUE / 10 || finalNumber * minus < Integer.MIN_VALUE / 10) {
        return 0;
      }

      // 123, 12, 1
      int digit = x % 10; // 3, 2, 1
      finalNumber = finalNumber * 10 + digit;
      x = x / 10;
    }

    return finalNumber * minus;
  }
}