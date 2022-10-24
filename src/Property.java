public class Property extends BoardSpace {
    private Player owner;
    private String colorGroup; //"A", "B", "C", etc.
    private boolean isMonopoly;
    private int price;
    private int rent;
    public Property(String name, int price, int rent, String colorGroup) {
        super(name, "Property");
        this.price = price;
        this.rent = rent;
        this.colorGroup = colorGroup;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getColorGroup() {
        return colorGroup;
    }

    public void setColorGroup(String colorGroup) {
        this.colorGroup = colorGroup;
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
