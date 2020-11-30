package leetcode;

/**
 * @author huaqiliang
 * @date: 2020/11/2223:19
 * @Description:移动0
 * @Version:
 */

public class MoveZero {
    public static void main(String[] args) {
        int[] ints = {1, 4, 0, 0, 5, 0, 8,0, 0, 0};
        int j = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0) {
                ints[j] = ints[i];
                if (i != j) {
                    ints[i] = 0;
                }
                j ++;
            }
        }
        for (int i: ints) {
            System.out.print(i + "====");

        }
    }
}
