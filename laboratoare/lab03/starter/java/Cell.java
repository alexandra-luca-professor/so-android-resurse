package ro.so.android.numeprenume;

public final class Cell {
    public final int col;
    public final int row;

    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "(" + col + ", " + row + ")";
    }
}
