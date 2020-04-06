import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordInFiles {
    private HashMap<String, ArrayList<String>> wordmap;

    WordInFiles() {
        wordmap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String s : fr.words()) {
            if (!wordmap.containsKey(s)) {
                ArrayList<String> filelist = new ArrayList<String>();
                filelist.add(f.getName());
                wordmap.put(s, filelist);
            } else {
                if (!wordmap.get(s).contains(f.getName()))
                    wordmap.get(s).add(f.getName());
            }
        }
    }

    public void buildWordFileMap() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int maxSize = 0;
        for (String s : wordmap.keySet()) {
            if (wordmap.get(s).size() > maxSize)
                maxSize = wordmap.get(s).size();
        }
        return maxSize;
    }

    public ArrayList<String> wordsInnumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        for (String s : wordmap.keySet()) {
            if (wordmap.get(s).size() == number)
                wordList.add(s);
        }
        return wordList;
    }

    public void printFilesIn(String word) {
        System.out.println("The word " + word + "appears in the below files ");
        for (String filename : wordmap.get(word)) {
            System.out.println(filename);
        }
    }

    public void tester() {
        buildWordFileMap();
        int maxValue=maxNumber();
        System.out.println("\nThe max no of files That has the most number of words are :" + maxValue);
        System.out.println("The words are as follows");
        for(String s:wordmap.keySet())
        {
            if(wordmap.get(s).size()==maxValue)
                System.out.println(s);
        }
        System.out.println("\n\nThe total map will be as follows i.e, word followed by the files\n");
        for (String s : wordmap.keySet()) {
            System.out.println("word : " + s);
            for (String filename : wordmap.get(s))
                System.out.println(filename);
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        WordInFiles wordInFiles=new WordInFiles();
        wordInFiles.tester();

    }
}
