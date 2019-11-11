package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.City;
import ch.heigvd.amt.citylog.model.Country;
import utils.Pair;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless EJB implementing CRUD operations for countries
 *
 * @author Luc Wachter, Alison Savary
 */
@Stateless
public class CitiesDAOImpl implements CitiesDAO {
    // Payara resource lookup
    @Resource(lookup = "jdbc/citylogdb")
    private DataSource dataSource;

    @EJB
    private CountriesDAO countries;

    @Override
    public List<City> findAll() {
        List<City> cities = new LinkedList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM City ORDER BY name ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("idCity");
                String name = resultSet.getString("name");
                String countryCode = resultSet.getString("fk_countryCode");
                // Get country from resulting id
                Country country = countries.findById(countryCode);
                cities.add(new City(Integer.parseInt(id), name, country));
            }
            connection.close();

            return cities;
        } catch (SQLException ex) {
            Logger.getLogger(CitiesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public List<Pair<City, Integer>> findAllByPopularity() {
        List<Pair<City, Integer>> citiesPopularity = new LinkedList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT idCity, City.name, fk_countryCode, COUNT(*) AS count FROM City " +
                    "INNER JOIN Visit ON City.idCity = Visit.fk_idCity GROUP BY City.idCity ORDER BY COUNT(*) DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("idCity");
                String name = resultSet.getString("name");
                String countryCode = resultSet.getString("fk_countryCode");
                int count = Integer.parseInt(resultSet.getString("count"));
                // Get country from resulting id
                Country country = countries.findById(countryCode);

                City newCity = new City(Integer.parseInt(id), name, country);
                citiesPopularity.add(new Pair<>(newCity, count));
            }
            connection.close();

            return citiesPopularity;
        } catch (SQLException ex) {
            Logger.getLogger(CitiesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public List<Pair<City, Integer>> findByUserId(String userId) {
        List<Pair<City, Integer>> visits = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("SELECT idCity, City.name, fk_countryCode, COUNT(*) AS count FROM " +
                    "City INNER JOIN Visit ON City.idCity = Visit.fk_idCity WHERE Visit.fk_username=? GROUP BY City" +
                    ".idCity, City.name ORDER BY City.name ASC");
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("idCity");
                String name = resultSet.getString("name");
                String countryCode = resultSet.getString("fk_countryCode");
                int count = Integer.parseInt(resultSet.getString("count"));
                // Get country from resulting id
                Country country = countries.findById(countryCode);

                City newCity = new City(Integer.parseInt(id), name, country);
                visits.add(new Pair<>(newCity, count));
            }

            return visits;
        } catch (SQLException ex) {
            Logger.getLogger(VisitsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public City create(City entity) {
        // TODO exception
        return null;
    }

    @Override
    public City findById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM City WHERE idCity = ?");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            String idCity = resultSet.getString("idCity");
            String name = resultSet.getString("name");
            String countryCode = resultSet.getString("fk_countryCode");
            // Get country from resulting id
            Country country = countries.findById(countryCode);

            return new City(Integer.parseInt(idCity), name, country);
        } catch (SQLException ex) {
            Logger.getLogger(CitiesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void update(City entity) {
        // TODO exception
    }

    @Override
    public void deleteById(Integer id) {
        // TODO exception
    }
}
