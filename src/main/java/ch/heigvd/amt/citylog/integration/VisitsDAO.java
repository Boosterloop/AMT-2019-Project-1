package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.Visit;

import java.util.List;

/**
 * Interface to specify actions carried by visits
 * and the types they use
 *
 * @author Luc Wachter, Alison Savary
 */
public interface VisitsDAO extends DAO<Integer, Visit> {
    List<Visit> findByUserAndCityId(String userId, Integer cityId);
}
