package reader;

import dataBase.DB;
import model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeReader  implements Reader {
    private  DB dataBase;
    public NodeReader()
    {
        dataBase = new DB();
    }
    public Map<String, Node> getRoot() {
        return getById("#");
    }
    public Map<String, Node> getById(String id)
    {
        Map<String, Node> map = new HashMap<String, Node>();
        ArrayList<Node> t = dataBase.getUserInParents(id) ;
        for(int i = 0; i < t.size();i++)
        {
            map.put(t.get(i).getId(), t.get(i));
        }
        return map;
    }
    public void remove(String id,String parent)
    {
        dataBase.removeNote(id,parent);
    }
    public void delete(String id)
    {
        dataBase.delete(id);
    }
    public void rename(String id,String text)
    {
        dataBase.renameNote(id,text);
    }
    public void add(String parent,String text)
    {
        dataBase.addNode(parent,text);
    }

}
