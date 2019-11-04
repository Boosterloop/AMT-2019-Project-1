package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.Country;

import java.util.List;

/**
 * Interface to specify actions carried by countries
 * and the types they use
 *
 * @author Luc Wachter, Alison Savary
 */
public interface CountriesDAO extends DAO<String, Country> {
    List<Country> findAll();
}
