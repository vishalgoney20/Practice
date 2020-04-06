//import java.util.*;
public class WordPlay {

    public boolean isVowel(char ch)
    {
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
            return true;
        return false;
    }
    public String replaceWords(String phrase,char ch)
    {
        StringBuilder mutableString=new StringBuilder(phrase);
        for (int i=0;i<mutableString.length();i++)
        {
            if(isVowel(mutableString.charAt(i)))
            {
                mutableString.setCharAt(i, ch);
            }
        }
        return mutableString.toString();
    }
    public String emphasize(String phrase,char ch)
    {
        StringBuilder mutableString=new StringBuilder((phrase));
        for(int i=0;i<mutableString.length();i++)
        {
            if(mutableString.charAt(i)==ch && (i%2)==0)
            {
                mutableString.setCharAt(i,'+');
            }
            if(mutableString.charAt(i)==ch && (i%2)!=0)
            {
                mutableString.setCharAt(i,'*');
            }
        }
        return mutableString.toString();
    }
}