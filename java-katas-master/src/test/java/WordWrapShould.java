import org.junit.Test;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.assertj.core.api.Assertions.assertThat;

/*
    TO DO LIST
    ..........
    "hola", -1 -> Throw Exception
    null, 1 -> ""
    "", 1 -> ""
    "hola", 7 -> "hola"
    "hola", 2 -> "ho\nla"
    "hola mundo", 7 -> "hola\n mundo"
 */

public class WordWrapShould {

    @Test (expected = IllegalArgumentException.class)
    public void columnsWidthIsLessThan1() { wordWrap("hola", -1); }

    @Test
    public void textIsNull () {
        assertThat(wordWrap(null, 1)).isEqualTo("");
    }

    @Test
    public void textIsEmpty () {
        assertThat(wordWrap("", 1)).isEqualTo("");
    }

    @Test
    public void textLengthIsShorterThanColumnWidth () {
        assertThat(wordWrap("hola", 7)).isEqualTo("hola");
    }

    @Test
    public void textLengthIsBiggerThanColumnWidth () {
        assertThat(wordWrap("hola", 2)).isEqualTo("ho\nla");
        assertThat(wordWrap("hooolaaaa", 2)).isEqualTo("ho\noo\nla\naa\na");
        assertThat(wordWrap("hola", 3)).isEqualTo("hol\na");
        assertThat(wordWrap("holitaa", 5)).isEqualTo("holit\naa");
    }

    @Test
    public void textIncludesWhiteSpaces () {
        assertThat(wordWrap("hola mundo", 7)).isEqualTo("hola\n mundo");
        assertThat(wordWrap("hola mundo", 1)).isEqualTo("h\no\nl\na\n \nm\nu\nn\nd\no");
        assertThat(wordWrap("hola mundo", 2)).isEqualTo("ho\nla\n m\nun\ndo");
        assertThat(wordWrap("                ", 4)).isEqualTo("    \n    \n    \n    ");
        assertThat(wordWrap("  as asd   d", 7)).isEqualTo("  as as\nd   d");
    }

    @Test
    public void columnsWidthMatchesWithNewLineCharacter () {
        assertThat(wordWrap("Saludando\nal mundo", 10)).isEqualTo("Saludando\n\nal mundo");
    }

    @Test
    public void columnsWidthMatchesWithTabCharacter () {
        //assertThat(wordWrap("Saludando\tal mundo", 10)).isEqualTo("Saludando\n\nal mundo");
    }

    private String wordWrap(String text, int columnsWidth){

        if (columnsWidth < 1) {
            throw new IllegalArgumentException("columnsWidth must be greater than 0");
        }
        if (text == null) {
            return "";
        }

        String textSubstring = text;
        String result = "";
        while (textSubstring.length() > columnsWidth) {
            int wrapIndex = calculateWrapIndex(text, columnsWidth);
            result += wrapLine(textSubstring, wrapIndex);
            textSubstring = textSubstring.substring(wrapIndex);
        }
        if (textSubstring.length() > 0) {
            result += textSubstring;
        }
        return result;
    }

    private String wrapLine(String textSubstring, int wrapIndex) {
        return textSubstring.substring(0, wrapIndex) + "\n";
    }

    private int calculateWrapIndex(String text, int columnsWidth) {
        int whiteSpaceIndex = text.indexOf(" ");
        // whiteSpaceIndex debe ser estrictamente mayor que 0 porque el
        // mínimo ancho de columna es 1
        boolean thereIsWhiteSpaceOnCurrentLine = (whiteSpaceIndex > 0)
                                                    && (columnsWidth > whiteSpaceIndex);
        int wrapIndex = columnsWidth;
        if (thereIsWhiteSpaceOnCurrentLine) {
            wrapIndex = whiteSpaceIndex;
        }
        return wrapIndex;
    }

}
