package org.example.labs.dao;

import org.example.labs.domain.Country;
import org.example.labs.domain.Region;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CountryDbDAO implements RepositoryDAO<Country> {
    public CountryDbDAO(){};

    private static final String select_all_country = "SELECT id, fullName, shortName FROM country ORDER BY fullName ASC";

    private static final String select_country_ById = "SELECT id, fullName, shortName FROM country WHERE id = ?";

    private static final String insert_country = "INSERT INTO country(fullName, shortName) VALUES(?, ?)";

    private static final String edit_country = "UPDATE country SET fullName = ?, shortName = ? WHERE id = ?";

    private static final String delete_country = "DELETE FROM country WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException{
        return builder.getConnection();
    }


    @Override
    public Long insert(Country o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(insert_country, new String[]{"id"})) {
            Long Id = -1L;
            pst.setString(1, o.getFullName());
            pst.setString(2, o.getShortName());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if(gk.next()){
                Id = gk.getLong("id");
            }
            gk.close();
            return Id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Country o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_country)) {
            pst.setString(1, o.getFullName());
            pst.setString(2, o.getShortName());
            pst.setLong(3, o.getId());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public void delete(Country o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_country)) {
            pst.setLong(1, o.getId());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    /*@Override
    public Country findById(Long id) throws SQLDataException {
        Country country = null;
        try(Connection con = getConnection()){
            PreparedStatement pst = con.prepareStatement(select_country_ById);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                country = fillCountry(rs);
            }
            rs.close();
            pst.close();
        }catch (Exception e){
            throw new SQLDataException();
        }
        return country;
    }*/

    @Override
    public Country findById(Long id) throws SQLDataException {
        Country country = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_country_ById)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    country = fillCountry(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Логирование исключения для выявления причины
            throw new SQLDataException("Error occurred while fetching country by ID");
        }
        return country;
    }

    @Override
    public List<Country> findAll() throws SQLDataException {
        /*List<Country> list = new LinkedList<>();
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(select_all_country);  ResultSet rs = pst.executeQuery()) {
           while (rs.next()){
               list.add(fillCountry(rs));
           }
           rs.close();
        }catch (Exception e){
            throw new SQLDataException();
        }
        return list;*/
        List<Country> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_all_country);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillCountry(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            throw new SQLDataException("Error occurred while fetching all countries");
        }
        return list;
    }

    private Country fillCountry(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getLong("id"));
        country.setFullName(rs.getString("fullName"));
        country.setShortName(rs.getString("shortName"));
        return country;
    }

    public Country FindById(Long id, List<Country> countries) {
        if (countries != null) {
            for (Country c: countries){
                if((c.getId()).equals(id)){
                    return c;
                }
            }
        }else{
            return null;
        }
        return null;
    }
}
