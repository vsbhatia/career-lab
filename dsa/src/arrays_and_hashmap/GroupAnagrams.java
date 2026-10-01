import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams{
    public static void main(String[] args) {
        Solution s= new Solution();
        var list = s.groupAnagrams(new String[]{"act","pots","tops","cat","stop","hat"});
        System.out.println(list);
    }

    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> anagrams = new HashMap<>();
            Map<String, String> countsMap = new HashMap<>();

            for (String s: strs) {
                String sorted = sortStr(s);
                String counts = counts(sorted);


                if (!countsMap.containsKey(sorted)) {
                    List<String> set = new ArrayList<>();
                    set.add(s);
                    anagrams.put(sorted, set);
                    countsMap.put(sorted, counts);
                } else {
                    anagrams.get(sorted).add(s);
                }
            }

            return anagrams.values().stream().map(ArrayList::new).collect(Collectors.toList());
        }

        String sortStr(String s) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

        String counts(String s) {
            Map<Character, Integer> chars = new LinkedHashMap<>();
            for (char c: s.toCharArray()) {
                chars.merge(c, 1, Integer::sum);
            }
            return chars.values().stream().map(Object::toString).collect(Collectors.joining("_"));
        }
    }

}