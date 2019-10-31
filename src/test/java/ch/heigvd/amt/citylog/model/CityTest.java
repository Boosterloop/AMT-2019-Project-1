package ch.heigvd.amt.citylog.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the City model
 *
 * @author Alison Savary, Luc Wachter
 */
public class CityTest {
    @Test
    public void cityShouldBeCreatable() {
        Country country = new Country("CH", "Schweiz");

        City city = new City(1234, "Saint-Pétersbourg", country);

        assertEquals(city.getName(), "Saint-Pétersbourg");
        assertEquals(city.getId(), 1234);
        assertEquals(city.getCountry(), country);
    }
}
