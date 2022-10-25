import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class AbstractDAO<T> implements DAO<T> {
    abstract public HashMap<Integer, T> findAll() throws SQLException;
    abstract public void delete(T t) throws SQLException;
    abstract public void delete(int id) throws SQLException;
    abstract public void add(T t) throws SQLException;
    abstract public void update(T t) throws SQLException;
    abstract public T findByID(int id) throws SQLException;
}
