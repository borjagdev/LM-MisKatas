import org.junit.Test;
import wordwrap.ColumnsWidth;
import wordwrap.Text;

import static org.assertj.core.api.Assertions.assertThat;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

public class ThirdWordWrapShould {

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_columnsWidth_is_not_valid() {
        wordWrap("test", -1);
    }
    @Test
    public void return_empty_string() {
        assertThat(wordWrap(null, 1)).isEqualTo("");
    }
    @Test
    public void not_wrap_when_text_length_is_less_than_columnsWidth() {
        assertThat(wordWrap("test", 10)).isEqualTo("test");
    }
    @Test
    public void wrap_when_text_length_is_greater_than_columnsWidth() {
        assertThat(wordWrap("alongword", 6)).isEqualTo("alongw\nord");
        assertThat(wordWrap("areallylongword", 6)).isEqualTo("areall\nylongw\nord");
        assertThat(wordWrap("ohlalala", 2)).isEqualTo("oh\nla\nla\nla");
    }
    @Test
    public void wrap_by_white_spaces() {
        assertThat(wordWrap("hello world", 7)).isEqualTo("hello\nworld");
        assertThat(wordWrap("a lot of words for a single line", 10))
                .isEqualTo("a lot of\nwords for\na single\nline");
        assertThat(wordWrap("this is a test", 4)).isEqualTo("this\nis a\ntest");
        assertThat(wordWrap("abc def ghixxxyyy", 3)).isEqualTo("abc\ndef\nghi\nxxx\nyyy");
    }

    public String wordWrap(String text, int columnsWidth) {
        Text aText = Text.create(text);
        ColumnsWidth width = ColumnsWidth.create(columnsWidth);
        return aText.wordWrap(width).toString();
    }

}