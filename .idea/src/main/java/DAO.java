import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DAO<T> {
    HashMap<Integer, T> findAll() throws SQLException;
    void delete(T t);
    void add(T t);
    void update(T t);
    T findByID(int id) throws SQLException;
}
