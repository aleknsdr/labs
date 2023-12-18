package org.example.labs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.labs.dao.CountryDbDAO;
import org.example.labs.dao.RegionDbDao;
import org.example.labs.domain.Country;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "countryServlet", value = "/country-servlet")
public class CountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CountryDbDAO countryDbDAO = new CountryDbDAO();

    public CountryServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            List<Country> countryList = new ArrayList<>();
            countryList.addAll(countryDbDAO.findAll());
            request.setAttribute("countries", countryList);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        String userPath = request.getServletPath();
        if("/country-servlet".equals(userPath)){
            request.getRequestDispatcher("/views/country.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CountryDbDAO countryDbDAO = new CountryDbDAO();

        String fullName = request.getParameter("inputFullName");
        String shortName = request.getParameter("inputShortName");

        Country newCountry = new Country(fullName, shortName);

        try {
            Long index = countryDbDAO.insert(newCountry);
        } catch (SQLException e){
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
