package put.io.testing.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator = null;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void testAdd(){
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(-1, 2));
        assertEquals(3, calculator.add(3,0));
        assertEquals(1, calculator.add(-2,3));
        assertEquals(-5, calculator.add(-2,-3));
        assertEquals(0, calculator.add(0,0));
        assertEquals(-1, calculator.add(1,-2));
    }

    @Test
    public void testMultiply(){
        assertEquals(0, calculator.multiply(3,0));
        assertEquals(0, calculator.multiply(-2,0));
        assertEquals(2, calculator.multiply(-2,-1));
        assertEquals(-4, calculator.multiply(-2,2));
        assertEquals(0, calculator.multiply(0,0));
    }

}