//Question 3
//        b)
//        you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
//        Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within
//        string.
//        Input: String a=“tt”, pattern =”@”
//        Output: true
//        Input: String a=“ta”, pattern =”t”
//        Output: false
//        Input: String

public class Question3B {
    public static boolean matches(String str, String pattern) {
        // If both string and pattern are empty, they match
        if (str.isEmpty() && pattern.isEmpty()) {
            return true;
        }

        // If either string or pattern is empty, they don't match
        if (str.isEmpty() || pattern.isEmpty()) {
            return false;
        }

        // If pattern contains only @, it matches the entire string
        if (pattern.equals("@")) {
            return true;
        }

        // If the first character of pattern is not @, and the first character of str does not match the pattern,
        // then the strings don't match
        if (pattern.charAt(0) != '@' && str.charAt(0) != pattern.charAt(0)) {
            return false;
        }

        // If the first character of pattern is #, or it matches the first character of str,
        // then we try to match the rest of the string and pattern
        if (pattern.charAt(0) == '#' || pattern.charAt(0) == str.charAt(0)) {
            return matches(str.substring(1), pattern.substring(1));
        }

        // If the first character of pattern is @, we try to match the rest of the pattern against
        // every possible substring of str, and return true if any of them match
        if (pattern.charAt(0) == '@') {
            for (int i = 1; i <= str.length(); i++) {
                if (matches(str.substring(i), pattern.substring(1))) {
                    return true;
                }
            }
        }
        // If none of the above conditions are met, the strings don't match
        return false;
    }

    public static void main(String[] args) {
        String a1 = "tt";
        String p1 = "@";
        System.out.println(matches(a1, p1)); // true

        String a2 = "ta";
        String p2 = "t";
        System.out.println(matches(a2, p2)); // false

        String a3 = "ta";
        String p3 = "t#";
        System.out.println(matches(a3, p3)); // true
    }
}