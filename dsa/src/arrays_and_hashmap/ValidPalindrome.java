package arrays_and_hashmap;

import java.util.stream.Collectors;

public class ValidPalindrome {
    public static void main(String[] args) {

    }
    public static boolean isPalindrome(String s) {
        String str = s.chars().mapToObj(c -> c).filter(c -> c >= 48 && c <= 57 || (c >= 97 && c <= 122)).collect(Collectors.joining(""))
    }
}
