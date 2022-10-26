/* Jaya */
public class Property extends BoardSpace {
    private Player owner;
    private boolean isMonopoly;
    private int price;
    private int rent;

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

    public boolean isMonopoly() {
        return isMonopoly;
    }

    public void setMonopoly(boolean monopoly) {
        isMonopoly = monopoly;
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
