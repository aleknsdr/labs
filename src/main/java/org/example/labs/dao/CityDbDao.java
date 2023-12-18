package org.example.labs.dao;

import org.example.labs.domain.City;
import org.example.labs.domain.Region;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CityDbDao implements RepositoryDAO<City>{
    private static final String select_all_city = "SELECT id, nameCity, regionId FROM city ORDER BY nameCity ASC";

    private static final String select_city_ById = "SELECT id, nameCity, regionId FROM city WHERE id = ?";

    private static final String insert_city = "INSERT INTO city(nameCity, regionId) VALUES(?, ?)";

    private static final String edit_city = "UPDATE city SET nameCity = ?, regionId = ? WHERE id = ?";

    private static final String delete_city = "DELETE FROM city WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(City o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(insert_city, new String[]{"id"})) {
            Long Id = -1L;
            pst.setString(1, o.getNameCity());
            pst.setLong(2, o.getRegionId());
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
    public void update(City o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_city)) {
            pst.setString(1, o.getNameCity());
            pst.setString(2, o.getRegion());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public void delete(City o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_city)) {
            pst.setLong(1, o.getId());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public City findById(Long id) throws SQLDataException {
        City city = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_city_ById)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    city = fillCity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Логирование исключения для выявления причины
            throw new SQLDataException("Error occurred while fetching country by ID");
        }
        return city;
    }

    @Override
    public List<City> findAll() throws SQLDataException {
        List<City> list = new LinkedList<>();
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(select_all_city);  ResultSet rs = pst.executeQuery()) {
            while (rs.next()){
                list.add(fillCity(rs));
            }
            rs.close();
        }catch (Exception e){
            throw new SQLDataException();
        }
        return list;
    }

    private City fillCity(ResultSet rs) throws SQLException {
        Long idRegion = rs.getLong("regionId");
        City city = new City();
        city.setId(rs.getLong("id"));
        city.setNameCity(rs.getString("nameCity"));
        city.setRegionId(idRegion);
        return city;
    }
}
