package battleship;

import java.util.*;
import java.util.List;


public class BattleshipService {
    Scanner scanner = new Scanner(System.in);
    ValidationService validationService = new ValidationService();

    UserService userService = new UserService();


    public void printingMethod(List<List<BattleshipFields>> battleMap, boolean isPreGame) {

        int row = battleMap.size();
        int column = battleMap.get(0).size();


        System.out.print(" ");
        for (int i = 0; i < column; i++) {
            System.out.print(" ");
            System.out.print(i + 1);
        }
        System.out.println();

        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            System.out.print((char) ('A' + rowIndex) + " ");

            for (int columnIndex = 0; columnIndex < battleMap.get(rowIndex).size(); columnIndex++) {
                System.out.print(getValueOfFields(battleMap.get(rowIndex).get(columnIndex), isPreGame) + " ");
            }
            System.out.println();
        }
    }

    public void printingMethod(List<List<BattleshipFields>> battleMapCurrentPlayer, List<List<BattleshipFields>> battleMapEnemyPlayer, boolean isPreGame) {

        int row = battleMapCurrentPlayer.size();
        int column = battleMapCurrentPlayer.get(0).size();


        System.out.print(" ");
        for (int i = 0; i < column; i++) {
            System.out.print(" ");
            System.out.print(i + 1);
        }
        System.out.println();

        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            System.out.print((char) ('A' + rowIndex) + " ");

            for (int columnIndex = 0; columnIndex < battleMapCurrentPlayer.get(rowIndex).size(); columnIndex++) {
                Character enemyValue = getValueOfFields(battleMapEnemyPlayer.get(rowIndex).get(columnIndex), isPreGame);

                if (enemyValue != '~') {
                    System.out.print(enemyValue+ " ");
                } else {
                    System.out.print(getValueOfFields(battleMapCurrentPlayer.get(rowIndex).get(columnIndex), isPreGame) + " ");
                }
            }
                System.out.println();
            }
        }


    public void printingMethodMultiplayer(List<List<BattleshipFields>> battleMapPlayer1,
                                          List<List<BattleshipFields>> battleMapPlayer2,
                                          List<List<BattleshipFields>> emptyMap
    ) {

        printingMethod(emptyMap, false);
        System.out.println("---------------------");
        printingMethod(battleMapPlayer1, battleMapPlayer2, true);

    }

    public Character getValueOfFields(BattleshipFields field, boolean isPreGame) {

        if (!field.isVisible() && !isPreGame) {
            return '~';
        }
        if (field.isShip()) {
            if (field.isFieldDown()) {
                return 'X';
            }
            return 'O';
        }
        if (field.isShipMiss()) {
            return 'M';
        }


        return '~';
    }


    public void provideCoordinations(List<List<BattleshipFields>> battleMap, int row, int column, ShipType shipType) {

        String[] coordinates = scanner.nextLine().split(" ");

        char firstCoordinateRow = coordinates[0].charAt(0);
        int firstCoordinateColumn = Integer.parseInt(coordinates[0].substring(1));
        char secondCoordinateRow = coordinates[1].charAt(0);
        int secondCoordinateColumn = Integer.parseInt(coordinates[1].substring(1));

        validationService.validateProvidedCoordinates(firstCoordinateRow, firstCoordinateColumn, secondCoordinateRow,
                secondCoordinateColumn, row, column);
        validationService.validationOfProvidedShips(firstCoordinateRow, firstCoordinateColumn, secondCoordinateRow,
                secondCoordinateColumn, shipType);

        if (firstCoordinateRow > secondCoordinateRow) {
            char tempVariable = firstCoordinateRow;
            firstCoordinateRow = secondCoordinateRow;
            secondCoordinateRow = tempVariable;

        } else if (firstCoordinateColumn > secondCoordinateColumn) {
            int tempVariable = firstCoordinateColumn;
            firstCoordinateColumn = secondCoordinateColumn;
            secondCoordinateColumn = tempVariable;

        }

        List<Coordinates> listOfCoordinates = new ArrayList<>();
        boolean isHorizontal = firstCoordinateRow == secondCoordinateRow;

        if (isHorizontal) {
            for (int i = firstCoordinateColumn - 1; i < secondCoordinateColumn; i++) {
                listOfCoordinates.add(new Coordinates(firstCoordinateRow - 65, i));
            }
        } else if (firstCoordinateColumn == secondCoordinateColumn) {
            for (int j = firstCoordinateRow; j <= secondCoordinateRow; j++) {
                listOfCoordinates.add(new Coordinates(j - 65, secondCoordinateColumn - 1));
            }
        }

        validationService.validationOfShipPosition(listOfCoordinates, battleMap);
        listOfCoordinates.forEach(field -> {
                    battleMap.get(field.rowIndex).get(field.columnIndex).setShip(true);
                    userService.getUserShips().put(field.convertToAlphabetic(),
                            listOfCoordinates.stream().map(coordinate ->
                                    battleMap.get(coordinate.rowIndex).get(coordinate.columnIndex)).toList());
                }

        );

    }

    public void provideShipsCoordinates(List<List<BattleshipFields>> battleMap, int row, int column) {


        for (int i = 0; i < ShipType.values().length; i++) {
            ShipType shipType = ShipType.values()[i];
            System.out.println("Enter the coordinates of the " + shipType.shipName + " (" + shipType.size + " cells)");
            while (true) {
                try {
                    provideCoordinations(battleMap, row, column, shipType);
                    printingMethod(battleMap, true);
                    break;

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    public void checkIfSingleCoordination(List<List<BattleshipFields>> targetMap) {


        while (true) {
            try {
                String message = "";
                String singleCoordinate = scanner.nextLine();


                char providedRowIndex = singleCoordinate.charAt(0);
                int proviedColumnIndex = Integer.parseInt(singleCoordinate.substring(1));
                validationService.validationOfSingleCoordinate(targetMap, providedRowIndex, proviedColumnIndex);

                if (targetMap.get(providedRowIndex - 65).get(proviedColumnIndex - 1).isShip()) {
                    targetMap.get(providedRowIndex - 65).get(proviedColumnIndex - 1).setFieldDown(true);
                    targetMap.get(providedRowIndex - 65).get(proviedColumnIndex - 1).setVisible(true);


                    if (checkIfAllShipsAreSank(targetMap)) {

                        message = "You sank the last ship. You won. Congratulations!";
                        System.out.println(message);
                        System.exit(1);
                    }
                    if (userService.isShipSank(singleCoordinate)) {
                        message = "You sank a ship! Specify a new target:";

                    } else {


                        message = "You hit a ship!";
                    }

                } else {
                    targetMap.get(providedRowIndex - 65).get(proviedColumnIndex - 1).setShipMiss(true);
                    targetMap.get(providedRowIndex - 65).get(proviedColumnIndex - 1).setVisible(true);
                    message = "You missed!";

                }

                System.out.println("");
                System.out.println(message);
                break;

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean checkIfAllShipsAreSank(List<List<BattleshipFields>> battleMap) {

        return battleMap.stream().flatMap(singleList -> singleList.stream()).filter(singleField ->
                singleField.isShip()).allMatch(singleField -> singleField.isFieldDown());


    }

}



