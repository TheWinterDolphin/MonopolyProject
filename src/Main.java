public class Main {

    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.setup();
        while(!myGame.getIsGameOver()) {
            myGame.next();
        }
    }
}
