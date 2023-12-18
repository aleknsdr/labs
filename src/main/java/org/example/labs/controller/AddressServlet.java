package org.example.labs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.labs.dao.AddressDbDao;
import org.example.labs.dao.CityDbDao;
import org.example.labs.dao.CountryDbDAO;
import org.example.labs.dao.RegionDbDao;
import org.example.labs.domain.Address;
import org.example.labs.domain.City;
import org.example.labs.domain.Country;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "addressServlet", value = "/address-servlet")
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AddressDbDao addressDbDao = new AddressDbDao();

    private final CityDbDao cityDbDao = new CityDbDao();

    public AddressServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            List<Address> addressList = new ArrayList<>();
            addressList.addAll(addressDbDao.findAll());
            for(Address a: addressList){
                a.setCity(cityDbDao.findById(a.getCityId()));
            }
            request.setAttribute("addresses", addressList);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        String userPath = request.getServletPath();
        if("/address-servlet".equals(userPath)){
            request.getRequestDispatcher("/views/address.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
