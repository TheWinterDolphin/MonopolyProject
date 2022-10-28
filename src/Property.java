/* Jaya */
public class Property extends BoardSpace {
    private Player owner; //Which player owns the property

    private int price; //Cost to buy property
    private int rent; //Cost for landing on property and paying owner

    public Property(String spaceName, String realName, int price, int rent) {
        super(spaceName, realName,"Property");
        this.price = price;
        this.rent = rent;
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

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}
