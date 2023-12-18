package org.example.labs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.labs.dao.CityDbDao;
import org.example.labs.dao.CountryDbDAO;
import org.example.labs.dao.RegionDbDao;
import org.example.labs.domain.City;
import org.example.labs.domain.Region;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "cityServlet", value = "/city-servlet")
public class CityServlet extends HttpServlet {
    private final RegionDbDao regionDbDao = new RegionDbDao();

    private final CityDbDao cityDbDao = new CityDbDao();
    public CityServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            List<City> cityList = new ArrayList<>();
            cityList.addAll(cityDbDao.findAll());
            for (City r: cityList){
                r.setRegion(regionDbDao.findById(r.getRegionId()));
            }
            request.setAttribute("cities", cityList);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        String userPath = request.getServletPath();
        if("/city-servlet".equals(userPath)){
            request.getRequestDispatcher("/views/city.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
