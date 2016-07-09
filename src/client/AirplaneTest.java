package client;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Airplane JUnit Test
 * <p>
 *     
 * </p>
 *
 * @author Andrew
 */

public class AirplaneTest {

	@Test
	public void testAirplaneStringStringIntInt() {
		Airplane airplane = new Airplane("A380", "Airbus", 10, 80);
		assertTrue(airplane.getModel().equals("A380") && airplane.getManf().equals("Airbus") && airplane.getFirst() == 10 && airplane.getCoach() == 80);
	}

	@Test
	public void testAirplaneStringStringStringString() {
		Airplane airplane = new Airplane("A380", "Airbus", "10", "80");
		assertTrue(airplane.getModel().equals("A380") && airplane.getManf().equals("Airbus") && airplane.getFirst() == 10 && airplane.getCoach() == 80);
	}

	@Test
	public void testToString() {
		Airplane airplane = new Airplane("A380", "Airbus", "10", "80");
		assertEquals(airplane.toString(), "< A380, Airbus, seat(first/coach):10/80 >");
	}

}
