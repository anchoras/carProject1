import java.sql.*;

public class PostgreSQLConnUtils {

    private static String url;
    private static String dbName;
    private static String user;
    private static String password;
    private static Connection conn;

    static {
        url = "jdbc:postgresql://localhost:5433/";
        dbName = "carPark1";
        user = "postgres";
        password = "password";
        conn = null;
    }

    public PostgreSQLConnUtils() {
        url = "jdbc:postgresql://localhost:5433/";
        dbName = "carPark1";
        user = "postgres";
        password = "password";
        conn = null;
    }

    public PostgreSQLConnUtils(String url, String dbName, String user, String password) {
        this.url = url;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.conn = null;
    }

    public PostgreSQLConnUtils(PostgreSQLConnUtils postgreSQLConn) {
        this.url = postgreSQLConn.url;
        this.dbName = postgreSQLConn.dbName;
        this.user = postgreSQLConn.user;
        this.password = postgreSQLConn.password;
        this.conn = postgreSQLConn.conn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConn() {
        return conn;
    }

    public static Connection getConnection() {

        try {
            if ((conn == null) || (conn.isClosed())) {
                conn = DriverManager.getConnection(url + dbName, user, password);

                if (conn != null) {
                    System.out.println("Connected to the database!");
                } else {
                    System.out.println("Failed to make connection!");
                }
            }  else {
                System.out.println("Returning existing connection");
                return  conn;
            }
        } catch (SQLException e) {
            System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return conn;
    }

    public static Connection getConnection(String fullURL, String user,  String password) {

        try {
            if ((conn == null) || (conn.isClosed())) {
                conn = DriverManager.getConnection(url + dbName, user, password);

                if (conn != null) {
                    System.out.println("Connected to the database!");
                } else {
                    System.out.println("Failed to make connection!");
                }
            } else {
                System.out.println("Returning existing connection");
                return  conn;
            }
        } catch (SQLException e) {
            System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return conn;
    }

    public static String getConnName() {

        String toReturn = null;
        try {
            if ((conn == null) || (conn.isClosed())) {
                conn = DriverManager.getConnection(url + dbName, user, password);

                if (conn != null) {
                    toReturn = "Connection to the DB \"" + dbName + "\""
                            + " by user " + user;
                } else {
                    toReturn = "Connection wasn't created";
                    System.out.println("Failed to make connection!");
                }
            } else {
                toReturn = "Connection to the DB \"" + dbName + "\""
                        + " by user " + user;
            }
        } catch (SQLException e) {
            System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return toReturn;
    }


    public static void closeConnection() {

        try {
            if (conn == null) {
                System.out.println("Connection doesn't exist");
            } else if (conn.isClosed()) {
                System.out.println("Connection is closed already");
            } else {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}