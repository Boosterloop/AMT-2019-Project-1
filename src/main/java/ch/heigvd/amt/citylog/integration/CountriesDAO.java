package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.Country;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Stateless EJB implementing CRUD operations for countries
 */
@Stateless
public class CountriesDAO implements DAO<String, Country> {
    // Payara resource lookup
    @Resource(lookup = "jdbc/citylogdb")
    private DataSource dataSource;

    public List<Country> findAll() {
        List<Country> countries = new LinkedList<>();
        countries.add(new Country("CH", "Schweiz"));
        countries.add(new Country("IT", "Italia"));
        countries.add(new Country("DE", "Deutschland"));

        return countries;
    }

    @Override
    public Country create(Country entity) {
        return null;
    }

    @Override
    public Country findById(String id) {
        return null;
    }

    @Override
    public void update(Country entity) {
    }

    @Override
    public void deleteById(String id) {
    }
}
