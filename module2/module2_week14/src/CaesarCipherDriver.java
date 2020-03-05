public class CaesarCipherDriver {
    public static void main(String[] args) {
        CaesarCipher cipher=new CaesarCipher();
        System.out.println("'FIRST LEGION ATTACK EAST FLANK!' with key 23 is converted as below \n "+cipher.encrypt("FIRST LEGION ATTACK EAST FLANK!",23)+"\n");
        System.out.println("'First Legion' with key 23 is converted as below \n"+cipher.encrypt("First Legion",23)+"\n");
        System.out.println("'First Legion' with key 17 is converted as below \n"+cipher.encrypt("First Legion",17)+"\n");

        cipher.testcaesar();
        System.out.println("encrypting 'First Legion' with two keys will be as follows \n"+cipher.encryptTwoKeys("First Legion",23,17));
    }
}
