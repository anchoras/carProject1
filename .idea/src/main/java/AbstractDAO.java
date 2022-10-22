import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class AbstractDAO<T> implements DAO<T> {
    abstract public HashMap<Integer, T> findAll() throws SQLException;
    abstract public void delete(T t);
    abstract public void add(T t);
    abstract public void update(T t);
    abstract public T findByID(int id) throws SQLException;
}
