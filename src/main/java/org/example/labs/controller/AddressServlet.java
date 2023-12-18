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

    private final RegionDbDao regionDbDao = new RegionDbDao();

    public AddressServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            List<Address> addressList = new ArrayList<>();
            addressList.addAll(addressDbDao.findAll());
            List<City> cityList = new ArrayList<>();
            cityList.addAll(cityDbDao.findAll());
            for (City r: cityList){
                r.setRegion(regionDbDao.findById(r.getRegionId()));
            }
            for(Address a: addressList){
                a.setCity(cityDbDao.findById(a.getCityId()));
            }
            request.setAttribute("addresses", addressList);
            request.setAttribute("cities", cityList);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        String userPath = request.getServletPath();
        if("/address-servlet".equals(userPath)){
            request.getRequestDispatcher("/views/address.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressDbDao addressDbDao1 = new AddressDbDao();

        String person = request.getParameter("inputPerson");
        String street = request.getParameter("inputStreet");
        int building = Integer.parseInt(request.getParameter("inputBuilding"));
        int office = Integer.parseInt(request.getParameter("inputOffice"));

        String city = request.getParameter("city");

        int index1 = city.indexOf('=');
        int index2 = city.indexOf(",");
        String c1 = city.substring(index1+1, index2);
        Long idCity = Long.parseLong(c1.trim());

        try {
            Address address = new Address(person, street, building, office, cityDbDao.findById(idCity), idCity);
            Long index = addressDbDao1.insert(address);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        doGet(request, response);
    }
}
