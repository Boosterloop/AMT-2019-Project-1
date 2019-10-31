package ch.heigvd.amt.citylog.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Country model
 *
 * @author Alison Savary, Luc Wachter
 */
public class CountryTest {
    @Test
    public void countryShouldBeCreatable() {
        Country country = new Country("LY", "Libye");
        
        assertEquals(country.getCode(), "LY");
        assertEquals(country.getName(), "Libye");
    }
}
