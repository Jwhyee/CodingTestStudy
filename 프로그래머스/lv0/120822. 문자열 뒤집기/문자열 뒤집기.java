class Solution {
    public String solution(String my_string) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = my_string.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            sb.append(charArray[i]);
        }
        return sb.toString();
    }
}