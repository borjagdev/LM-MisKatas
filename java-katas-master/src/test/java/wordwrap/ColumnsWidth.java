package wordwrap;

public class ColumnsWidth {
    private final int columnsWidth;

    private ColumnsWidth(int columnsWidth) {
        this.columnsWidth = columnsWidth;
    }
    public static ColumnsWidth create(int columnsWidth) {
        if (columnsWidth < 1) {
            throw new IllegalArgumentException("columnsWidth must be greater than 0");
        }
        return new ColumnsWidth(columnsWidth);
    }
    public int value() {
        return columnsWidth;
    }
}
