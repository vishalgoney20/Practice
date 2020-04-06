import edu.duke.FileResource;

import java.util.Collections;
import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> map;

    CodonCount() {
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        map.clear();
        for (int i=start;i<dna.length()-3;i=i+3) {
            String s=dna.substring(i,i+3).trim();
            if (!map.containsKey(s)) {
                map.put(s, 1);

            } else {
                map.put(s, map.get(s) + 1);
            }
        }
    }

    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int max = 0;
        for (String s : map.keySet()) {
            if (map.get(s) > max) {
                max = map.get(s);
                mostCommonCodon = s;
            }
        }
        return mostCommonCodon;
    }
    public void printCodonCounts(int start,int end)
    {
        for(String s:map.keySet())
        {
            if(map.get(s)>=start&& map.get(s)<=end)
            {
                System.out.println(s+" : "+map.get(s));
            }
        }
    }
    public void tester()
    {
        FileResource fr=new FileResource();
        String dna=fr.asString().toUpperCase();
        System.out.println(dna);
        for(int i=0;i<3;i++)
        {
            buildCodonMap(i,dna);
            System.out.println("starting with Position " + i + " results in " + map.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("The most common codon is " + mostCommonCodon + " with " + "count " + map.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 are:");
            printCodonCounts(1,5);
        }

    }

    public static void main(String[] args) {
        CodonCount codonCount=new CodonCount();
        codonCount.tester();
    }
}
