package sample;

import java.sql.*;

public class DB {
    private static volatile DB instance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private DB(){}
    public Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection(Config.urlDB, Config.userNameDB, Config.passwordDB);
                System.out.println("connection successful");
            } catch (SQLException | ClassNotFoundException exp) {
                System.err.println(exp.getMessage());
            }
        }
        return connection;
    }

    public static DB getInstance() {
        if (instance == null) {
            synchronized (DB.class) {
                if (instance == null) {
                    instance = new DB();
                }
            }
        }
        return instance;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException exp) {
            System.err.println(exp.getMessage());
        }
        return resultSet;
    }

    public boolean executeUpdate(String sql) throws SQLException {
        getConnection();
        boolean result = false;
        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(sql) > 0) {
                System.out.println("great");
                result = true;
            } else {
                System.out.println("Ничего не изменилось!");
            }
        } catch (SQLException exp) {
            System.err.println(exp.getMessage());
        } finally {
            this.close();
        }
        return result;
    }

    public void close() throws SQLException {
        try {

            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            } else {
                System.err.println("resultSet пустой!");
            }

            if (statement != null) {
                statement.close();
                statement = null;
            } else {
                System.err.println("statement пустой!");
            }


            if (connection != null) {
                connection.close();
                connection = null;
            } else {
                System.err.println("connection пустой!");
            }
        } catch (SQLException exp) {
            System.err.println(exp.getMessage());
        }
    }

}
