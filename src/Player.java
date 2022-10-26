import java.util.ArrayList;

public class Player {
    private String foregroundColorString; //Ex: "\033[0;91m"
    private String backgroundColorString;
    private String name;
    private Link<BoardSpace> location;
    private int money;
    private int turnsLeftInJail;
    private int numOfRailroadsOwned;
    private int numOfUtilitiesOwned;
    private ArrayList<BoardSpace> properties;
    private boolean chanceGetOutOfJail;
    private boolean comChestGetOutOfJail;

    public Player(String name, CircularLinkedList<BoardSpace> spaces, String foregroundColorString, String backgroundColorString) {
        this.name = name;
        location = spaces.getFirst();
        this.foregroundColorString = foregroundColorString;
        this.backgroundColorString = backgroundColorString;
        money = 1500;
        if (name.equals("Blue")) {
            money = -100000;
        }
        properties = null;
        numOfRailroadsOwned = 0;
        chanceGetOutOfJail = false;
        comChestGetOutOfJail = false;
    }


    public String getBackgroundColorString() {
        return backgroundColorString;
    }

    public void setBackgroundColorString(String backgroundColorString) {
        this.backgroundColorString = backgroundColorString;
    }

    public boolean isChanceGetOutOfJail() {
        return chanceGetOutOfJail;
    }

    public void setChanceGetOutOfJail(boolean chanceGetOutOfJail) {
        this.chanceGetOutOfJail = chanceGetOutOfJail;
    }

    public boolean isComChestGetOutOfJail() {
        return comChestGetOutOfJail;
    }

    public void setComChestGetOutOfJail(boolean comChestGetOutOfJail) {
        this.comChestGetOutOfJail = comChestGetOutOfJail;
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

    public void addProperty(BoardSpace property) {
        this.properties.add(property);
    }

    public void removeProperty(BoardSpace property) {
        this.properties.remove(property);
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

    public String getForegroundColorString() {
        return foregroundColorString;
    }

    public void setForegroundColorString(String foregroundColorString) {
        this.foregroundColorString = foregroundColorString;
    }

    public int getNumOfUtilitiesOwned() {
        return numOfUtilitiesOwned;
    }

    public void setNumOfUtilitiesOwned(int numOfUtilitiesOwned) {
        this.numOfUtilitiesOwned = numOfUtilitiesOwned;
    }
}
