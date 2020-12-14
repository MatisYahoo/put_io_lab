package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailureOrErrorTest {

    @Test
    public void test1(){
        assertFalse(0 < 1);
    }

    @Test
    public void test2(){
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(1, 2));
    }

    @Test
    public void test3(){
        try {
            assertFalse(0 < 1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}