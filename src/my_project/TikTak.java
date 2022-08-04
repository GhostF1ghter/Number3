package my_project;



import java.util.Scanner;


public class TikTak {
    int[][] field;
    String botGame;
    static int randomFirstPlayer;
    static int randomSecondPlayer;

    public static void main(String[] args) {


        TikTak tikTak = new TikTak();
        tikTak.startGame(4);

        Scanner scanner = new Scanner(System.in);


        System.out.println("Играем с ботом? Да или нет.");
        String botGame = scanner.next();
        if (botGame.equalsIgnoreCase("Да")) {

            randomFirstPlayer = (int) (Math.random() * 100);
            randomSecondPlayer = (int) (Math.random() * 100);
            System.out.println("Игрок выбрасывает: " + randomFirstPlayer); // изменить на 0 1
            System.out.println("Бот выбрасывает: " + randomSecondPlayer);

            // уменшить длину кода
            if (randomFirstPlayer > randomSecondPlayer) {
                playerIsGoingFirst(tikTak, scanner);
            } else {
                botIsGoingFirst(tikTak, scanner);
            }
        }
         else {
            twoPlayers(tikTak, scanner);
        }
    }


        public static void playerIsGoingFirst (TikTak tikTak, Scanner scanner){

            boolean start = true;
            for (int i = 0; i < tikTak.limit(); i++) {

                tikTak.playerCheck();
                System.out.println("Ходите вы. Введите столбец и строку");
                int column = scanner.nextInt();
                int row = scanner.nextInt();
                System.out.println("Вы выбрали столбец: " + (column + 1) + " и строку: " + (row + 1));
                tikTak.step(column, row, "tic");
//                tikTak.step(column, row, "tic");
                if (tikTak.winnerCheck().equalsIgnoreCase("true")) {
                    System.out.println("Игра окончена");
                    start = false;
                    break; // изменить на while
                }
                System.out.println("Ходит бот");
                tikTak.botStep();
                if (tikTak.winnerCheck().equalsIgnoreCase("true")) {
                    System.out.println("Игра окончена");
                    start = false;
                    break;
                }

            }
            scanner.close();
        }

        public static void botIsGoingFirst (TikTak tikTak, Scanner scanner){
            boolean start = true;

            for (int i = 0; i < tikTak.limit(); i++) {
                tikTak.playerCheck();
                System.out.println("Ходит бот");
                tikTak.botStep();
                if (tikTak.winnerCheck().equalsIgnoreCase("true")) {
                    System.out.println("Игра окончена");
                    start = false;
                    break;
                }
                System.out.println("Ходите вы. Введите столбец и строку");
                int column = scanner.nextInt();
                int row = scanner.nextInt();
                System.out.println("Вы выбрали столбец: " + (column + 1) + " и строку: " + (row + 1));
                tikTak.step(column, row, "tac");
                if (tikTak.winnerCheck().equalsIgnoreCase("true")) {
                    System.out.println("Игра окончена");
                    start = false;
                    break;
                }

            }
            scanner.close();
        }

        public static void twoPlayers (TikTak tikTak, Scanner scanner){
            boolean start = true;
            int a = 5;
            for (int i = 0; i < tikTak.limit(); i++) {
                tikTak.playerCheck();
                System.out.println("Укажите игрока:");
                String player = scanner.next();
                for (int j = 0; j < 100; j++) {
                    if (player.equalsIgnoreCase(tikTak.playerCheck())) {
                        System.out.println("Правильно. Ходит " + player);
                        break;

                    } else {
                        System.out.println("Неправильно.Ходит " + tikTak.playerCheck());
                        System.out.println("Укажите правильного игрока");
                        player = scanner.next();
                        if (player.equalsIgnoreCase(tikTak.playerCheck())) {
                            System.out.println("Теперь правильно");
                            break;
                        }
                    }
                }
                System.out.println("Введите строку:");
                int number = scanner.nextInt();
                System.out.println("Введите столбец");
                int number2 = scanner.nextInt();
                System.out.println("Игрок: " + player + "выбрал столбец: " + (number + 1) + " и строку: " + (number2 + 1));
                tikTak.step(number, number2, player);

                if (tikTak.winnerCheck().equalsIgnoreCase("true")) {
                    System.out.println("Игра окончена");
                    start = false;
                    break;
                }
            }
            scanner.close();
        }


        public int limit() {
            return field.length * field.length;
        }


        public void startGame ( int size){
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


        public void print () {
            for (int j = 0; j < field.length; j++) {
                for (int i = 0; i < field.length; i++) {
                    System.out.print(field[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        public boolean step ( int column, int row, String player){


            if (player.equalsIgnoreCase("tic")) {
                this.field[column][row] = 1;
            }
            if (player.equalsIgnoreCase("tac")) {
                this.field[column][row] = 2;
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


        public String playerCheck () {
            int counterFirstPlayer = 0;
            int counterSecondPlayer = 0;
            String tic = "Ходят крестики (1)";
            String tac = "Ходят нолики (2) ";

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == 1) {
                        counterFirstPlayer++;
                    }
                    if (field[i][j] == 2) {
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

        // Использовать 4 цикла. While
        public boolean botStep () {


                    for (int i = 0; i < field.length; i++) {
                        if (field[field.length - i - 1][i] == 0) {
                            step(field.length - i - 1, i, whoTicWhoTac());
                            return true;
                        }
                        if (field[field.length - i - 1][field.length - i - 1] == 0) {
                            step(field.length - i - 1, field.length - i - 1, whoTicWhoTac());
                            return true;
                        }

                    }
                    for (int i = 0; i < field.length; i++) {
                        for (int j = 0; j < field.length; j++) {
                            if (field[j][i] == 0) {
                                step(j, i, whoTicWhoTac());
                                return true;
                            }
                            if (field[field.length - j - 1][i] == 0) {
                                step(field.length - j - 1, i, whoTicWhoTac());
                                return true;
                            }


                        }
                    }

                    for (int i = 0; i < field.length; i++) {
                        for (int j = 0; j < field.length; j++) {
                            if (field[i][j] == 0) {
                                step(i, j, whoTicWhoTac());
                                return true;
                            }
                            if (field[i][field.length - i - 1] == 0) {
                                step(i, field.length - i - 1, whoTicWhoTac());
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


