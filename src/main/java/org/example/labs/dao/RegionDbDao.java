package org.example.labs.dao;

import org.example.labs.domain.Country;
import org.example.labs.domain.Region;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RegionDbDao implements RepositoryDAO<Region>{
    private static final String select_all_region = "SELECT id, nameRegion, countryId FROM region ORDER BY nameRegion ASC";

    private static final String select_region_ById = "SELECT id, nameRegion, countryId FROM region WHERE id = ?";

    private static final String insert_region = "INSERT INTO region(nameRegion, countryId) VALUES(?, ?)";

    private static final String edit_region = "UPDATE region SET nameRegion = ?, countryId = ? WHERE id = ?";

    private static final String delete_region = "DELETE FROM region WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    @Override
    public Long insert(Region o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(insert_region, new String[]{"id"})) {
            Long Id = -1L;
            pst.setString(1, o.getNameRegion());
            pst.setLong(2, o.getCountryId());
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
    public void update(Region o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_region)) {
            pst.setString(1, o.getNameRegion());
            pst.setString(2, o.getCountry());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public void delete(Region o) throws SQLDataException {
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_region)) {
            pst.setLong(1, o.getId());
            pst.executeUpdate();
        }catch (Exception e){
            throw new SQLDataException();
        }
    }

    @Override
    public Region findById(Long id) throws SQLDataException {
        Region region = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_region_ById)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    region = fillRegion(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Логирование исключения для выявления причины
            throw new SQLDataException("Error occurred while fetching country by ID");
        }
        return region;
    }

    @Override
    public List<Region> findAll() throws SQLDataException {
        List<Region> list = new LinkedList<>();
        try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(select_all_region);  ResultSet rs = pst.executeQuery()) {
            while (rs.next()){
                list.add(fillRegion(rs));
            }
            rs.close();
        }catch (Exception e){
            throw new SQLDataException();
        }
        return list;
    }

    private Region fillRegion(ResultSet rs) throws SQLException {
        Long idCountry = rs.getLong("countryId");
        Region region = new Region();
        region.setId(rs.getLong("id"));
        region.setNameRegion(rs.getString("nameRegion"));
        region.setCountryId(idCountry);
        return region;
    }
}
