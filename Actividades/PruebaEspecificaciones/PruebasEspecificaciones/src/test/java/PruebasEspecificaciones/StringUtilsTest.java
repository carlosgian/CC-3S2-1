package PruebasEspecificaciones;

import org.junit.jupiter.api.Test;
import static PruebasEspecificaciones.StringUtils.substringsBetween;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    @Test
    void strlsNullOrEmpty(){
        assertThat(substringsBetween(null, "a", "b"))
                .isEqualTo(null);
        assertThat(substringsBetween("", "a", "b"))
                .isEqualTo(new String[]{});
    }


    @Test
    void openIsNullOrEmpty(){
        assertThat(substringsBetween("abc", null, "b"))
                .isEqualTo(null);
        assertThat(substringsBetween("abc", "a", ""))
                .isEqualTo(null);
    }
    @Test
    void closeIsNullOrEmpty() {
        assertThat(substringsBetween("abc", "a", null))
                .isEqualTo(null);
        assertThat(substringsBetween("abc", "a", ""))
                .isEqualTo(null);
    }

    @Test
    void strOfLegth1(){
        assertThat(substringsBetween("a", "a", "b")).isEqualTo(null);
        assertThat(substringsBetween("a", "b", "a")).isEqualTo(null);
        assertThat(substringsBetween("a", "b", "b")).isEqualTo(null);
        assertThat(substringsBetween("a", "a", "a")).isEqualTo(null);

    }

    void openAndCloseOfLenght1(){
        assertThat(substringsBetween("abc", "x", "y")).isEqualTo(null);
        assertThat(substringsBetween("abc", "a", "y")).isEqualTo(null);
        assertThat(substringsBetween("abc", "x", "c")).isEqualTo(null);
        assertThat(substringsBetween("abc", "a", "c")).isEqualTo(new String[] { "b", "b"});
    }


    @Test
    void openAndCloseTagsOfDifferentSizes() {
        assertThat(substringsBetween("aabcc", "xx", "yy")).isEqualTo(null);
        assertThat(substringsBetween("aabcc", "aa", "yy")).isEqualTo(null);
        assertThat(substringsBetween("aabcc", "xx", "cc")).isEqualTo(null);
        assertThat(substringsBetween("aabbcc", "aa", "cc"))
                .isEqualTo(new String[] {"bb"});
        assertThat(substringsBetween("aabbccaaeecc", "aa", "cc"))
                .isEqualTo(new String[] {"bb", "ee"});
    }


    @Test
    void notSubstringBetweenOpenAndCloseTags(){
        assertThat(substringsBetween("aabb", "aa", "bb"))
                .isEqualTo(new String[] { ""});
    }
}

    /*@Test
    void closeTagAppearingMultipleTimes() {
        // Completa
    }*/

