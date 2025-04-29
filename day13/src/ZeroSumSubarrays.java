import java.util.HashMap;
import java.util.ArrayList;

class ZeroSumSubarrays {
    public static ArrayList<ArrayList<Integer>> findSubarrays(int[] arr) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j <= i; j++) temp.add(j);
                result.add(temp);
            }
            ArrayList<Integer> indices = map.get(sum);
            if (indices != null) {
                for (int index : indices) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = index + 1; j <= i; j++) temp.add(j);
                    result.add(temp);
                }
                indices.add(i);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(sum, temp);
            }
        }
        return result;
    }
}