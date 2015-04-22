public class Chess {
    public static void main(String[] args) {
        Parser parser = new Parser("D:\\pgn");
        parser.run();
        Executor executor = new WorstMoveExecutor();
        executor.execute();
    }
}
