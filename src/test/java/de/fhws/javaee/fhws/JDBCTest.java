/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Matthias Reining
 */
@Ignore
public class JDBCTest {

    String driver = "org.h2.Driver";
    //example embedded / server
    String url = "jdbc:h2:tcp://localhost/D:/fhws/servers/database/fhws-db";

    String username = "sa";
    String password = "sa";

    @Test
    public void shouldConnectToDatabase() throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        assertTrue(connection.isValid(3));
    }

    @Test
    public void shouldReadData() throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " - " + rs.getString(2));
        }

        rs.close();
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldInsertData() throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        int count = stmt.executeUpdate("INSERT INTO test(data1, data2) Values('Hi3', 'all3')");
        System.out.println("count: " + count);

        //System.out.println( count / 0);
        String email = "Mickey Mouse";

        //böeses Beispiel für SQL Injection
        //email = "', '...'); DELETE FROM test; INSERT INTO test(data1, data2) Values('Hi ";
        stmt.executeUpdate("INSERT INTO test(data1, data2) Values('Hi " + email + "', 'all3')");

        connection.commit();

        //rs.close();
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldInsertDataInCorrectWay() throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO test(data1, data2) Values(?, ?)");
        stmt.setString(1, "Hallo");
        stmt.setString(2, "FH-WS");
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Test
    public void shouldReadMeta() throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        Statement stmt = connection.createStatement();

        ResultSet rs = connection.getMetaData().getColumns(null, null, "TEST", null);
        System.out.println("MetaInfo Data:");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + rs.getString(6) + " - " + rs.getString(7));
        }

        rs.close();
        stmt.close();
        connection.close();
    }
}
