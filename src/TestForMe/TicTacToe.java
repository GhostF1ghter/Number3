package TestForMe;


import java.util.Scanner;



public class TicTacToe{


    int[][] field;
    String botGame;
    static int randomFirstPlayer;
    static int randomSecondPlayer;

    public static void main(String[] args) {


        TicTacToe tikTak = new TicTacToe();

//        PlayerVersusPlayer playerVersusPlayer = new PlayerVersusPlayer();
//        BotVersusPlayer botVersusPlayer = new BotVersusPlayer();



        tikTak.startGame(4);

        Scanner scanner = new Scanner(System.in);


        System.out.println("Играем с ботом? Да или нет.");
        String botGame = scanner.next();
        if (botGame.equalsIgnoreCase("Да")) {

            randomFirstPlayer = 0;
            randomSecondPlayer = 1;
            System.out.println("Игрок выбрасывает: " + randomFirstPlayer); // изменить на 0 1
            System.out.println("Бот выбрасывает: " + randomSecondPlayer);

            if (randomFirstPlayer > randomSecondPlayer) {
                BotVersusPlayer.playerIsGoingFirst(tikTak,scanner);
            } else {
                BotVersusPlayer.botIsGoingFirst(tikTak,scanner);
            }
//        } else {
//            PlayerVersusPlayer.twoPlayers(tikTak, scanner);
        }
    }




    public int limit() {
        return field.length * field.length;
    }


    public void startGame(int size) {
        this.field = new int[size][size];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = 0;
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void print() {
        for (int j = 0; j < field.length; j++) {
            for (int i = 0; i < field.length; i++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }












}

class BotVersusPlayer extends TicTacToe implements Steps, PlayerAndWinnerCheck  {

//    private static PlayerAndWinnerCheck botVersusPlayerCheck;
//    private static Steps botVersusPlayerStep;
        int[][] field;
     static Steps botVersusPlayerStep = new BotVersusPlayer();
     static PlayerAndWinnerCheck botVersusPlayerCheck = new BotVersusPlayer();
    public static void playerIsGoingFirst(TicTacToe tikTak, Scanner scanner) {



        boolean start = true;
        for (int i = 0; i < tikTak.limit(); i++) {

            botVersusPlayerCheck.playerCheck();
            System.out.println("Ходите вы. Введите столбец и строку");
            int column = scanner.nextInt();
            int row = scanner.nextInt();
            System.out.println("Вы выбрали столбец: " + (column + 1) + " и строку: " + (row + 1));
            botVersusPlayerStep.step(column, row, "tic");
            if (botVersusPlayerCheck.winnerCheck().equalsIgnoreCase("true")) {
                System.out.println("Игра окончена");
                start = false;
                break; // изменить на while
            }
            System.out.println("Ходит бот");
            botVersusPlayerStep.botStep();
            if (botVersusPlayerCheck.winnerCheck().equalsIgnoreCase("true")) {
                System.out.println("Игра окончена");
                start = false;
                break;
            }

        }
        scanner.close();
    }


    public static void botIsGoingFirst(TicTacToe tikTak, Scanner scanner) {
        boolean start = true;

        for (int i = 0; i < tikTak.limit(); i++) {
            botVersusPlayerCheck.playerCheck();
            System.out.println("Ходит бот");
            botVersusPlayerStep.botStep();
            if (botVersusPlayerCheck.winnerCheck().equalsIgnoreCase("true")) {
                System.out.println("Игра окончена");
                start = false;
                break;
            }
            System.out.println("Ходите вы. Введите столбец и строку");
            int column = scanner.nextInt();
            int row = scanner.nextInt();
            System.out.println("Вы выбрали столбец: " + (column + 1) + " и строку: " + (row + 1));
            botVersusPlayerStep.step(column, row, "tac");
            if (botVersusPlayerCheck.winnerCheck().equalsIgnoreCase("true")) {
                System.out.println("Игра окончена");
                start = false;
                break;
            }

        }
        scanner.close();
    }


    public String playerCheck () {
        int counterFirstPlayer = 0;
        int counterSecondPlayer = 0;


        for (int i = 0; i < super.field.length; i++) {
            for (int j = 0; j < super.field[i].length; j++) {
                if (super.field[i][j] == 1) {
                    counterFirstPlayer++;
                }
                if (super.field[i][j] == 2) {
                    counterSecondPlayer++;
                }
            }
        }

        if (counterFirstPlayer < counterSecondPlayer || counterFirstPlayer == counterSecondPlayer) {
            return "tic";
        } else {
            return "tac";
        }
    }

    public boolean step(int column, int row, String player){


        if (player.equalsIgnoreCase("tic")) {
            super.field[column][row] = 1;
        }
        if (player.equalsIgnoreCase("tac")) {
            super.field[column][row] = 2;
        }
        print();
        return true;
    }

    public String winnerCheck () {
        boolean isWinner = false;

        for (int i = 0; i < field.length; i++) {
            int columnCounter = 0;
            int rowCounter = 0;

            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == field[i][0] && field[i][0] != 0) {
                    columnCounter++;
                }
                if (field[j][i] == field[0][i] && field[0][i] != 0) {
                    rowCounter++;
                }
            }
            if (columnCounter == field.length || rowCounter == field.length) {
                isWinner = true;
                System.out.println("Row  " + rowCounter + " Column " + columnCounter);
            }
        }
        int mainDiagonalCounter = 0;
        int secondaryDiagonalCounter = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] == field[0][0] && field[0][0] != 0) {
                mainDiagonalCounter++;
            }
            if (field[i][field.length - 1 - i] == field[field.length - 1][0] && field[field.length - 1][0] != 0) {
                secondaryDiagonalCounter++;
            }
        }
        if (mainDiagonalCounter == field.length || secondaryDiagonalCounter == field.length) {
            isWinner = true;
            System.out.println("MainDiagonal " + mainDiagonalCounter + " Secondary " + secondaryDiagonalCounter);
        }
        return Boolean.toString(isWinner);
    }

