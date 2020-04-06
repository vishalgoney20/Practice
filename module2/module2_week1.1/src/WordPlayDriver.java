public class WordPlayDriver {
    public static void main(String[] args) {

        WordPlay wordPlay = new WordPlay();
        System.out.println("Hello World is replaced as " + wordPlay.replaceWords("Hello World", '*'));
        System.out.println("dnactgaaactga replaced at 'a' with '+' even and '*' at odd positions as  " + wordPlay.emphasize("dnactgaaactga", 'a'));
        System.out.println("Mary Bella Abracadabra replaced at 'a' with '+' even and '*' at odd positions as  " + wordPlay.emphasize("Mary Bella Abracadabra", 'a'));
    }
}
