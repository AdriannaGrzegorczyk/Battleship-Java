package battleship;

public class BattleshipFields {




    private boolean fieldDown;

    private boolean isEmptyField;

    private boolean isVisible;

    private boolean shipMiss;

    private boolean isShip;



//1. Czy jest tam statek gracza
//2. Czy jest tam statek kompa
//3. Czy statek Gracza jest trafiony
//4. Czy statek kompa jest trafiony



    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField(boolean emptyField) {
        isEmptyField = emptyField;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isShipMiss() {
        return shipMiss;
    }

    public void setShipMiss(boolean shipMiss) {
        this.shipMiss = shipMiss;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setShip(boolean ship) {
        isShip = ship;
    }

    public boolean isFieldDown() {
        return fieldDown;
    }

    public void setFieldDown(boolean fieldDown) {
        this.fieldDown = fieldDown;
    }
}
