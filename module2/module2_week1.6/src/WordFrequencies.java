import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    WordFrequencies() {
        myWords=new ArrayList<String>();
        myFreqs= new ArrayList<Integer>();
    }

    public void findUnique() {
        myFreqs.clear();
        myWords.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                myFreqs.set(index, myFreqs.get(index) + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        System.out.println("Number Of unique words :" + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) +" : "+myFreqs.get(i));
        }
        int max = findIndexOfMax();
        int index = myFreqs.indexOf(max);
        System.out.println("Thw word that occurs most and its count is  " + myWords.get(index) + ":" + max);
    }

    public int findIndexOfMax() {
        int maxValue = 0;
        for (int i : myFreqs) {
            if (i > maxValue)
                maxValue = i;
        }
        return maxValue;
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.tester();
    }
}
