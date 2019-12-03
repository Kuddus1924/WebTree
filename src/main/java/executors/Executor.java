package executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Executor {
    private final Connection connection;

    public  Executor(Connection connection)
    {
        this.connection = connection;
    }
    public void execUpdate(String request) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute(request);
        statement.close();
    }
    public  <T> ArrayList<T> execQuery(String query, Handler<T> handler) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        ArrayList<T> value = handler.handle(resultSet);
        resultSet.close();
        statement.close();
        return value;
    }
}