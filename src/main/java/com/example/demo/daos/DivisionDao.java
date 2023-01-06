package com.example.demo.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.models.Division;
import com.example.demo.models.Region;
public class DivisionDao { 
    private Connection con;

    public DivisionDao(Connection connection) {
        this.con = connection;
    }

    public List<Division> getAll() {
        List<Division> divisions = new ArrayList<>();
        String query = "Select d.id, d.name, r.name from tb_m_division d join tb_m_region r on d.regionId = r.id";
        try {
            ResultSet resultSet = con.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                Region region = new Region();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(region);
                // region.setName(resultSet.getString(3));
                region.setName(resultSet.getString(4));
                divisions.add(division);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public Division getById(Integer id){
        Division division = new Division();
        String query = "SELECT * FROM tb_m_division WHERE id = ?";
        try {
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }

    public boolean insertData(Division division){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("Insert INTO tb_m_division(id, name) values(?,?)");
            preparedStatement.setInt(1, division.getId());
            preparedStatement.setString(2, division.getName());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateData(Division division){
        try {
            String query = "Update tb_m_division SET id = ?, name = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, division.getId());
            preparedStatement.setString(2, division.getName());
            preparedStatement.setInt(3, division.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Division division){
        try {
            String query = "Delete from tb_m_division where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, division.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
