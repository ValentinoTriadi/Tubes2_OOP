package oop.if2210_tb2_sc4.util;

public class StringUtil {
    public static String toTitleCase(String input) {
        // Split the input string by underscore
        String[] words = input.split("_");
        StringBuilder result = new StringBuilder();

        // Iterate over the words
        for (String word : words) {
            // Capitalize the first letter of each word and append to result
            result.append(Character.toUpperCase(word.charAt(0)))
                    //Lowercase the other
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }

        // Remove trailing space and return the result
        return result.toString().trim();
    }
    public static String toUpperSnakeCase(String input) {
        // Split the input string by space
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        // Iterate over the words
        for (int i = 0; i < words.length; i++) {
            // Convert each word to lowercase and append to result
            if (i > 0) {
                result.append("_");
            }
            result.append(words[i].toUpperCase());
        }

        // Return the result
        return result.toString();
    }
}
