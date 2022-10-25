import java.util.ArrayList;

public class Player {
    private String colorString; //Ex: "\033[0;91m"
    private String name;
    private Link<BoardSpace> location;
    private int money;
    private int turnsLeftInJail;
    private ArrayList<BoardSpace> properties;

    public Player(String name, CircularLinkedList<BoardSpace> spaces) {
        this.name = name;
        BoardSpace go = new BoardSpace("GO", "GO");
        location = spaces.getFirst();
        money = 1500;
        properties = null;
    }

    public String getColorString() {
        return colorString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link<BoardSpace> getLocation() {
        return location;
    }

    public void setLocation(Link<BoardSpace> location) {
        this.location = location;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTurnsLeftInJail() {
        return turnsLeftInJail;
    }

    public void setTurnsLeftInJail(int turnsLeftInJail) {
        this.turnsLeftInJail = turnsLeftInJail;
    }

    public ArrayList<BoardSpace> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<BoardSpace> properties) {
        this.properties = properties;
    }
}
