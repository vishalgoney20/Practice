import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder mutableString=new StringBuilder("");
        for(int i=whichSlice;i<message.length();i=i+totalSlices)
        {
            mutableString.append(message.charAt(i));
        }
        return mutableString.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesarCracker=new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++)
        {
            String slicedString=sliceString(encrypted,i,klength);
            key[i]=caesarCracker.getKey(slicedString);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> wordSet=new HashSet<String>();
        for(String line:fr.lines())
        {
            line=line.toLowerCase();
            wordSet.add(line);
        }
        return wordSet;
    }
    public int countWords(String message,HashSet<String> set)
    {
        int realWordCount=0;
        for(String word:message.split("\\W"))
        {
            word=word.toLowerCase();
            if(set.contains(word))
                realWordCount+=1;
        }
        return realWordCount;
    }

    public String breakForLanguage(String encrypted,HashSet<String> set)
    {
        int largestRealWordCount=0;
        String finalDecrypted="";
        char ch=mostCommonCharIn(set);
        for(int i=1;i<=100;i++)
        {
            int key[]=tryKeyLength(encrypted,i,ch);
            VigenereCipher vigenereCipher=new VigenereCipher(key);
            String decrypted=vigenereCipher.decrypt(encrypted);
            int realWordCount=countWords(decrypted,set);
            if(realWordCount>largestRealWordCount)
            {
                largestRealWordCount=realWordCount;
                finalDecrypted=decrypted;
            }
        }
        return finalDecrypted;
    }
    public void countLetters(String word,HashMap<Character,Integer> map)
    {
        for(int i=0;i<word.length();i++)
        {
            if(!map.containsKey(word.charAt(i)))
                map.put(word.charAt(i),1);
            else
                map.put(word.charAt(i),map.get(word.charAt(i))+1);
        }
    }
    public char mostCommonCharIn(HashSet<String> set)
    {
        char freqChar='e';
        HashMap<Character,Integer> charCount=new HashMap<Character, Integer>();
        for(String s:set)
        {
            countLetters(s,charCount);
        }
        int maxValue=0;
        for(Character ch:charCount.keySet())
        {
            if(charCount.get(ch)>maxValue) {
                maxValue = charCount.get(ch);
                freqChar=ch;
            }
        }
        return freqChar;
    }
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages)
    {
        int max = 0;
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            if (i > max){
                max = i;
            }
        }
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            if (i == max){
                System.out.println(s + "\n" + language+" is the language used to decrypt the message ");
            }
        }
    }

    public void breakVigenere1 () {
        FileResource fr=new FileResource();
        int key[]=tryKeyLength(fr.asString(),5,'e');
        VigenereCipher vigenereCipher=new VigenereCipher(key);
        String decrypted=vigenereCipher.decrypt(fr.asString());
        System.out.println("\nThe decrypted String of known length 5 is\n");
        System.out.println(decrypted);

    }
    public void breakVigenere2()
    {
        System.out.println("\n\n\nThe decrypted String of unknown length  is.........\n");
        FileResource fr1=new FileResource();
        FileResource dir=new FileResource();
        HashSet<String> realWordsSet=readDictionary(dir);
        String decrypted=breakForLanguage(fr1.asString(),realWordsSet);
        System.out.println(decrypted);
    }
    public void breakVigenere3()
    {
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        String[] lang={"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        HashMap<String,HashSet<String>> languageMap=new HashMap<String,HashSet<String>>();
        for(int i=0;i<lang.length;i++)
        {
            FileResource filename=new FileResource("./dictionaries/"+lang[i]);
            HashSet<String> directory=readDictionary(filename);
            languageMap.put(lang[i],directory);
        }
        breakForAllLangs(encrypted,languageMap);
    }

    public static void main(String[] args) {
        VigenereBreaker vb=new VigenereBreaker();
        vb.breakVigenere3();
    }

}

