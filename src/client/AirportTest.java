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

public class AirportTest {

	@Test
	public void testAirportStringStringDoubleDouble() {
		Airport airport = new Airport("Logan", "BOS", 42.3656, 71.0096);
		assertTrue(airport.getName().equals("Logan") && airport.getCode().equals("BOS") && airport.getLat() == 42.3656 && airport.getLon() == 71.0096);
	}

	@Test
	public void testAirportStringStringStringString() {
		Airport airport = new Airport("Logan", "BOS", "42.3656", "71.0096");
		assertTrue(airport.getName().equals("Logan") && airport.getCode().equals("BOS") && airport.getLat() == 42.3656 && airport.getLon() == 71.0096);
	}

	@Test
	public void testToString() {
		Airport airport = new Airport("Logan", "BOS", 42.3656, 71.0096);
		assertEquals(airport.toString(), "< Logan, BOS, lat:42.3656, lon:71.0096 >");
	}
}
