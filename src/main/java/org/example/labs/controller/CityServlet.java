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

    private final CountryDbDAO countryDbDAO = new CountryDbDAO();
    public CityServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            List<City> cityList = new ArrayList<>();
            cityList.addAll(cityDbDao.findAll());
            List<Region> regionList = new ArrayList<>();
            regionList.addAll(regionDbDao.findAll());
            for (City r: cityList){
                r.setRegion(regionDbDao.findById(r.getRegionId()));
            }
            for (Region r: regionList){
                r.setCountry(countryDbDAO.findById(r.getCountryId()));
            }
            request.setAttribute("cities", cityList);
            request.setAttribute("regions", regionList);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        String userPath = request.getServletPath();
        if("/city-servlet".equals(userPath)){
            request.getRequestDispatcher("/views/city.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDbDao cityDbDao1 = new CityDbDao();

        String nameRegion = request.getParameter("inputNameCity");

        String region = request.getParameter("region");

        int index1 = region.indexOf('=');
        int index2 = region.indexOf(",");
        String c1 = region.substring(index1+1, index2);
        Long idRegion = Long.parseLong(c1.trim());

        try {
            City city = new City(nameRegion, regionDbDao.findById(idRegion), idRegion);
            Long index = cityDbDao1.insert(city);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        doGet(request, response);
    }

}
