
import java.util.Iterator;
public class Node implements Comparable<Node>{
    public int depth;
    public int index;
    public ArrayList<Node> branches;
    public Node parent = null;
    public Token data;
    public NodeType type;
    public Node(ArrayList<Node> branches, Token data, int depth, int index, NodeType type) {
        this.branches = branches;
        this.data = data;
        this.depth = depth;
        this.index = index;
        this.type = type;
    }
    public void makeChild(Node in){}
    @Override
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
    @SuppressWarnings("empty-statement")
    public int count(){
        int out = 1;
        for (Iterator<Node> it = branches.iterator(); it.hasNext(); pass()) {
            Node i = it.next();
            out += i.count();
        }
        return out;
    }

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
