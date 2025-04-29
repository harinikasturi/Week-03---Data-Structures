import java.util.HashMap;

class PairSum {
    public static boolean hasPair(int[] arr, int target) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(target - num)) {
                return true;
            }
            map.put(num, true);
        }
        return false;
    }
}