package battleship;

public enum ShipType {
    AIRCRAFT_CARRIER("Aircraft Carrier",5),
    BATTLESHIP("Battleship",4),
    SUBMARINE("Submarine",3),
    CRUISER("Cruiser",3),
    DESTROYER("Destroyer",2);

    public final String shipName;
    public final int size;

    ShipType(String shipName, int size) {
        this.shipName=shipName;
        this.size = size;
    }


}
