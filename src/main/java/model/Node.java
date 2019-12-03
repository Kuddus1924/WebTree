package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {
    @JsonProperty("id")
    private String id;
    @JsonProperty("parent")
    private String parent;
    @JsonProperty("text")
    private String text;
    @JsonProperty("children")
    private boolean children;
    public Node(String id,String parent,String text)
    {
        this.id = id;
        this.parent = parent;
        this.text = text;
        children = true;
    }

    public String getId() {
        return id;
    }

    public String getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }
}
