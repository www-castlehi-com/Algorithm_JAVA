package TMAX.RnD2023;

import java.util.Arrays;

public class Problem_3 {
    static final int MOD = 1_000_000_007;
    static int result = 0;

    public int solution(int[] arr) {
        int length = arr.length;
        int totalSum = Arrays.stream(arr).sum();

        checkEquation(arr, totalSum);
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] != arr[j]) {
                    int[] swapArr = swap(arr, i, j);
                    checkEquation(swapArr, totalSum);
                }
            }
        }

        return result;
    }

    private int[] swap(int[] arr, int idx1, int idx2) {
        int[] arrCopy = Arrays.copyOf(arr, arr.length);

        int temp = arrCopy[idx1];
        arrCopy[idx1] = arrCopy[idx2];
        arrCopy[idx2] = temp;
        return arrCopy;
    }

    private void checkEquation(int[] arr, int totalSum) {
        int length = arr.length;

        int leftSum = 0;
        for (int i = 0; i < length - 1; i++) {
            leftSum += arr[i];
            int rightSum = totalSum - leftSum;
            if (leftSum == rightSum)
                result = (result + 1) % MOD;
        }
    }
}
