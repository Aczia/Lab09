import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalcTest {
	private CalcEngineString calc;

	@BeforeEach
	void setUp() throws Exception {
		calc = new CalcEngineString();	
	}

	@Test
	void multiplBeforeAdditionTest() {
		assertEquals(8, calc.evaluates(calc.infixToPostfix("2+2*3")));
		assertEquals(10, calc.evaluates(calc.infixToPostfix("6+1*2+4-2")));
		assertEquals(0, calc.evaluates(calc.infixToPostfix("0+0*2")));
	}
	
	@Test
	void divisionBeforeSubtractionTest() {
		assertEquals(5, calc.evaluates(calc.infixToPostfix("8-6/2")));
		assertEquals(3, calc.evaluates(calc.infixToPostfix("4-4/4")));
		assertEquals(0, calc.evaluates(calc.infixToPostfix("5-5/1")));
	}
	
	@Test
	void parenthesisPriorityTest() {
		assertEquals(9, calc.evaluates(calc.infixToPostfix("(1+2)*3")));
		assertEquals(0, calc.evaluates(calc.infixToPostfix("(3+2)*0")));
		assertEquals(0, calc.evaluates(calc.infixToPostfix("(1-1)*8")));
	}	
	
	@Test
	void divisionByZiroTest() {
		assertThrows(ArithmeticException.class, () -> {
			calc.evaluates(calc.infixToPostfix("8/0"));
        });
	}

}
