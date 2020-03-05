import edu.duke.FileResource;

import java.util.ArrayList;

public class CaesarBreaker {

    public int[] countLetters(String s) {
        int[] letterFrequencyArray = new int[26];
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                int support = Math.abs('a' - s.charAt(i));
                letterFrequencyArray[support] += 1;
            }
        }
        return letterFrequencyArray;
    }

    public int maxIndex(int[] frequentArray) {
        int maxIndex = 0;
        for (int i = 0; i < frequentArray.length; i++) {
            if (frequentArray[i] > maxIndex)
                maxIndex = i;
        }
        return maxIndex;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        return cipher.encrypt(encrypted, 26 - 23);
    }

    public void testDecrypt() {
        CaesarCipher caesarCipher = new CaesarCipher();
        String encrypted = caesarCipher.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        System.out.println("\n\n'FIRST LEGION ATTACK EAST FLANK!' is Encrypted as " + encrypted + " and it is decrypted as below");
        System.out.println("Decrypted String is" + decrypt(encrypted)+"\n\n");
    }

    public String halfOfString(String message, int start) {
        String newString = "";
        //StringBuilder mutableString=new StringBuilder(newString);
        int support = 2;
        for (int i = start; i < message.length(); i++) {
            if (support % 2 == 0) {
                newString = newString + message.charAt(i);
                support += 1;
            } else {
                support = 2;
            }
        }
        return newString;
    }

    public int getKey(String s) {
        int[] frequentArray = new int[26];
        frequentArray = countLetters(s);
        int max = maxIndex(frequentArray);
        //System.out.println(max);
        int dkey = max - 4;
        if (max < 4) {
            dkey = 26 - (4 - max);
        }
        return 26 - dkey;
    }


    public String decryptTwoKeys(String encrypted) {
        String firstHalfEncrypted = halfOfString(encrypted, 0);
        String secondHalfEncrypted = halfOfString(encrypted, 1);
        System.out.println(firstHalfEncrypted);
        System.out.println(secondHalfEncrypted);
        int firstHalfKey = getKey(firstHalfEncrypted);
        int secondHalfKey = getKey(secondHalfEncrypted);
        CaesarCipher cc = new CaesarCipher();

        System.out.println("First key:\t" + firstHalfKey + "\nSecond key:\t"
                + secondHalfKey);

        return cc.encryptTwoKeys(encrypted, firstHalfKey, secondHalfKey);
    }

    public void testDecryptTwoKeys() {
        // encryptTwoKeys(“First Legion”, 23, 17) should return “Czojq Ivdzle”
        String encrypted = "Czojq Ivdzle";
        System.out.println("Encrypted message:  " + encrypted);
        System.out.println("Decrypted message:  " + decryptTwoKeys(encrypted)+"\n");

        String encrypted2 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("Encrypted message:  " + encrypted2);
        System.out.println("Decrypted message:  " + decryptTwoKeys(encrypted2)+"\n");
    }

    public String decryptTwoKeysMethod2() {
        CaesarCipher caesarCipher = new CaesarCipher();
        String encrypted = caesarCipher.encryptTwoKeys("First Legion", 23, 17);
        ArrayList<String> halfString1 = new ArrayList<String>();
        ArrayList<String> halfString2 = new ArrayList<String>();
        String decrypted = "";
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                char ch = encrypted.charAt(i);
                halfString1.add(caesarCipher.encrypt(Character.toString(ch), 26 - 23));
            } else {
                char ch = encrypted.charAt(i);
                halfString2.add(caesarCipher.encrypt(Character.toString(ch), 26 - 17));
            }
        }
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                decrypted += halfString1.get(index1);
                index1 += 1;
            } else {
                decrypted += halfString2.get(index2);
                index2 += 1;
            }
        }
        return decrypted;
    }

    public static void main(String[] args) {
        CaesarBreaker caesarBreaker = new CaesarBreaker();
        caesarBreaker.testDecrypt();
        caesarBreaker.testDecryptTwoKeys();
        caesarBreaker.decryptTwoKeysMethod2();
    }
}