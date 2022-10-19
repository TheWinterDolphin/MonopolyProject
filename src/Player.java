import java.util.ArrayList;

public class Player {
    private String name;
    private Link<BoardSpace> location;
    private int money;
    private int turnsLeftInJail;
    private ArrayList<BoardSpace> properties;

    public Player(String name) {
        this.name = name;
        BoardSpace go = new BoardSpace("GO");
        location = new Link<BoardSpace>(go);
        money = 1500;
        properties = null;
    }
}
