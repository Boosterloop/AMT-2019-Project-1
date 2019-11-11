package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.CitiesDAO;
import ch.heigvd.amt.citylog.model.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Pair;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * List of most visited cities servlet
 *
 * @author Luc Wachter, Alison Savary
 */
public class TopCitiesServlet extends HttpServlet {
    @EJB
    private CitiesDAO cities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/cities.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int start = Integer.parseInt(req.getParameter("start"));
        int nbrOfElements = Integer.parseInt(req.getParameter("length"));
        String search = req.getParameter("search[value]");

        List<Pair<City, Integer>> citiesPop = cities.findAllByPopularity(start, nbrOfElements, search);
        int recordsTotal = cities.getNumberOfCities();

        // Create the JSON to send to the datatable
        JSONObject response = new JSONObject();
//        response.put("draw", 1);
        response.put("recordsTotal", recordsTotal);
        response.put("recordsFiltered", recordsTotal);

        JSONArray data = new JSONArray();

        // Create JSON payload
        for (Pair<City, Integer> cityPop : citiesPop) {
            JSONObject element = new JSONObject();
            element.put("name", cityPop.getFirst().getName());
            element.put("country", cityPop.getFirst().getCountry().getName());
            element.put("count", cityPop.getSecond());

            data.add(element);
        }

        response.put("data", data);

        // Send JSON data to datatable
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = res.getOutputStream();
        out.write(response.toJSONString().getBytes());
        out.flush();
    }
}
