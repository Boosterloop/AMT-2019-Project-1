package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.City;
import utils.Pair;

import java.util.List;

/**
 * Interface to specify actions carried by countries
 * and the types they use
 *
 * @author Luc Wachter, Alison Savary
 */
public interface CitiesDAO extends DAO<Integer, City> {
    /**
     * Get all cities in alphabetical order of the city name
     *
     * @return A list of all cities in alphabetical order
     */
    List<City> findAll();

    /**
     * Get all cities and their popularity in order of popularity
     *
     * @return A list of all cities and their popularity in order of popularity
     */
    List<Pair<City, Integer>> findAllByPopularity(int start, int nbrOfElements, String search);

    /**
     * Find all cities visited by given user
     *
     * @param userId The visiting user's id
     * @return A list of cities visited by the user and their popularity
     */
    List<Pair<City, Integer>> findByUserId(String userId);

    /**
     * Get total number of cities in DB
     *
     * @return number of cities
     */
    int getNumberOfCities();
}
