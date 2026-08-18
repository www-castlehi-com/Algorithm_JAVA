import java.util.*;

class Solution {

    private Map<Integer, Integer> subtracted = new HashMap<>();

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (subtracted.containsKey(numbers[i])) {
                return new int[]{subtracted.get(numbers[i]), i + 1};
            }

            subtracted.put(target - numbers[i], i + 1);
        }

        return new int[]{};
    }
}