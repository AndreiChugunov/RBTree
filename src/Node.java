/**
 * Created by 804314 on 09.04.2016.
 */
public class Node<T extends Comparable<T>> {
    public Node<T> right;
    public Node<T> left;
    public Node<T> parent;
    public T value;
    private Color color;


    public Node(T value, Node<T> parent, Node<T> nil){
        this.value = value;
        this.parent = parent;
        if(parent != nil) {
            this.color = Color.RED;
            if(this.value.compareTo(parent.value) > 0)
                parent.right = this;
            else
                parent.left = this;
        }
        else
            this.color = Color.BLACK;
    }
    public Node() {
        this.value = null;
        parent = this;
        left = this;
        right = this;
        color = Color.BLACK;
    }
    public Color color() {
        return (value == null) ? Color.BLACK : color;
    }
    public void color(Color color){
        this.color = color;
    }

}
