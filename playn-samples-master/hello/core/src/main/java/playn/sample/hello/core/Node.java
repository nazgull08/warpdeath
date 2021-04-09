      // для запуска!!!! (mvn test -Pjava)
package playn.sample.hello.core;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    public List<Node<T>> children = new ArrayList<Node<T>>();
    public Node<T> parent = null;
    public T data = null;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        Node<T> child = new Node<T>(data);
        child.setParent(this);
        this.children.add(child);
        System.out.printf("+++0\n");
    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
        System.out.printf("+++1\n");
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }
}
