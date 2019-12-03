package dataBase;

import executors.Executor;
import model.Node;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class NodeDAO {
   private Executor executor;
    public NodeDAO(Connection connection)
    {
        executor = new Executor(connection);
    }

    public void insert(String id,String text) throws SQLException
    {
        executor.execUpdate("insert into nodes (parent,text) values ('" + id + "','" + text +"')");
    }

    public void remove(String id,String parent) throws SQLException
    {
        executor.execUpdate("update nodes set parent ='" +parent + "'where id ='" + id+"'");
    }

    public void rename(String id,String text) throws SQLException
    {
        executor.execUpdate("update nodes set text ='" +text + "'where id ='" + id+"'");
    }

    public ArrayList<Node> getByParent(String id) throws SQLException
    {
        return executor.execQuery("select * from nodes where parent='" + id + "'",result -> {
            ArrayList<Node> results = new ArrayList<>();
            while(result.next()) {
                results.add(new Node(Long.toString(result.getLong(1)),result.getString(2),result.getString(3)));
            }
            return results;
        });
    }

    public void delete(String id)throws  SQLException
    {
        executor.execUpdate("delete from nodes where id='" + id+"'");
    }


}
