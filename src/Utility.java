/* Jaya */
public class Utility extends BoardSpace {
    private Player owner; //Which player owns the utility
    private int price; //Cost to buy utility
    // rent is (dice roll * 4) if both owned, (dice roll * 10) if both owned (but this is handled in Game class under utilityLand() so no need to have a rent variable here)

    public Utility(String spaceName, String realName, int price) {
        super(spaceName, realName,"Utility");
        this.price = price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
