package arrays_and_hashmap;

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequentElements {

    public static void main(String[] args) {
        topKFrequent(new int[]{1,2,2,3,3,3}, 2);
    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0 ; i < nums.length; i++) {
            Integer n = nums[i];
            Integer c = counts.get(nums[i]);
            if (c != null) {
                counts.put(n, c + 1);
            } else {
                counts.put(n, 1);
            }
        }


        LinkedHashMap<Integer, Integer> newCnt = counts.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new
        ));

        Set<Integer> res = new HashSet<>();
        int i = 0;
        for (Map.Entry<Integer, Integer> entry: newCnt.entrySet()) {
            if (i < k) {
                res.add(entry.getKey());
                i++;
            }
        }

        return res.stream()
                .mapToInt(Integer::intValue)
                .toArray();

    }

}
