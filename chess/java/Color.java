public enum Color {
    WHITE, BLACK;

    public Color other() {
        if (this == WHITE) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
