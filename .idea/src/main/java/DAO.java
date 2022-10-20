import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    ArrayList<T> findAll(T t) throws SQLException;
    void delete(T t);
    void add(T t);
    void update(T t);
    T findByID(int id) throws SQLException;
}
