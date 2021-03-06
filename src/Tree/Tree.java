package Tree;


/**
 *
 * @author omarc
 * @param <T>
 */
public class Tree<T>  {
    
    private Node<T> root;
    
    public Tree() {
        this.root = new Node<>();
    }

    public Tree(T content) {
        this.root = new Node<>(content);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }
    
}
