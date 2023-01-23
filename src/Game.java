import java.util.Scanner;
import java.util.*;

public class Game {
    private static final String SOLVED_ID = "123456780";
    Board theBoard;
    String originalBoardID;
    String boardName;

    /**
     *  Solve the provided board
     * @param label Name of board (for printing)
     * @param b Board to be solved
     */
    public void playGiven(String label, Board b) {
        theBoard = b;
        originalBoardID = b.getId();
        boardName = label;
        System.out.println("Board initial: " + boardName + " \n" + theBoard.toString());
        solve();

    }

    /**
     * Create a random board (which is solvable) by jumbling jumnbleCount times.
     * Solve
     * @param label Name of board (for printing)
     * @param jumbleCount number of random moves to make in creating a board
     */
    public void playRandom(String label, int jumbleCount) {
        theBoard = new Board();
        theBoard.makeBoard(jumbleCount);
        System.out.println(label + "\n" + theBoard);
        playGiven(label, theBoard);


    }


//"102453786"
    public static void main(String[] args) {
        String[] games = {"102453786", "123740658", "023156478", "413728065", "145236078", "123456870"};
        String[] gameNames = {"Easy Board", "Game1", "Game2", "Game3", "Game4", "Game5 No Solution"};
        Game g = new Game();
        Scanner in = new Scanner(System.in);
        Board b;
        String resp;
        for (int i = 0; i < games.length - 1; i++) {
            b = new Board(games[i]);
            g.playGiven(gameNames[i], b);
            System.out.println("Click any key to continue\n");
            resp = in.nextLine();
        }


        boolean playAgain = true;
        //playAgain = false;

        int JUMBLECT = 18;  // how much jumbling to do in random board
        while (playAgain) {
            g.playRandom("Random Board", JUMBLECT);

            resp = "";
            while (resp.equals("")) {
                System.out.println("Play Again?  Answer Y for yes\n");
                resp = in.nextLine().toUpperCase();
            }
            playAgain = (resp.charAt(0) == 'Y');
        }


    }

    /**
     * solves the current puzzle and prints out the solution
     *
     * @author Kate Numbers
     */
    public void solve() {
        LinkedList<State> queue = new LinkedList<State>();
        Node<State> startingState = new Node<State>(new State(theBoard.getId(), "")) ;
        queue.enqueue(startingState);
        char [] moves = getMoves();

        Integer added = 0;
        Integer removed = 0;

        Node<State> currState = startingState;

        //For unsolvable boards, it will go on forever
        while (true) {
            Board currBoard = new Board(currState.element.getId());
            if (currBoard.isSolved(currBoard.getId())) {
                break;
            }

            /*
            Attempts to make each move U, D, L, R. If succeeds, it will add that state to the LinkedList()
             */
            for (char move : moves) {
                Board makeMove = new Board(currBoard.getId());
                boolean moveMade = makeMove.makeMove(move, currState.element.getLast());
                if (moveMade) {
                    // Checks if the move made worked, if it did, it adds it to the queue
                    State newMove = new State(makeMove.getId(), currState.element.getSteps() + move);
                    queue.enqueue(new Node<State>(newMove));
                    added++;
                }
            }
            currState = currState.next;
            queue.dequeue();
            removed++;
        }

        // Prints out the solution
        System.out.println("Solution: ");
        printSteps(theBoard, currState.element.getSteps());
        System.out.println("Moves required: " + currState.element.getSteps() + "(" + currState.element.getNumSteps() + ")");
        System.out.println(boardName + " Queue Added= " + added + "\tQueue Removed= " + removed);
        System.out.println();
    }

    /**
     * gets a list of the all possible moves
     *
     * @return char []
     * @author Kate Numbers
     */
    private char [] getMoves() {
        char [] moves = new char [4];
        moves[0] = 'U';
        moves[1] = 'D';
        moves[2] = 'L';
        moves[3] = 'R';
        return moves;
    }

    /**
     * Prints the solution steps along with visual aids
     *
     * @param board
     * @param steps
     * @author Kate Numbers
     */
    public void printSteps(Board board, String steps) {
        System.out.println(board.toString());


        for (int i = 0; i < steps.length(); i++) {
            char lastMove = ' ';
            if (i - 1 != -1) {
                lastMove = steps.charAt(i - 1);
            }
            board.makeMove(steps.charAt(i), lastMove);
            System.out.println(steps.charAt(i) + "==>");
            System.out.println(board.toString());
        }
    }
}

