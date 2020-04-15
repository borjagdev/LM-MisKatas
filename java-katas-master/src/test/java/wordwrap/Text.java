package wordwrap;

public class Text {
    private final String text;

    private Text(String text) {
        this.text = text;
    }
    private String value() {
        return text;
    }

    public static Text create(String text) {
        if (text == null){
            return new Text("");
        }
        return new Text(text);
    }
    public int length() {
        return text.length();
    }
    public Text append(Text otherText) {
        return new Text(value() + otherText.value());
    }
    public Text wordWrap(ColumnsWidth columnsWidth){
        if (length() <= columnsWidth.value()){
            return this;
        }
        ColumnsWidth wrapIndex = selectWrapIndex(columnsWidth);
        Text wrappedLine = wrapLine(wrapIndex);
        if (skipInitialWhiteSpace(columnsWidth, wrapIndex)) {
            wrapIndex = ColumnsWidth.create(wrapIndex.value() + 1);
        }
        Text remainingLines = remainingLines(wrapIndex);
        return wrappedLine.append(remainingLines.wordWrap(columnsWidth));
    }
    private ColumnsWidth selectWrapIndex(ColumnsWidth columnsWidth) {
        int whiteSpaceIndex = value().substring(0, columnsWidth.value() + 1)
                .lastIndexOf(' ');
        if ((whiteSpaceIndex != -1) && (whiteSpaceIndex <= columnsWidth.value())) {
            return ColumnsWidth.create(whiteSpaceIndex);
        }
        return columnsWidth;
    }
    private Text wrapLine(ColumnsWidth columnsWidth) {
        return new Text(value().substring(0, columnsWidth.value()) + "\n");
    }
    private boolean skipInitialWhiteSpace(ColumnsWidth columnsWidth, ColumnsWidth wrapIndex) {
        return columnsWidth != wrapIndex;
    }
    private Text remainingLines(ColumnsWidth columnsWidth) {
        return new Text(value().substring(columnsWidth.value()));
    }

    @Override
    public String toString() {
        return value();
    }
}
