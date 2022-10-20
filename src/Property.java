public class Property extends BoardSpace {
    private Player owner;
    private String colorGroup; //"A", "B", "C", etc.
    private boolean isMonopoly;
    private String name;
    private int price;
    private int rent;
    public Property(String name, int price, int rent, String colorGroup) {
        super("Property");
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.colorGroup = colorGroup;
    }
}
