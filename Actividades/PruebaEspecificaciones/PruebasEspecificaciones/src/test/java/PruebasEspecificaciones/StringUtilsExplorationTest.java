package PruebasEspecificaciones;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Estas pruebas son realizados en la fase de exploracion (paso 2)
 */
public class StringUtilsExplorationTest {

    public static StringUtils stringUtils;

    @Test
    void simpleCase(){
        assertThat(
                stringUtils.substringsBetween("abcd", "a", "d")
        ).isEqualTo(new String[] { "bc" });
    }

    @Test
    void manyStrings(){
        assertThat(
                stringUtils.substringsBetween("abcdabcdab", "a", "d")
        ).isEqualTo(new String[] { "bc", "bc" });
    }
    @Test
    void openAndCloseTagsThatAreLongerThan1Char(){
        assertThat(
                stringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")
        ).isEqualTo(new String[] { "bc", "bf" });
    }

}
