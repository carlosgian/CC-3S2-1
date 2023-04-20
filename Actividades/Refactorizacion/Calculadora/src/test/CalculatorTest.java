package test;


import main.Calculadora;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private static Calculadora calculadora;

    @BeforeAll
    public static void init(){
        calculadora = new Calculadora();
    }

    @Test
    public void whenCalculatorInitializedThenReturnTrue(){
        assertTrue(calculadora.getStatus());
    }

    @Test
    public void whenAdditionTwoNumberThenReturnCorrectAnswer(){
        assertEquals(5, calculadora.addition(2,3));
    }

    @Test
    public void whenSubtractionTwoNumberThenReturnCorrectAnswer(){
        assertEquals(4,calculadora.subtraction(8,4));
    }

    @Test
    public void whenDivisionThenReturnCorrectAnswer(){
        assertEquals(2, calculadora.division(8,4));
    }

    @Test
    public void whenDivisionByZeroThenThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{
            calculadora.division(5,0);}
            );
            assertEquals("No se puede dividir por cero", exception.getMessage());
    }

//PRUEBAS PARA LOS NUEVOS MÉTODOS EMPIEZAN AQUÍ
    @Test
    public void whenMultiplyTwoNumberThenReturnCorrectAnswer(){
        assertEquals(14, calculadora.multiply(7, 2));
    }

    @Test
    public void RemainderOfTwoNumbersReturnsCorrectAnswer(){
        assertEquals(2, calculadora.remainder(7,5));
        assertEquals(4, calculadora.remainder(4,16));
        assertEquals(0, calculadora.remainder(0,15));
    }

    @Test
    public void RemainderWithCeroThrowsException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            calculadora.remainder(4, 0);}
        );
        assertEquals("No se puede calcular el residuo con cero", exception.getMessage());
    }

    @Test
    public void WhenExponentiationTwoNumbersReturnCorrectAnswer(){
        assertEquals(343, calculadora.exponentiation(7,3));
        assertEquals(1, calculadora.exponentiation(5,0));
    }

    @Test
    public void WhenExponentiationWithNegativeThrowsException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.exponentiation(5,-1);}
        );
        assertEquals("No se permiten exponentes negativos en el dominio de los enteros", exception.getMessage());
    }

}
