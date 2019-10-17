package data;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

/**
 * Created by user on 18.02.2019.
 */
public class DAOaccess {
    public static void databaseAccess() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //   ResultSet rs;
        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String userName = "system";
        String password = "Kom3inator$";
        Connection con = null;



        String car_id;
        String car_name;

        ArrayList<String> carList = new ArrayList<String>();

        try {
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                    + con.getMetaData().getDatabaseProductName());
           Statement smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_UPDATABLE);
            System.out.println("sukces kurwa mać");


       // String sql = " select * from cars ";

            ResultSet rst = smt.executeQuery(" select * from  cars ");
         //  smt.executeUpdate("insert into cars (car_id, car_name)" +
         //        " values (4,'fiat')");
          //  rst = smt.getResultSet();

           while (rst.next()) {
                car_id = rst.getString("idcar");
                car_name = rst.getString("carmodel");
                carList.add("car id: " + car_id + "  car name: " + car_name + " ");
            }

            smt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(carList);



        }

    }




