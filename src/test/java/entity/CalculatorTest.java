package entity;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
	
	@Test
	public void shouldReturnZeroOnEmptyString() {
		assertEquals(0, Calculator.add(""));
	}
	
	@Test
	public void shouldRetrunNumberOnNumber() {
		assertEquals(1, Calculator.add("1"));
	}
	
	@Test
	public void shouldReturnSumOnTwoNumbersDelimitedByComma() {
		assertEquals(3, Calculator.add("1,2"));
	}
	
	@Test
	public void shouldReturnSumOnMultipleNumbers() {
		assertEquals(6, Calculator.add("1,2,3"));
	}
	
	@Test
	public void shouldAcceptNewLineAsValidDelimiter() {
		assertEquals(6, Calculator.add("1,2\n3"));
	}
	
	@Test
	public void shouldAcceptCustomDelimiterSntax() {
		assertEquals(3, Calculator.add("//;\n1;2"));
	}
	
	@Test
	public void customDelimiterCouldBeAlsoARegExpSpecialChar() {
		assertEquals(3, Calculator.add("//.\n1.2"));
	}
	
	@Test
	public void shouldRaiseExceptionOnNegatives() {
		try {
			Calculator.add("-1,2,3");
			fail("Exception expeted.");
		} catch (RuntimeException ex) {
			
		}
	}
	
	@Test
	public void exceptionMessageShouldContainTheNegativeNumber() {
		try {
			Calculator.add("-1,2,3");
			fail("Exception expeted.");
		} catch (RuntimeException ex) {
			assertEquals("Negatives not allowed: [-1]", ex.getMessage());
		}
	}

}
