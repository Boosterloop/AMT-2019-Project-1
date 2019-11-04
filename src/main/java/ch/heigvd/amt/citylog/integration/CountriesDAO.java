package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.Country;

import java.util.List;

public interface CountriesDAO extends DAO<String, Country> {
    List<Country> findAll();
}
