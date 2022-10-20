import java.sql.SQLException;
import java.util.ArrayList;

abstract public class AbstractDAO<T> implements DAO<T> {
    abstract public ArrayList<T> findAll(T t) throws SQLException;
    abstract public void delete(T t);
    abstract public void add(T t);
    abstract public void update(T t);
    abstract public T findByID(int id) throws SQLException;
}
