public class Utility extends BoardSpace {
    private Player owner;
    private boolean bothOwned; // rent is dice roll * 4 if 1 owned, * 10 if both owned
    private int price;
    private String spaceName;
    private String realName;

    public Utility(String spaceName, String realName, int price) {
        super(spaceName, realName,"Utility");
        this.price = price;
        this.spaceName = spaceName;
        this.realName = realName;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isBothOwned() {
        return bothOwned;
    }

    public void setBothOwned(boolean bothOwned) {
        this.bothOwned = bothOwned;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
