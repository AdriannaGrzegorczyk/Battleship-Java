package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        BattleshipService battleshipService = new BattleshipService();

        List<List<BattleshipFields>> battleMapPlayer1 = new ArrayList<>();
        List<List<BattleshipFields>> battleMapPlayer2 = new ArrayList<>();
        List<List<BattleshipFields>> battleMapEmpty = new ArrayList<>();



        int column = 10;
        int row = 10;

        for (int i = 0; i < row; i++) {
            battleMapPlayer1.add(new ArrayList<>());
            for (int j = 0; j < column; j++) {
                battleMapPlayer1.get(i).add(new BattleshipFields());
                battleMapPlayer2.get(i).add(new BattleshipFields());
            battleMapPlayer2.add(new ArrayList<>());
            battleMapEmpty.add(new ArrayList<>());
                battleMapEmpty.get(i).add(new BattleshipFields());
            }
        }

        System.out.println("Player 1, place your ships on the game field:");
        System.out.println("");
        battleshipService.printingMethod(battleMapPlayer1,true);
        battleshipService.provideShipsCoordinates(battleMapPlayer1, row, column);
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        scanner.nextLine();

        System.out.println("Player 2, place your ships on the game field:");
        battleshipService.printingMethod(battleMapPlayer2,true);
        battleshipService.provideShipsCoordinates(battleMapPlayer2, row, column);

        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        scanner.nextLine();

        while (true) {
            battleshipService.printingMethodMultiplayer(battleMapPlayer1,battleMapPlayer2, battleMapEmpty);
            System.out.println("");
            System.out.println("Player 1, it's your turn:");
            battleshipService.checkIfSingleCoordination(battleMapPlayer2);

            System.out.println("Press Enter and pass the move to another player");
            System.out.println("...");

            scanner.nextLine();

            battleshipService.printingMethodMultiplayer(battleMapPlayer2,battleMapPlayer1, battleMapEmpty);
            System.out.println("");
            System.out.println("Player 2, it's your turn:");
            battleshipService.checkIfSingleCoordination(battleMapPlayer1);

            System.out.println("Press Enter and pass the move to another player");
            System.out.println("...");

            scanner.nextLine();
        }


    }
}
