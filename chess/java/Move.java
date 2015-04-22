import java.util.ArrayList;

public class Move {
    public Move(int number, Color color, String move, Move prevMove) {
        this.number = number;
        this.color = color;
        this.move = move;
        depth = 0;
        this.prevMove = prevMove;
        nextMoves = new ArrayList<>();
        probability = 0;
    }

    private int number;
    private Color color;
    private String move;
    private int depth;
    private Move prevMove;
    private ArrayList<Move> nextMoves;
    private double probability;

    public void addMove(Move nextMove) {
        nextMoves.add(nextMove);
    }

    public Move getMoveByString(String move) {
        for (Move curMove : nextMoves) {
            if (curMove.getMove().equals(move)) {
                return curMove;
            }
        }
        return null;
    }

    public void update(double newProb) {
        if (number == 0) {
            return;
        }
        probability = (probability * depth + newProb) / (++depth);
        prevMove.update(newProb);
    }

    public Color getColor() {
        return color;
    }

    public String getMove() {
        return move;
    }

    public Move getPrev() {
        return prevMove;
    }

    public ArrayList<Move> getMoves() {
        return nextMoves;
    }

    public double getProb() {
        return probability;
    }

    public double dif() {
        if (prevMove == null) {
            return Double.MAX_VALUE;
        } else {
            return probability - prevMove.probability;
        }
    }

    public static final Move root = new Move(0, null, null, null);
}
