import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collections;

public class CharactersInPlay {
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myCharactersFreqs;

    CharactersInPlay() {
        myCharactersFreqs = new ArrayList<Integer>();
        myCharacters = new ArrayList<String>();
    }

    public void update(String person) {
        int index = myCharacters.indexOf(person);
        if (index == -1) {
            myCharacters.add(person);
            myCharactersFreqs.add( 1);
        } else
            myCharactersFreqs.set(index, myCharactersFreqs.get(index) + 1);
    }

    public void findAllCharacters() {
        myCharacters.clear();
        myCharactersFreqs.clear();
        FileResource fr = new FileResource();
        for (String s : fr.lines()) {
            int dotIndex = s.indexOf(".");
            if (dotIndex != -1) {
                String subString = s.substring(0, dotIndex);
                update(subString);
            }
        }
    }

    void tester() {
        findAllCharacters();
        for (int i = 0; i < myCharacters.size(); i++) {
            System.out.println(myCharacters.get(i) + " : " + myCharactersFreqs.get(i));
        }
        int num2=Collections.max(myCharactersFreqs);
        int num1=2;
        for(int i=num2-1;i>=0;i--)
        {
            int index=myCharactersFreqs.indexOf(i);
            if(index!=-1)
            {
                num1=i;
                break;
            }
        }
        charactersWithNumParts(num1,num2);
    }

    void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters Frequency along with Character");

        System.out.println(num1 + " : " + myCharacters.get(myCharactersFreqs.indexOf(num1)));
        System.out.println(num2 + " : " + myCharacters.get(myCharactersFreqs.indexOf(num2)));

    }


    public static void main(String[] args) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester();
    }
}
