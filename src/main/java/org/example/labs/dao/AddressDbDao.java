package org.example.labs.dao;

import org.example.labs.domain.Address;
import org.example.labs.domain.City;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AddressDbDao implements RepositoryDAO<Address> {

    private static final String select_all_address = "SELECT id, person, street, building, office, cityId FROM address ORDER BY person ASC";

    private static final String select_address_ById = "SELECT id, person, street, building, office, cityId FROM address WHERE id = ?";

    private static final String insert_address = "INSERT INTO address(person, street, building, office, cityId) VALUES(?, ?, ?, ?, ?)";

    private static final String edit_address = "UPDATE address SET person = ?, street = ?, building = ?, office = ?, cityId = ? WHERE id = ?";

    private static final String delete_address = "DELETE FROM address WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Address o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(insert_address, new String[]{"id"})) {
            Long Id = -1L;
            pst.setString(1, o.getPerson());
            pst.setString(2, o.getStreet());
            pst.setInt(3, o.getBuilding());
            pst.setInt(4, o.getOffice());
            pst.setLong(5, o.getCityId());
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
    public void update(Address o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_address)) {
            pst.setString(1, o.getPerson());
            pst.setString(2, o.getStreet());
            pst.setInt(2, o.getBuilding());
            pst.setInt(2, o.getOffice());
            pst.setLong(2, o.getCityId());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public void delete(Address o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_address)) {
            pst.setLong(1, o.getId());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public Address findById(Long id) throws SQLDataException {
        Address address = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_address_ById)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    address = fillAddress(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Логирование исключения для выявления причины
            throw new SQLDataException("Error occurred while fetching country by ID");
        }
        return address;
    }

    @Override
    public List<Address> findAll() throws SQLDataException {
        List<Address> list = new LinkedList<>();
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(select_all_address);  ResultSet rs = pst.executeQuery()) {
            while (rs.next()){
                list.add(fillAddress(rs));
            }
            rs.close();
        }catch (Exception e){
            throw new SQLDataException();
        }
        return list;
    }

    private Address fillAddress(ResultSet rs) throws SQLException {
        Long idCity = rs.getLong("cityId");
        Address address = new Address();
        address.setId(rs.getLong("id"));
        address.setPerson(rs.getString("person"));
        address.setStreet(rs.getString("street"));
        address.setBuilding(rs.getInt("building"));
        address.setOffice(rs.getInt("office"));
        address.setCityId(idCity);
        return address;
    }
}
