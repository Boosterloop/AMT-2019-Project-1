package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.City;

import java.util.List;

/**
 * Interface to specify actions carried by countries
 * and the types they use
 *
 * @author Luc Wachter, Alison Savary
 */
public interface CitiesDAO extends DAO<Integer, City> {
    List<City> findAll();
}
