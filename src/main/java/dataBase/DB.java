package dataBase;

import model.Node;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DB {
    private Connection connection;
    public DB()
    {
        connection = getConnection();
    }
    public ArrayList<Node> getUserInParents(String Parent)
    {
        try {
            ArrayList<Node> node = (new NodeDAO(connection)).getByParent(Parent);
            return node;
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
            return null;
        }
    }
    public void addNode(String parent,String text)
    {
        try{
            connection.setAutoCommit(false);
            NodeDAO dao = new NodeDAO(connection);
            dao.insert(parent,text);
            connection.commit();
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
        }
    }
    public void removeNote(String id,String parent)
    {
        try{
            connection.setAutoCommit(false);
            NodeDAO dao = new NodeDAO(connection);
            dao.remove(id,parent);
            connection.commit();
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
        }
    }
    public void renameNote(String id,String text)
    {
        try{
            connection.setAutoCommit(false);
            NodeDAO dao = new NodeDAO(connection);
            dao.rename(id,text);
            connection.commit();
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
        }
    }
    public void delete(String id)
    {
        try{
            NodeDAO dao = new NodeDAO(connection);
            ArrayList<Node> list = getUserInParents(id);
            for(int i = 0; i < list.size();i++)
                delete(list.get(i).getId());
            dao.delete(id);
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
        }
    }


    private  Connection getConnection()
    {
        try {
            Class driver_class = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driver_class.newInstance();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://localhost:3306/store"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC";
            String name = "root";
            String pass = "root";
            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
