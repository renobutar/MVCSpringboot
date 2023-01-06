package com.example.demo.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.models.Region;

public class RegionDao {
    //private static final Region Region = null;
    private Connection con;

    public RegionDao(Connection connection) {
        this.con = connection;
    }

    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        String query = "Select id, name from tb_m_region";
        try {
            ResultSet resultSet = con.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (Exception e) {
       
            e.printStackTrace();
        }
        return regions;
    }

    // public Region getById(Integer id) {
    //     String query = "Select id, name from tb_m_region Where id = ?";
    //     Region region = new Region();
    //     try {
    //         ResultSet resultSet = con.prepareStatement(query).executeQuery();
    //         while (resultSet.next()) {
    //             region.setId(resultSet.getInt(1));
    //             region.setName(resultSet.getString(2));
    //         }
    //     } catch (Exception e) {
       
    //         e.printStackTrace();
    //     }
    //     return region;
    // }
    public Region getById(Integer id) {
        String query = "Select id, name from tb_m_region Where id = ?";
        Region region = new Region();
        try {
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }

    public Boolean insertData(Region region){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("Insert INTO tb_m_region(name) values(?)");
            preparedStatement.setString(1, region.getName());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateData(Region region){
        try {
            String query = "Update tb_m_region SET id = ?, name = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setInt(3, region.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.getMessage();
        }
        return false;
    }

    public Boolean delete(Integer id){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("Delete from tb_m_region where id = ?");
            preparedStatement.setInt(1,id);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