//    public boolean botStep () {
//
//
//        for (int i = 0; i < field.length; i++) {
//            if (field[field.length - i - 1][i] == 0) {
//                step(field.length - i - 1, i, botVersusPlayerStep.whoTicWhoTac());
//                return true;
//            }
//            if (field[field.length - i - 1][field.length - i - 1] == 0) {
//                step(field.length - i - 1, field.length - i - 1, botVersusPlayerStep.whoTicWhoTac());
//                return true;
//            }
//
//        }
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field.length; j++) {
//                if (field[j][i] == 0) {
//                    step(j, i, whoTicWhoTac());
//                    return true;
//                }
//                if (field[field.length - j - 1][i] == 0) {
//                    step(field.length - j - 1, i, botVersusPlayerStep.whoTicWhoTac());
//                    return true;
//                }
//
//
//            }
//        }
//
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field.length; j++) {
//                if (field[i][j] == 0) {
//                    step(i, j, botVersusPlayerStep.whoTicWhoTac());
//                    return true;
//                }
//                if (field[i][field.length - i - 1] == 0) {
//                    step(i, field.length - i - 1, botVersusPlayerStep.whoTicWhoTac());
//                    return true;
//                }
//
//            }
//        }
//        return false;
//
//    }


    public boolean botStep () {


        for (int i = 0; i < super.field.length; i++) {
            if (super.field[super.field.length - i - 1][i] == 0) {
                step(super.field.length - i - 1, i, botVersusPlayerStep.whoTicWhoTac());
                return true;
            }
            if (super.field[super.field.length - i - 1][super.field.length - i - 1] == 0) {
                step(super.field.length - i - 1, super.field.length - i - 1, botVersusPlayerStep.whoTicWhoTac());
                return true;
            }

        }
        for (int i = 0; i < super.field.length; i++) {
            for (int j = 0; j < super.field.length; j++) {
                if (super.field[j][i] == 0) {
                    step(j, i, whoTicWhoTac());
                    return true;
                }
                if (super.field[super.field.length - j - 1][i] == 0) {
                    step(super.field.length - j - 1, i, botVersusPlayerStep.whoTicWhoTac());
                    return true;
                }


            }
        }

        for (int i = 0; i < super.field.length; i++) {
            for (int j = 0; j < super.field.length; j++) {
                if (super.field[i][j] == 0) {
                    step(i, j, botVersusPlayerStep.whoTicWhoTac());
                    return true;
                }
                if (super.field[i][super.field.length - i - 1] == 0) {
                    step(i, super.field.length - i - 1, botVersusPlayerStep.whoTicWhoTac());
                    return true;
                }

            }
        }
        return false;

    }
    public String whoTicWhoTac () {
        if (randomFirstPlayer > randomSecondPlayer) {
            return "tac";
        } else {
            return "tic";
        }
    }
}



//class PlayerVersusPlayer extends TicTacToe{
//    public static void twoPlayers(TicTacToe tikTak, Scanner scanner) {
//        boolean start = true;
//        int a = 5;
//        for (int i = 0; i < tikTak.limit(); i++) {
//            tikTak.playerCheck();
//            System.out.println("Укажите игрока:");
//            String player = scanner.next();
//            for (int j = 0; j < 100; j++) {
//                if (player.equalsIgnoreCase(tikTak.playerCheck())) {
//                    System.out.println("Правильно. Ходит " + player);
//                    break;
//
//                } else {
//                    System.out.println("Неправильно.Ходит " + tikTak.playerCheck());
//                    System.out.println("Укажите правильного игрока");
//                    player = scanner.next();
//                    if (player.equalsIgnoreCase(tikTak.playerCheck())) {
//                        System.out.println("Теперь правильно");
//                        break;
//                    }
//                }
//            }
//            System.out.println("Введите строку:");
//            int number = scanner.nextInt();
//            System.out.println("Введите столбец");
//            int number2 = scanner.nextInt();
//            System.out.println("Игрок: " + player + "выбрал столбец: " + (number + 1) + " и строку: " + (number2 + 1));
//            tikTak.step(number, number2, player);
//
//            if (tikTak.winnerCheck().equalsIgnoreCase("true")) {
//                System.out.println("Игра окончена");
//                start = false;
//                break;
//            }
//        }
//        scanner.close();
//    }
//
//}


interface Steps {
    boolean step(int column, int row, String player);

    boolean botStep();

    String whoTicWhoTac();
}

interface PlayerAndWinnerCheck {
    String winnerCheck();

    String playerCheck();
}