import java.util.ArrayList;

public class Player {
    private String colorString; //Ex: "\033[0;91m"
    private String backgroundColorString;
    private String name;
    private Link<BoardSpace> location;
    private int money;
    private int turnsLeftInJail;
    private int numOfRailroadsOwned;
    private ArrayList<BoardSpace> properties;
    private int numGetOutOfJailFree;

    public Player(String name, CircularLinkedList<BoardSpace> spaces, String colorString, String backgroundColorString) {
        this.name = name;
        location = spaces.getFirst();
        this.colorString = colorString;
        this.backgroundColorString = backgroundColorString;
        money = 1500;
        properties = null;
        numOfRailroadsOwned = 0;
        numGetOutOfJailFree = 0;
    }

    public int getNumGetOutOfJailFree() {
        return numGetOutOfJailFree;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }

    public String getBackgroundColorString() {
        return backgroundColorString;
    }

    public void setBackgroundColorString(String backgroundColorString) {
        this.backgroundColorString = backgroundColorString;
    }

    public void setNumGetOutOfJailFree(int numGetOutOfJailFree) {
        this.numGetOutOfJailFree = numGetOutOfJailFree;
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

    public int getNumOfRailroadsOwned() {
        return numOfRailroadsOwned;
    }

    public void setNumOfRailroadsOwned(int numOfRailroadsOwned) {
        this.numOfRailroadsOwned = numOfRailroadsOwned;
    }

    public boolean equals(Player other) {
        return other.name.equalsIgnoreCase(this.name);
    }
}
