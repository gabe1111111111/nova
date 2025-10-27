public class Token {
    public int line;
    public TokenType type;
    public String data;
    public String fileName;
    Token(TokenType type, int line, String data, String fileName){
        this.line = line;
        this.data = data;
        this.type = type;
        this.fileName = fileName;
    }  // Constructor
    Token(int line, String data, String fileName){
        this.line = line;
        this.data = data;
        this.type = null;
        this.fileName = fileName;
    }
    public String toString(){
    return ("( data = " + data + ", line = " + line + ", type = " + type + ", file name = " + this.fileName + " )\n"); 
    }

}
