package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.City;
import ch.heigvd.amt.citylog.model.Country;

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

    // TODO: Remove
    @Override
    public List<City> findAll() {
        List<City> cities = new LinkedList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM City LIMIT 1000000");
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
