import java.sql.*;
import java.util.ArrayList;

public class PowerUnitDAO extends AbstractDAO<PowerUnit> {
    private Connection conn;
    private Statement stmnt;
    private PreparedStatement prepstmnt;
    private String sqlquery;
    private ResultSet rset;

    @Override
    public ArrayList<PowerUnit> findAll(PowerUnit powerUnit) throws SQLException {
        conn = PostgreSQLConnUtils.getConnection();
        sqlquery = "SELECT * FROM PowerUnit";
        stmnt = conn.createStatement();
        rset = stmnt.executeQuery(sqlquery);
        ArrayList<PowerUnit> punits = new ArrayList<PowerUnit>();
        while (rset.next()) {
            PowerUnit punit = new PowerUnit();
            int id = rset.getInt(1);
            punit.setType(String.valueOf(rset.getInt(2)));
            punit.setName(rset.getString(3));
            punit.setMaxcapacity(rset.getDouble(4));
        }
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
        conn = PostgreSQLConnUtils.getConnection();
        sqlquery = "SELECT * FROM PowerUnit WHERE id=?";
        prepstmnt = conn.prepareStatement(sqlquery);
        prepstmnt.setInt(1, id);
        rset = prepstmnt.executeQuery(sqlquery);
        PowerUnit punit = new PowerUnit();
        while (rset.next()) {
            punit.setType(String.valueOf(rset.getInt(2)));
            punit.setName(rset.getString(3));
            punit.setMaxcapacity(rset.getDouble(4));
        }
        return punit;
    }
}
