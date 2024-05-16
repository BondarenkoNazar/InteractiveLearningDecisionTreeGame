package finaltask.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private String data;
    private Node parent;
    private final List<Node> children;

    public Node(){
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public Node(String data){
        this.data = data;
        this.children = new ArrayList<>();
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
