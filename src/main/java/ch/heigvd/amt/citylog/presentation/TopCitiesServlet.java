package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.CitiesDAO;
import ch.heigvd.amt.citylog.model.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Pair;

import javax.ejb.EJB;
import javax.json.JsonObject;
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
//        req.setAttribute("cities", cities.findAllByPopularity(0, 10, null));
        req.getRequestDispatcher("/WEB-INF/pages/cities.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int start = Integer.parseInt(req.getParameter("start"));
        int nbrOfElements = Integer.parseInt(req.getParameter("length"));
        String search = req.getParameter("search[value]");

        List<Pair<City, Integer>> citiesPop = cities.findAllByPopularity(start, nbrOfElements, search);
//        int recordsTotal = moviesManager.getNumberOfMovies();

        // Create the JSON to send to the datatable
        JSONArray response = new JSONArray();
//        response.put("recordsTotal", recordsTotal);
//        response.put("recordsFiltered", recordsTotal);

        // Create JSON payload
        for (Pair<City, Integer> cityPop : citiesPop) {
            JSONObject element = new JSONObject();
            element.put("id", cityPop.getFirst().getId());
            element.put("name", cityPop.getFirst().getName());

            response.add(element);
        }

        System.out.println(response.toJSONString().getBytes());

        // Send JSON data to datatable
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = res.getOutputStream();
        out.write(response.toJSONString().getBytes());
        out.flush();
    }
}
