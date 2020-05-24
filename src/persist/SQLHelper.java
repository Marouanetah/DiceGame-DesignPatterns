package persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SQLHelper {

    private String adressServer;
    private String loginServer;
    private String passwordServer;
    private String database;
    private Statement streamConnexion;
    private ResultSet streamResponse;

    public SQLHelper(String adressServer, String loginServer, String passwordServer, String database) {

        this.adressServer = adressServer;
        this.loginServer = loginServer;
        this.passwordServer = passwordServer;
        this.database = database;

        this.streamResponse = null;
    }

    public boolean connexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            return false;
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + this.adressServer + "/" + this.database, this.loginServer, this.passwordServer);
            this.streamConnexion = con.createStatement();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to database");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean executeRequest(String sqlRequest) {
        try {
            if (sqlRequest.substring(0, 6).equalsIgnoreCase("SELECT")) {
                this.streamResponse = this.streamConnexion.executeQuery(sqlRequest);
            } else {
                this.streamConnexion.executeUpdate(sqlRequest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public int nbResponse() {
        ResultSet resultSet = this.streamResponse;
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
        } catch (SQLException e) {
            return 0;
        }
        return size;
    }

    public ResultSet fetchArray() {
        try {
            if (this.streamResponse.next()) {
                return this.streamResponse;
            } else {
                return null;
            }
        } catch (SQLException ex) {
                ex.printStackTrace();
            return null;
        }
    }

    public void close() {

        try {
            if (this.streamResponse != null) {
                this.streamResponse.close();
            }
            this.streamResponse.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
