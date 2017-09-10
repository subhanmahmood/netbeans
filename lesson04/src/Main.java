/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author subha
 */
public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String ConnectionUrl = "jdbc:mysql://localhost:3306/test?" + "user=root&password=subhan";
            Connection con = DriverManager.getConnection(ConnectionUrl);
            Statement stmt = null;
            ResultSet rs = null;

            String SQL = "SELECT * FROM tblpizza";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("PizzaType") + ":" + rs.getString("Cost"));
            }
            
            String queryInsert = "INSERT INTO tblpizza (PizzaType, Toppings, Cost, SpecialInfo)" + " VALUES ('Special', 'Pineapple', 10.50, 0)";
            CRUDRow(queryInsert, stmt);
            
            String queryUpdate = "UPDATE tblpizza SET Cost = 12.50, SpecialInfo = 'Gluten Free' WHERE PizzaType = 'Special'";
            CRUDRow(queryUpdate, stmt);
            
            String queryDelete = "DELETE FROM tblpizza WHERE PizzaType = 'Margherita'";
            CRUDRow(queryDelete, stmt);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception:" + e.toString());
        }

    }
    
    public static void CRUDRow(String strSQL, Statement stmt){
        try{
           int rowsEffected = stmt.executeUpdate(strSQL); 
           System.out.println(rowsEffected + " rows effected");
        }catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        }
    }
}