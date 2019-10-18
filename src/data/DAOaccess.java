package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

import model.Person;
import oracle.jdbc.driver.OracleDriver;

/**
 * Created by user on 18.02.2019.
 */
public class DAOaccess {

    private Connection conn = null;
    private Statement stm = null;


    public Connection getConnectionAndAccess() throws SQLException {
        Connection con = null;
        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String userName = "system";
        String password = "Kom3inator$";

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }


        return conn;
    }


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


    public Statement getStatement() {

        try {
             stm = getConnectionAndAccess().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println("sukces kurwa mać");
        } catch (SQLException sqlExcp) {
            System.out.println("nie udało sie nawiązać połączenia");
        }

        return stm;
    }

    public List<Person> getPersons() throws SQLException {
        List<Person>  persons = new ArrayList<>();
        try {
          //  conn = getConnectionAndAccess();
            stm = getStatement();


        int personid;
        String personName;
        String personLastname;
        String personRoom;
        String personStartHours;
        String personFinishHours;

            //code
        ResultSet rst = stm.executeQuery(" select * from  persons ");
        //  smt.executeUpdate("insert into cars (car_id, car_name)" +
        //        " values (4,'fiat')");
        //  rst = smt.getResultSet();

        while (rst.next()) {
            personid = rst.getInt("personid");
            personName = rst.getString("personname");
            personLastname = rst.getString("personlastname");
            personRoom = rst.getString("personroom");
            personStartHours= rst.getString("starthours");
            personFinishHours = rst.getString("finishhours");
            persons.add(
                    new Person(personid, personName,personLastname,personRoom,personStartHours,personFinishHours));

        }
        System.out.println(persons);

          stm.close();
           conn.close();
        }catch(SQLException exc){System.err.print("dupsko");}
        return persons;
    }
    public void saveToDB(List<Person> personList){
        stm = getStatement();


        try {


            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into persons(personid,personname,personlastname,personroom,starthours,finishhours) values (?,?,?,?,?,?)");
             for(Person person: personList){
                 System.out.println(person.toString());

                 prepStmt.setInt(1,person.getPersonid());
                 prepStmt.setString(2, person.getFirstName());
                 prepStmt.setString(3, person.getLastName());
                 prepStmt.setString(4, person.getRoom());
                 prepStmt.setString(5, person.getStartHour());
                 prepStmt.setString(6, person.getfinishHour());
                 prepStmt.execute();
             }

        }catch (SQLException e) {
            System.out.println("failed to save to database");
        }
    }

    public int   getlastid(){
       stm = getStatement();
        int  lastId = 0;
        try {
            ResultSet rst = stm.executeQuery(" select max(personid) from  persons ");

            while (rst.next()){
                lastId = rst.getInt("max(personid)");
            }
        }catch(SQLException e){}
        return lastId;
    }
}




