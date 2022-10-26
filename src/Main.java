/*Andrew*/
public class Main {

    public static void main(String[] args) {
        Game myGame = new Game(); //Create instance of Game class
        myGame.setup(); //Initialize variables and players in the game

        //Keep Looping Game turns until the Game is over
        while(!myGame.getIsGameOver()) {
            myGame.next();
        }
    }
}
