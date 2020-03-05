import java.util.HashMap;

import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key) {
        StringBuilder mutableString = new StringBuilder(input);
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";

        String upperShifted = upperAlphabet.substring(key) + upperAlphabet.substring(0, key);
        String lowerShifted = lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);

        for (int i = 0; i < mutableString.length(); i++) {
            char currChar = mutableString.charAt(i);
            if (currChar >= 'A' && currChar <= 'Z') {
                int index = upperAlphabet.indexOf(currChar);
                if (index != -1) {
                    char charToBeReplaced = upperShifted.charAt(index);
                    mutableString.setCharAt(i, charToBeReplaced);
                }
            }
            if (currChar >= 'a' && currChar <= 'z') {
                int index = lowerAlphabet.indexOf(currChar);
                if (index != -1) {
                    char charToBeReplaced = lowerShifted.charAt(index);
                    mutableString.setCharAt(i, charToBeReplaced);
                }
            }
        }
        return mutableString.toString();
    }

    public void testcaesar() {
        FileResource fr = new FileResource();
        String fileText = fr.asString();
        System.out.println("\n\nFile text is encrypted as " + encrypt(fileText, 23));
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder mutableString = new StringBuilder(input);
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String key1upperShifted = upperAlphabet.substring(key1) + upperAlphabet.substring(0, key1);
        String key2upperShifted = upperAlphabet.substring(key2) + upperAlphabet.substring(0, key2);
        String key1lowerShifted = lowerAlphabet.substring(key1) + lowerAlphabet.substring(0, key1);
        String key2lowerShifted = lowerAlphabet.substring(key2) + lowerAlphabet.substring(0, key2);
        for (int i = 0; i < mutableString.length(); i++) {
            if ((i % 2) == 0) {
                char currChar = mutableString.charAt(i);
                if (currChar >= 'A' && currChar <= 'Z') {
                    int index = upperAlphabet.indexOf(currChar);
                    if (index != -1) {
                        char charToBeReplaced = key1upperShifted.charAt(index);
                        mutableString.setCharAt(i, charToBeReplaced);
                    }
                }
                if (currChar >= 'a' && currChar <= 'z') {
                    int index = lowerAlphabet.indexOf(currChar);
                    if (index != -1) {
                        char charToBeReplaced = key1lowerShifted.charAt(index);
                        mutableString.setCharAt(i, charToBeReplaced);
                    }
                }

            }
            if ((i % 2) != 0) {
                char currChar = mutableString.charAt(i);
                if (currChar >= 'A' && currChar <= 'Z') {
                    int index = upperAlphabet.indexOf(currChar);
                    if (index != -1) {
                        char charToBeReplaced = key2upperShifted.charAt(index);
                        mutableString.setCharAt(i, charToBeReplaced);
                    }
                }
                if (currChar >= 'a' && currChar <= 'z') {
                    int index = lowerAlphabet.indexOf(currChar);
                    if (index != -1) {
                        char charToBeReplaced = key2lowerShifted.charAt(index);
                        mutableString.setCharAt(i, charToBeReplaced);
                    }
                }
            }
        }

        return mutableString.toString();
    }
}
