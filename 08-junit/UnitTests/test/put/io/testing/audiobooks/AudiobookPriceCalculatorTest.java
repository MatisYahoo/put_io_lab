package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import put.io.testing.junit.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
    AudiobookPriceCalculator calculator = null;

    @BeforeEach
    public void setUp(){
        calculator = new AudiobookPriceCalculator();
    }

    @Test
    public void testSubscriber(){
        Customer customer = new Customer("one", Customer.LoyaltyLevel.STANDARD, true);
        Audiobook audiobook = new Audiobook("audio1", 10);
        assertEquals(0, calculator.calculate(customer ,audiobook));
    }

    @Test
    public void testSilver(){
        Customer customer = new Customer("two", Customer.LoyaltyLevel.SILVER, false);
        Audiobook audiobook = new Audiobook("audio2", 10);
        assertEquals(9, calculator.calculate(customer ,audiobook));
    }

    @Test
    public void testGold(){
        Customer customer = new Customer("three", Customer.LoyaltyLevel.GOLD, false);
        Audiobook audiobook = new Audiobook("audio3", 10);
        assertEquals(8, calculator.calculate(customer ,audiobook));
    }

    @Test
    public void testStandard(){
        Customer customer = new Customer("one", Customer.LoyaltyLevel.STANDARD, false);
        Audiobook audiobook = new Audiobook("audio1", 10);
        assertEquals(10, calculator.calculate(customer ,audiobook));
    }
}