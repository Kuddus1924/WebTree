package reader;

import java.util.Map;

public interface Reader {
    Map getById(String id);
    Map getRoot();
    void remove(String id,String parent);
    void rename(String id,String parent);
    void delete(String id);
    void add(String parent,String text);
}
