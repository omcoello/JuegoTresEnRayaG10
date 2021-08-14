/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.ArrayList;

/**
 *
 * @author omarc
 * @param <T>
 */
public class Node<T> {
    private T content;
    private ArrayList<Tree<T>> children;
    
    public Node(){
        this.children = new ArrayList<>();
    }
    
    public Node(T content){
        this.content = content;
        this.children = new ArrayList<>();
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public ArrayList<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tree<T>> children) {
        this.children = children;
    }
}
