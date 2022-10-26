/* Jaya */
public class BoardSpace {
    private String spaceName; // the name that shows up on the board, i.e. "H1"
    private String realName; // the actual name of the space/property, i.e. "Reading Railroad"
    private String type; // options: Property, GO, Chance, Community Chest, Utility, Railroad, To Jail, Tax, Jail, Free Parking
    public BoardSpace(String spaceName, String realName, String type) {
        this.spaceName = spaceName;
        this.realName = realName;
        this.type = type;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String name) {
        this.spaceName = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName() {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(BoardSpace other) { // compares the realName of the BoardSpaces
        return other.realName.equals(this.realName);
    }
}
