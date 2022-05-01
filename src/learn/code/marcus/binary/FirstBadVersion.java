package learn.code.marcus.binary;

public class FirstBadVersion {
  int firstBad;

  public FirstBadVersion(int firstBad) {
    this.firstBad = firstBad;
  }

  static public void main(String args[]) {
    FirstBadVersion fbv = new FirstBadVersion(2);
    int firstBadVersion = fbv.firstBadVersion(9);
    System.out.println(firstBadVersion);
  }

  public boolean isBadVersion(int i) {
    return i >= firstBad;
  }

  public int firstBadVersion(int n) {
    int middle, left = 1, right = n;
    //     3
    // 1 2 3 4 5 6 7 8 9
    //             7

    while (right > left) {
      // 1 + (9 - 1) / 2 = 5;
      middle = left + (right - left) / 2;
      boolean isBad = isBadVersion(middle);
      if (isBad) {
//        if (!isBadVersion(middle -1)) {
//          return middle;
//        }
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    return left;
  }
}
