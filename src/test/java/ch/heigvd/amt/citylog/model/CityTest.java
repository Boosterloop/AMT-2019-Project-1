package ch.heigvd.amt.citylog.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the City class
 *
 * @author Alison Savary, Luc Wachter
 */
public class CityTest {
    @Test
    public void cityShouldBeCreatable() {
        City city = new City(1234, "Saint-Pétersbourg", 12);

        assertEquals(city.getName(), "Saint-Pétersbourg");
        assertEquals(city.getId(), 1234);
        assertEquals(city.getCountryId(), 12);
    }
}
