import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    TO DO
    .....
    ("test", -1) -> throw exception
    (null, 1) -> ""
    ("", 1) -> ""
    ("test", 7) -> "test"
    ("a longword", 6) -> "a long--word"
    ("areallylongword", 6) -> "areall--ylongw--ord"
    ("hello world", 7) -> "hello--world"
    ("a lot of words for a single line", 10) -> "a lot of--words for--a single--line"
    ("this is a test", 4) -> "this--is a--test"
 */

public class AnotherWordWrapShould {

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_columnsWidth_is_not_valid() {
        wordWrap("test", -1);
    }
    @Test
    public void return_empty_string_when_text_is_null() {
        assertThat(wordWrap(null, 1)).isEqualTo("");
    }
    @Test
    public void return_empty_string_when_text_is_empty() {
        assertThat(wordWrap("", 1)).isEqualTo("");
    }
    @Test
    public void not_wrap_when_text_length_is_less_than_columnsWidth() {
        assertThat(wordWrap("test", 7)).isEqualTo("test");
    }
    @Test
    public void wrap_when_text_length_is_greater_than_columnsWidth() {
        assertThat(wordWrap("a longword", 6)).isEqualTo("a long\nword");
        assertThat(wordWrap("areallylongword", 6)).isEqualTo("areall\nylongw\nord");
    }

    private String wordWrap(String text, int columnsWidth) {
        if (columnsWidth < 1) {
            throw new IllegalArgumentException("columnsWidth must be greater than 0");
        }
        if (text == null) {
            return "";
        }

        if (text.length() < columnsWidth) {
            return text;
        }
        String wrappedText = text.substring(0,columnsWidth) + "\n";
        text = text.substring(columnsWidth);
        wrappedText += wordWrap(text, columnsWidth);
        return wrappedText;
    }

}