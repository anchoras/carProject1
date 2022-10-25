import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PowerUnitDAO extends AbstractDAO<PowerUnit> {
    private Connection conn;
    private Statement stmnt;
    private PreparedStatement prepstmnt;
    private String sqlquery;
    private ResultSet rset;
    private PostgreSQLConnUtils psqlUtils = new PostgreSQLConnUtils();

    @Override
    public HashMap<Integer, PowerUnit> findAll() throws SQLException {
        conn = psqlUtils.getConnection();
        sqlquery = "SELECT * FROM \"PowerUnit\"";
        stmnt = conn.createStatement();
        rset = stmnt.executeQuery(sqlquery);
        HashMap<Integer, PowerUnit> punits = new HashMap<Integer, PowerUnit>();
        while (rset.next()) {
            PowerUnit punit = new PowerUnit();
            int id = rset.getInt(1);
            punit.setType(String.valueOf(rset.getInt(2)));
            punit.setName(rset.getString(3));
            punit.setMaxcapacity(rset.getDouble(4));
            punits.put(id, punit);
        }
        conn.close();
        return punits;
    }

    @Override
    public void delete(PowerUnit powerUnit) throws SQLException {
        conn = psqlUtils.getConnection();
        sqlquery = "DELETE FROM \"PowerUnit\" " +
                "WHERE name=? AND maxcapacity=?";
        prepstmnt = conn.prepareStatement(sqlquery);
        prepstmnt.setString(1, powerUnit.getName());
        prepstmnt.setDouble(2, powerUnit.getMaxcapacity());
        rset = prepstmnt.executeQuery();
        while (rset.next()) {
            System.out.println("Deleted rows(?): " + rset.getRow());
        }
        conn.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        conn = psqlUtils.getConnection();
        sqlquery = "DELETE FROM \"PowerUnit\" " +
                "WHERE id=?";
        prepstmnt = conn.prepareStatement(sqlquery);
        prepstmnt.setInt(1, id);
        rset = prepstmnt.executeQuery();
        if (!rset.next()) {
            System.out.println("Something went wrong; power unit was not deleted by id...");
        }
        conn.close();
    }

    @Override
    public void add(PowerUnit punit) throws SQLException {
        conn = psqlUtils.getConnection();
        conn.setAutoCommit(false);
        sqlquery = "SELECT id FROM \"PowerUnitTypes\" " +
                "WHERE name=?";
        prepstmnt = conn.prepareStatement(sqlquery);
        prepstmnt.setString(1, punit.getType());
        rset = prepstmnt.executeQuery();
        int typeId = rset.getInt(1);
        sqlquery = "INSERT INTO \"PowerUnit\" (type, name, maxcapacity) " +
                "values (?, ?, ?)";
        prepstmnt.setInt(1, typeId);
        prepstmnt.setString(2, punit.getName());
        prepstmnt.setDouble(3, punit.getMaxcapacity());
        rset = prepstmnt.executeQuery();
        conn.commit();
        if (!rset.next()) {
            System.out.println("Something went wrong; power unit was not added ...");
        }
        conn.setAutoCommit(true);
        conn.close();
    }

    @Override
    public void update(PowerUnit oldPunit, PowerUnit newPunit) throws SQLException{
        System.out.println("mock Power unit update");   //TODO release update(PU, PU) for PowerUnitDAO
    }

    @Override
    public void update(PowerUnit punit) throws SQLException{
        System.out.println("mock Power Unit update");   //TODO release  update(PU) for PowerUnitDAO
    }

    @Override
    public void update(int id, PowerUnit newPunit) throws SQLException {
        conn = psqlUtils.getConnection();
        conn.setAutoCommit(false);
        sqlquery = "SELECT id FROM \"PowerUnitTypes\" " +
                "WHERE name=?";
        prepstmnt = conn.prepareStatement(sqlquery);
        prepstmnt.setString(1, newPunit.getType());
        rset = prepstmnt.executeQuery();
        int typeId = rset.getInt(1);
        sqlquery = "UPDATE \"PowerUnit\" (type, name, maxcapacity) " +
                "values (?, ?, ?) " +
                "WHERE id=?";
        prepstmnt.setInt(1, typeId);
        prepstmnt.setString(2, newPunit.getName());
        prepstmnt.setDouble(3, newPunit.getMaxcapacity());
        prepstmnt.setInt(4, id);
        rset = prepstmnt.executeQuery();
        conn.commit();
        if (!rset.next()) {
            System.out.println("Something went wrong; power unit was not added ...");
        }
        conn.setAutoCommit(true);
        conn.close();
    }

    @Override
    public PowerUnit findByID(int id) throws SQLException {
        conn = psqlUtils.getConnection();
        sqlquery = "SELECT PUt.name AS type, PU.name, PU.maxcapacity FROM \"PowerUnit\" AS PU " +
                "LEFT JOIN \"PowerUnitTypes\" AS PUt ON PUt.id = PU.type " +
                "WHERE PU.id=?";
        prepstmnt = conn.prepareStatement(sqlquery);
        prepstmnt.setInt(1, id);
        rset = prepstmnt.executeQuery();
        PowerUnit punit = new PowerUnit();
        if (rset.next()) {
            punit.setType(rset.getString(1));
            punit.setName(rset.getString(2));
            punit.setMaxcapacity(rset.getDouble(3));
        } else {
            System.out.println("Result set was returned empty");
            throw new SQLException("Out of bound index");
        }
        conn.close();
        return punit;
    }
}
