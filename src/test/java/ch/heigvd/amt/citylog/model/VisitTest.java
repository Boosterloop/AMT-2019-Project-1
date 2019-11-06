package ch.heigvd.amt.citylog.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Visit model
 *
 * @author Alison Savary, Luc Wachter
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VisitTest {
    User user = null;
    City city = null;
    Date date = null;

    @BeforeAll
    public void setup() throws ParseException {
        user = new User("laykel", "ZüR1chB3rn");
        Country country = new Country("CH", "Schweiz");
        city = new City(23, "Kaltbrunn", country);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        date = sdf.parse("2014.3.24");
    }

    @Test
    public void visitShouldBeCreatableWithOnlyStartDate() {
        Visit visit = new Visit(user, city, date);

        assertEquals(city, visit.getCity());
        assertEquals(user, visit.getUser());
        assertEquals(date, visit.getStartDate());
    }

    @Test
    public void visitShouldBeCreatableWithAllArgs() {
        Visit visit = new Visit(user, city, date, date);

        assertEquals(city, visit.getCity());
        assertEquals(user, visit.getUser());
        assertEquals(date, visit.getStartDate());
        assertEquals(date, visit.getEndDate());
    }
}
