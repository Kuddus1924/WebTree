package executors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Handler<T> {
    ArrayList<T> handle(ResultSet resultSet) throws SQLException;
}
