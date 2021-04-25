package com.certification.ocp.jdbc;

import java.sql.*;

public class StatementsAndResultSet {

    public static void main(String[] args) throws SQLException {

        /*
            The PreparedStatement interface is a subtype of the Statement interface, representing precompiled statements. It can be created via Connection::prepareStatement
                precompiled statement means that the database does not have to compile the query again as it is already compiled
            Parameters of a prepared statement are denoted by question mark placeholders and can be set by suitable setter methods such as setInt and setString
            In this example we will use an in-memory database called h2, check the pom.xml for its dependency
         */

        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            Statement statement = connection.createStatement();   // create a general statement
            ResultSet statementResultSet = statement.executeQuery("SELECT * FROM members WHERE name = 'John' AND age = 18");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM members WHERE name = ? AND age = ?");    // create a prepared statement
            preparedStatement.setString(1, "John");
            preparedStatement.setInt(2, 18);
            ResultSet preparedResultSet = preparedStatement.executeQuery();

            /*
                You can access data in a ResultSet object though a cursor, which is a pointer pointing to one row of data in the result set.
                    At beginning, the pointer points to the row before the first one
                The ResultSet::next method moves the cursor to the next row. This method returns false if the cursor is positioned after the last row and true otherwise.

                ResultSet, Statement and Connection are all JDBC resources, which can be closed by invoking the close method. If the connection is closed all the objects will closed as well.
             */
            // ResultSet preparedResultSet = preparedStatement.executeQuery("SELECT * FROM members"); is a valid instruction too as PreparedStatement inherits from Statement interface
            // but this generates a sql exception
        }catch (SQLException sqlException){
            // an exception will be generated as the table members does not exist
            System.out.print(sqlException.toString());
        }finally {
            if(connection!=null)
                connection.close(); // this will close all other Statements and ResultSets
        }
        System.out.printf("%n ----------------------------- %n");

        // Get data example
        connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        populateDatabase(connection);
        String query = "SELECT * FROM REGISTRATION";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("ID");            // same as , resultSet.getInt(1);
                String name = resultSet.getString("NAME");  // same as , resultSet.getString(2);
                System.out.printf("[ ID = %2d ; NAME : %-5s ]%n", id, name);
            }
        }finally {
            // statement and resultSet will be closed automatically
            if(connection!=null)
                connection.close();
        }
    }

    private static void populateDatabase(Connection connection) throws SQLException {

        connection.setAutoCommit(false);    // disable the auto-commit, we will commit after creating and populating the REGISTRATION table
        Statement statement = connection.createStatement();
        String sql = "DROP TABLE IF EXISTS REGISTRATION";
        statement.executeUpdate(sql);
        System.out.println("Drop table ...");
        sql = "CREATE TABLE REGISTRATION (id INTEGER not NULL, name VARCHAR(255), PRIMARY KEY ( id ))";
        statement.executeUpdate(sql);
        System.out.println("Create table ...");
        sql = "INSERT INTO REGISTRATION VALUES (1, 'Ali')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO REGISTRATION VALUES (2, 'Fatma')";
        statement.executeUpdate(sql);
        connection.commit();
        System.out.println("Inserted records into the table ...");
    }
}
