package org.example.labs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.labs.dao.CityDbDao;
import org.example.labs.dao.CountryDbDAO;
import org.example.labs.dao.RegionDbDao;
import org.example.labs.domain.Country;
import org.example.labs.domain.Region;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "regionServlet", value = "/region-servlet")
public class RegionServlet extends HttpServlet {

    private final RegionDbDao regionDbDao = new RegionDbDao();

    private final CountryDbDAO countryDbDAO = new CountryDbDAO();

    public RegionServlet(){}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            List<Region> regionList = new ArrayList<>();
            regionList.addAll(regionDbDao.findAll());
            for (Region r: regionList){
                r.setCountry(countryDbDAO.findById(r.getCountryId()));
            }
            request.setAttribute("regions", regionList);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        String userPath = request.getServletPath();
        if("/region-servlet".equals(userPath)){
            request.getRequestDispatcher("/views/region.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
