import java.util.ArrayList;
import java.util.Collections;

public class WorstMoveExecutor implements Executor {
    public WorstMoveExecutor() {

    }

    public void execute() {
        System.out.println("\nWorstMove:");
        System.out.print("Searching ... ");
        Move ans = dfs(Move.root);
        System.out.println("done!");
        ArrayList<String> moves = new ArrayList<>();
        while (ans.getPrev() != null) {
            moves.add(String.format("%s (%f; %+f)", ans.getMove(), ans.getProb(), ans.dif()));
            ans = ans.getPrev();
        }
        Collections.reverse(moves);
        for (int i = 0; i < moves.size(); ++i) {
            if (i % 2 == 0) {
                System.out.printf("\n%d. ", i / 2 + 1);
            }
            System.out.print(moves.get(i) + " ");
        }
    }

    private Move dfs(Move move) {
        Move ans = Move.root;
        for (Move nextMove : move.getMoves()) {
            Move cur = dfs(nextMove);
            if (cur.dif() < ans.dif()) {
                ans = cur;
            }
        }
        if (move.dif() * (move.getColor() == Color.BLACK ? -11 : 1) < ans.dif()) {
            ans = move;
        }
        return ans;
    }
}
