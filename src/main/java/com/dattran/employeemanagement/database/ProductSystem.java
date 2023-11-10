package com.dattran.employeemanagement.database;

import com.dattran.employeemanagement.model.ProductSystemObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSystem {
    private Connection connection;
    private ConnectionPool connectionPool;
    public ProductSystem() {
        this.connectionPool = new ConnectionPoolImpl();
        try {
            this.connection = this.connectionPool.getConnection("Product System");
            if(this.connection.getAutoCommit()) {
                this.connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public ObservableList<ProductSystemObject> getAllProducts() {
        ObservableList<ProductSystemObject> listAll = FXCollections.observableArrayList();
        String SQL = "Select * from tblps ORDER BY ps_id ASC";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProductSystemObject product = new ProductSystemObject();
                    product.setProductSystem_id(rs.getInt("ps_id"));
                    product.setProductSystem_enable(rs.getInt("ps_enable"));
                    product.setProductSystem_language(rs.getInt("ps_language"));
                    product.setProductSystem_manager_id(rs.getInt("ps_manager_id"));
                    product.setProductSystem_name(rs.getString("ps_name"));
                    product.setProductSystem_notes(rs.getString("ps_notes"));
                    product.setProductSystem_delete(rs.getInt("ps_delete"));
                    product.setProductSystem_created_date(rs.getString("ps_created_date"));
                    product.setProductSystem_modified_date(rs.getString("ps_modified_date"));
                    product.setProductSystem_deleted_date(rs.getString("ps_deleted_date"));
                    product.setProductSystem_deleted_author(rs.getString("ps_deleted_author"));
                    product.setProductSystem_table(rs.getString("ps_table"));
                    product.setProductSystem_name_en(rs.getString("ps_name_en"));
                    product.setProductSystem_created_author_id(rs.getInt("ps_created_author_id"));
                    listAll.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAll;
    }
    public List<ProductSystemObject> getAllProductsByList() {
        List<ProductSystemObject> listAll = new ArrayList<>();
        String SQL = "Select * from tblps ORDER BY ps_id ASC";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProductSystemObject product = new ProductSystemObject();
                    product.setProductSystem_id(rs.getInt("ps_id"));
                    product.setProductSystem_enable(rs.getInt("ps_enable"));
                    product.setProductSystem_language(rs.getInt("ps_language"));
                    product.setProductSystem_manager_id(rs.getInt("ps_manager_id"));
                    product.setProductSystem_name(rs.getString("ps_name"));
                    product.setProductSystem_notes(rs.getString("ps_notes"));
                    product.setProductSystem_delete(rs.getInt("ps_delete"));
                    product.setProductSystem_created_date(rs.getString("ps_created_date"));
                    product.setProductSystem_modified_date(rs.getString("ps_modified_date"));
                    product.setProductSystem_deleted_date(rs.getString("ps_deleted_date"));
                    product.setProductSystem_deleted_author(rs.getString("ps_deleted_author"));
                    product.setProductSystem_table(rs.getString("ps_table"));
                    product.setProductSystem_name_en(rs.getString("ps_name_en"));
                    product.setProductSystem_created_author_id(rs.getInt("ps_created_author_id"));
                    listAll.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAll;
    }
    public void deleteProduct(ProductSystemObject product) {
        String SQL = "DELETE FROM tblps WHERE ps_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, product.getProductSystem_id());
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                this.connection.rollback();
            }
            this.connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ProductSystemObject getProductById(int id) {
        String SQL = "SELECT * FROM tblps WHERE ps_id = ?";
        ProductSystemObject product;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    product = new ProductSystemObject();
                    product.setProductSystem_id(rs.getInt("ps_id"));
                    product.setProductSystem_enable(rs.getInt("ps_enable"));
                    product.setProductSystem_language(rs.getInt("ps_language"));
                    product.setProductSystem_manager_id(rs.getInt("ps_manager_id"));
                    product.setProductSystem_name(rs.getString("ps_name"));
                    product.setProductSystem_notes(rs.getString("ps_notes"));
                    product.setProductSystem_delete(rs.getInt("ps_delete"));
                    product.setProductSystem_created_date(rs.getString("ps_created_date"));
                    product.setProductSystem_modified_date(rs.getString("ps_modified_date"));
                    product.setProductSystem_deleted_date(rs.getString("ps_deleted_date"));
                    product.setProductSystem_deleted_author(rs.getString("ps_deleted_author"));
                    product.setProductSystem_table(rs.getString("ps_table"));
                    product.setProductSystem_name_en(rs.getString("ps_name_en"));
                    product.setProductSystem_created_author_id(rs.getInt("ps_created_author_id"));
                    return product;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addProduct(ProductSystemObject product) {
        StringBuilder SQL = new StringBuilder();
        SQL.append("INSERT INTO tblps(");
        SQL.append("ps_name, ps_manager_id, ps_notes, ps_delete, ps_created_date, ps_modified_date, ps_deleted_date, ps_deleted_author, ps_table, ps_enable, ps_name_en, ps_created_author_id, ps_language)");
        SQL.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL.toString());
            preparedStatement.setString(1, product.getProductSystem_name());
            preparedStatement.setInt(2, product.getProductSystem_manager_id());
            preparedStatement.setString(3, product.getProductSystem_notes());
            preparedStatement.setInt(4, product.getProductSystem_delete());
            preparedStatement.setString(5, product.getProductSystem_created_date());
            preparedStatement.setString(6, product.getProductSystem_modified_date());
            preparedStatement.setString(7, product.getProductSystem_deleted_date());
            preparedStatement.setString(8, product.getProductSystem_deleted_author());
            preparedStatement.setString(9, product.getProductSystem_table());
            preparedStatement.setInt(10, product.getProductSystem_enable());
            preparedStatement.setString(11, product.getProductSystem_name_en());
            preparedStatement.setInt(12, product.getProductSystem_created_author_id());
            preparedStatement.setInt(13, product.getProductSystem_language());
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                this.connection.rollback();
                return false;
            }
            this.connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean updateProduct(ProductSystemObject product) {
        StringBuilder SQL = new StringBuilder();
        SQL.append("UPDATE  tblps SET ");
        SQL.append("ps_name = ?, ps_manager_id = ?, ps_notes = ?, ps_delete =?, ps_created_date=?, ps_modified_date=?, ps_deleted_date=?, ps_deleted_author=?, ps_table=?, ps_enable=?, ps_name_en=?, ps_created_author_id=?, ps_language=? ");
        SQL.append("WHERE ps_id = ?");
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL.toString());
            preparedStatement.setString(1, product.getProductSystem_name());
            preparedStatement.setInt(2, product.getProductSystem_manager_id());
            preparedStatement.setString(3, product.getProductSystem_notes());
            preparedStatement.setInt(4, product.getProductSystem_delete());
            preparedStatement.setString(5, product.getProductSystem_created_date());
            preparedStatement.setString(6, product.getProductSystem_modified_date());
            preparedStatement.setString(7, product.getProductSystem_deleted_date());
            preparedStatement.setString(8, product.getProductSystem_deleted_author());
            preparedStatement.setString(9, product.getProductSystem_table());
            preparedStatement.setInt(10, product.getProductSystem_enable());
            preparedStatement.setString(11, product.getProductSystem_name_en());
            preparedStatement.setInt(12, product.getProductSystem_created_author_id());
            preparedStatement.setInt(13, product.getProductSystem_language());
            preparedStatement.setInt(14, product.getProductSystem_id());
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                this.connection.rollback();
                return false;
            }
            this.connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static void main(String[] args) {
        ProductSystem productSystem = new ProductSystem();
        ProductSystemObject product = productSystem.getProductById(5);
        product.setProductSystem_name("Iphone 10");
        productSystem.updateProduct(product);
//        ObservableList<ProductSystemObject> list = productSystem.getAllProducts();
//        System.out.println(list.size());
    }
}
