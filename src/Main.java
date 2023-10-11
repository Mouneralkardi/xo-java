import java.util.Scanner;

class TicTacToe {
    private char[][] board;     // En matris för spelbrädet.
    private char currentPlayer;  // Den spelare som har nästa drag.
    private String playerXName; // Namnet på spelare X.
    private String playerOName; // Namnet på spelare O.

    public TicTacToe() {
        board = new char[3][3];   // Skapar en 3x3 rutsnäda för spelet.
        currentPlayer = 'X';       // Börjar med spelare X.
        playerXName = "";         // Inget namn för spelare X i början.
        playerOName = "";         // Inget namn för spelare O i början.
        initializeBoard();        // Förbereder rutsnädan för spelet.
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);  // För att läsa användarinmatning.

        System.out.print("Namn på spelare X: ");
        playerXName = scanner.nextLine();  // Frågar efter namnet på spelare X.
        System.out.print("Namn på spelare O: ");
        playerOName = scanner.nextLine();  // Frågar efter namnet på spelare O.

        while (true) {  // Spel-loop tills användaren väljer att avsluta.
            printBoard();  // Visar rutsnädan.
            System.out.println("Det är " + (currentPlayer == 'X' ? playerXName : playerOName) + "s tur.");
            // Visar vems tur det är baserat på nuvarande spelare.

            System.out.println("Ange ditt drag: ");
            String move = scanner.nextLine();  // Läser in användarens drag.

            if (isValidMove(move)) {
                int row = move.charAt(0) - 'A';  // Konverterar bokstavsdelen av draget till radnummer (0, 1, 2).
                int col = move.charAt(1) - '1';  // Konverterar sifferdelen av draget till kolumnnummer (0, 1, 2).
                board[row][col] = currentPlayer;  // Uppdaterar rutsnädan med användarens drag.

                if (isGameWon()) {  // Kollar om någon vinner.
                    printBoard();
                    System.out.println(playerXName + " vinner!");
                    // Visar vinnaren baserat på nuvarande spelare.
                    if (playAgain(scanner)) {  // Frågar om användaren vill spela igen.
                        initializeBoard();     // Återställer rutsnädan.
                        currentPlayer = 'X';    // Börjar med spelare X igen.
                        continue;
                    } else {
                        break;  // Avslutar spelet om användaren inte vill spela igen.
                    }
                } else if (isBoardFull()) {  // Kollar om det blir oavgjort.
                    printBoard();
                    System.out.println("Oavgjort!");
                    if (playAgain(scanner)) {  // Frågar om användaren vill spela igen.
                        initializeBoard();     // Återställer rutsnädan.
                        currentPlayer = 'X';    // Börjar med spelare X igen.
                        continue;
                    } else {
                        break;  // Avslutar spelet om användaren inte vill spela igen.
                    }
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Byter spelare för nästa drag.
            } else {
                System.out.println("Ogiltigt drag. Försök igen.");
                // Meddelande om att draget är ogiltigt om det inte uppfyller kraven.
            }
        }

        scanner.close();  // Stänger av användarinput när spelet är klart.
    }



    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';// Förbereder rutsnädan med tomma rutor.
            }
        }
    }

    private void printBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " "); // Visar rutsnädan.
            }
            System.out.println();
        }
    }


    private boolean isValidMove(String move) {
        if (move.length() != 2) {
            return false;  // Ogiltigt drag om det inte är två tecken långt.
        }

        int row = move.charAt(0) - 'A';
        int col = move.charAt(1) - '1';

        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false;  // Ogiltigt drag om det är utanför brädet eller om rutan är upptagen.
        }

        return true;
    }
    private boolean isGameWon() {
        // Kolla om någon har vunnit spelet.

        // Kolla rader
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Om någon har vunnit i en rad.
            }
        }

        // Kolla kolumner
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true; // Om någon har vunnit i en kolumn.
            }
        }

        // Kolla diagonaler
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Om någon har vunnit på diagonalen från övre vänstra till nedre högra.
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Om någon har vunnit på diagonalen från övre högra till nedre vänstra.
        }

        return false; // Ingen har vunnit ännu.
    }

    private boolean isBoardFull() {
        // Kolla om spelbrädet är fullt.

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Det finns åtminstone ett tomt utrymme på brädet.
                }
            }
        }
        return true; // Brädet är fullt utan tomma utrymmen.
    }

    private boolean playAgain(Scanner scanner) {
        // Fråga om spelaren vill spela igen.

        System.out.println("Spela igen? (Ja/Nej): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        return choice.equals("ja"); // Returnera true om svaret är "ja".
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
