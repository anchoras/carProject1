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
    public void delete(PowerUnit powerUnit) {

    }

    @Override
    public void add(PowerUnit powerUnit) {

    }

    @Override
    public void update(PowerUnit powerUnit) {

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
