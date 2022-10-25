import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DAO<T> {
    HashMap<Integer, T> findAll() throws SQLException;
    void delete(T t) throws SQLException;
    void delete(int id) throws SQLException;
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    T findByID(int id) throws SQLException;
}