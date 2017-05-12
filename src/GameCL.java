import java.util.InputMismatchException;
import java.util.Scanner;

public class GameCL {
    private String player1;
    private String player2;
    private ClassForGame game;
    private int size;
    boolean player;
    Scanner in = new Scanner(System.in);


    public void play() {
        System.out.println("Первый игрок, введите своё имя:");
        name(1);
        System.out.println("Второй игрок, введите своё имя:");
        name(2);
        System.out.println("Введите размер поля:");
        getSize();
        field1();
        System.out.println("Игру крестики нолики начинает " +
                player1);
        game = new ClassForGame(size, player1, player2);
        move();

    }

    private void move() {
        if (!player) {
            System.out.println(player1 + ",сделайте свой ход ");
        } else {
            System.out.println(player2 + ",делайте свой ход");
        }
        try {
            Scanner in = new Scanner(System.in);
            String ff = in.nextLine();
            int turn = Integer.valueOf(ff);
            int x = turn / 10;
            int y = turn % 10;
            try {
                game.makeTurn(x, y);
                player = !player;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            }
            field2();
        } catch (NumberFormatException e) {
            System.out.println("Необходимо ввести число!");
        }
        searchWinner();
    }

    private void name(int player) {
        String name = in.nextLine();
        if (name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            name(player);
        } else {
            if (player == 1) player1 = name;
            else player2 = name;
        }
    }

    private void getSize() {
        try {
            int size = in.nextInt();
            if (size < 3) {
                System.out.println("размер поля не может быть меньше 3");
                getSize();
            } else this.size = size;
        } catch (NumberFormatException e) {
            System.out.println("Введите число еще раз!");
            getSize();
        }
    }

    private void searchWinner() {
        String winner;
        winner = game.getWinner();
        if (winner != null) {
            System.out.println("Победил " + winner);
        } else move();
    }

    public void field1() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(i + "" + j + " | ");
            }
            System.out.println("");
            for (int k = 0; k < size; k++) {
                System.out.print("----");
            }
            System.out.println("");
        }
    }

    public void field2() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (game.get(i, j) != null) {
                    System.out.print(game.get(i, j) + " | ");
                } else System.out.print(i + "" + j + " | ");
            }
            System.out.println("");
            for (int k = 0; k < size; k++) {
                System.out.print("----");
            }
            System.out.println("");
        }
    }
}
