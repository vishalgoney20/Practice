import edu.duke.*;


public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int letterCount = 0;
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 && !Character.isLetter(s.charAt(i)))
                    continue;
                if (i == (s.length() - 1) && !Character.isLetter(s.charAt(i)))
                    continue;
                letterCount += 1;
            }
            counts[letterCount] += 1;
        }
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 1; i < counts.length; i++) {
            System.out.println("number of words of length  " + i + " are " + counts[i]);
        }
        System.out.println("frequent word length is " + indexOfMax(counts));
    }

    public int indexOfMax(int[] values) {
        int FrequentWordLength = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > FrequentWordLength)
                FrequentWordLength = values[i];
        }
        return FrequentWordLength;
    }

    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}
