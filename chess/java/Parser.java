import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public Parser(String dir) {
        pgnDir = new File(dir);
    }

    private File pgnDir;

    public void run() {
        for (File file : pgnDir.listFiles()) {
            parse(file);
        }
    }

    private void parse(File file) {
        try (Scanner in = new Scanner(file)) {
            long passedLength = 0;
            long proc = 0;
            System.out.println("Parsing ... 0%");
            while (in.hasNextLine()) {
                String line = in.nextLine();
                passedLength += line.length();
                long curProc = 100 * passedLength / file.length();
                if (curProc > proc) {
                    proc = curProc;
                    System.out.println("Parsing ... " + proc + "%");
                }
                String[] moves = line.replaceAll("\\d*\\.|\\[.*\\]|\\{.*\\}", "").trim().split(" ");
                Move curMove = Move.root;
                int curNumber = 1;
                Color curColor = Color.WHITE;
                for (String move : moves) {
                    if (move.isEmpty()) {
                        continue;
                    } else if (move.equals("1-0")) {
                        curMove.update(1);
                    } else if (move.equals("0-1")) {
                        curMove.update(-1);
                    } else if (move.equals("1/2-1/2")) {
                        curMove.update(0);
                    } else {
                        Move nextMove = curMove.getMoveByString(move);
                        if (nextMove == null) {
                            nextMove = new Move(curNumber, curColor, move, curMove);
                            curMove.addMove(nextMove);
                        }
                        if (curColor == Color.BLACK) {
                            ++curNumber;
                        }
                        curColor = curColor.other();
                        curMove = nextMove;
                    }
                }
            }
            System.out.println("Parsing ... done!");
        } catch (FileNotFoundException ex) {
            System.err.println("ERROR : Something bad with file!");
        }
    }
}
