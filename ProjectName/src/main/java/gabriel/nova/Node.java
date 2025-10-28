package gabriel.nova;
/**
 * this class is the node for the abstract syntax tree 
 * this class will be used as the abstract syntax tree
 * @author Gabriel Lacey
 */
import java.util.Iterator;
public class Node implements Comparable<Node>{
    public int depth;
    public int index;
    public ArrayList<Node> branches;
    public Node parent = null;
    public Token data;
    public NodeType type;
/**
 * constructor
 * @param data the token the node refers to
 * @param type the category of node
 */
    public Node(Token data, NodeType type) {
        this.data = data;
        this.type = type;
    }
    /**
     * modifies data of both the input and this to make in a well formed child of this
     * @param in node to be made into a child of this
     */
    public void makeChild(Node in){
        in.depth = depth +1;
        in.index = branches.size;
        in.parent = this;
        this.branches.add(in);
    }

    @Override
    /**
     * 
     * @return a string representation of itself and its subtree 
     */
     public String toString(){
        String out = "";
        for(int i = 0; i < depth; i++){
            out += "    ";
        }
        out += data.toString() + type + "\n";
        for(Iterator<Node> it = branches.iterator(); it.hasNext(); pass()){
            Node i = it.next();
            out += i.toString();
        }
        return out;
    }
    /**
     * counts the nodes in its tree
     * @return number of nodes in its tree
     */
    @SuppressWarnings("empty-statement")
    public int count(){
        int out = 1;
        for (Iterator<Node> it = branches.iterator(); it.hasNext(); pass()) {
            Node i = it.next();
            out += i.count();
        }
        return out;
    }

    /**
     * compares the line the token data is at
     */
    @Override
    public int compareTo(Node o) {
        /*
         * if o < this return -
         */
        if(o.data.line < data.line) return -1;
        else if (o.data.line > data.line) return 1;
        return 0;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    private void pass(){}
}
