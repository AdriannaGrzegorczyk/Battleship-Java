package battleship;

import java.util.List;


public class ValidationService {


    public boolean validateProvidedCoordinates(char firstCoordinateRow,
                                               int firstCoordinateColumn,
                                               char secondCoordinateRow,
                                               int secondCoordinateColumn,
                                               int row,
                                               int column) {
        boolean validCoordinatesRange = (firstCoordinateRow >= 'A' && firstCoordinateRow <= 'A' + row
                && secondCoordinateRow >= 'A' && secondCoordinateRow <= 'A' + row
                && firstCoordinateColumn >= 1 && firstCoordinateColumn <= column
                && secondCoordinateColumn >= 1 && secondCoordinateColumn <= column);
        boolean validCoordinatesValues = !((firstCoordinateColumn != secondCoordinateColumn) && firstCoordinateRow
                != secondCoordinateRow);
        return (validCoordinatesRange && validCoordinatesValues);


    }

    public void validationOfSingleCoordinate(List<List<BattleshipFields>> battleMap, char rowCoordinate, int columnCoordinate) {

        boolean validCoordinatesRange = (rowCoordinate >= 'A' && rowCoordinate <= 'A' + battleMap.size()
                && columnCoordinate >= 1 && columnCoordinate <= battleMap.get(0).size());
        if (!validCoordinatesRange) {
            throw new RuntimeException("Error! You entered the wrong coordinates! Try again:");
        }

    }

    public void validationOfProvidedShips(char firstCoordinateRow,
                                          int firstCoordinateColumn,
                                          char secondCoordinateRow,
                                          int secondCoordinateColumn,
                                          ShipType shipType) {

        if ((firstCoordinateRow != secondCoordinateRow) && (firstCoordinateColumn != secondCoordinateColumn)) {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        }

        int rowLength = (Math.abs(firstCoordinateRow - secondCoordinateRow) + 1);
        int columnLength = (Math.abs(firstCoordinateColumn - secondCoordinateColumn) + 1);

        if (!(shipType.size == columnLength && rowLength == 1) && !(shipType.size == rowLength && columnLength == 1)) {
            throw new RuntimeException("Error! Wrong length of the " + shipType.shipName + " ! Try again:");
        }

    }

    public void validationOfShipPosition(List<Coordinates> coordinates,
                                         List<List<BattleshipFields>> battleMap) {


        coordinates.forEach(field -> {
            int column = battleMap.get(0).size();
            int row = battleMap.size();
            RuntimeException exception = new RuntimeException("Error! You placed it too close to another one. Try again:");

            if (field.columnIndex + 1 < column && battleMap.get(field.rowIndex).get(field.columnIndex + 1).isShip()) {
                throw exception;
            }

            if (field.rowIndex + 1 < row && field.columnIndex + 1 < column && battleMap.get(field.rowIndex + 1)
                    .get(field.columnIndex + 1).isShip()) {
                throw exception;
            }

            if (field.rowIndex + 1 < row && battleMap.get(field.rowIndex + 1).get(field.columnIndex).isShip()) {
                throw exception;
            }

            if (field.rowIndex + 1 < row && field.columnIndex - 1 >= 0 && battleMap.get(field.rowIndex + 1)
                    .get(field.columnIndex - 1).isShip()) {
                throw exception;
            }

            if (field.columnIndex - 1 >= 0 && battleMap.get(field.rowIndex).get(field.columnIndex - 1).isShip()) {
                throw exception;
            }

            if (field.rowIndex - 1 >= 0 && field.columnIndex - 1 >= 0 && battleMap.get(field.rowIndex - 1)
                    .get(field.columnIndex - 1).isShip()) {
                throw exception;
            }

            if (field.rowIndex - 1 >= 0 && battleMap.get(field.rowIndex - 1).get(field.columnIndex).isShip()) {
                throw exception;
            }

            if (field.rowIndex - 1 >= 0 && field.columnIndex + 1 < column && battleMap.get(field.rowIndex - 1)
                    .get(field.columnIndex + 1).isShip()) {
                throw exception;
            }
        });

    }

}
