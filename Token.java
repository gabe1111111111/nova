/**
 * this class is for tokens creates by the lexical analysis
 * @author Gabriel Lacey   
 */
public class Token {
    public int line;
    public TokenType type;
    public String data;
    public String fileName;
    /**
     * constructor if type is known
     * @param type category of token 
     * @param line  line number that the token was found at
     * @param data text the token has
     * @param fileName file the token was found in
     */
    Token(TokenType type, int line, String data, String fileName){
        this.line = line;
        this.data = data;
        this.type = type;
        this.fileName = fileName;
    }  
    /**
     * constructor if type is unknown 
     * @param line  line number that the token was found at
     * @param data text the token has
     * @param fileName file the token was found in
     */
    Token(int line, String data, String fileName){
        this.line = line;
        this.data = data;
        this.type = null;
        this.fileName = fileName;
    }
    /**
     * string representation is as follows <br>
     * data = <em> data </em> , line = <em> line </em>, type = <em> type </em>, filename = <em> filename </em>
     * @return a string representation of the token
     */
    @Override
    public String toString(){
    return ("( data = " + data + ", line = " + line + ", type = " + type + ", file name = " + this.fileName + " )\n"); 
    }

}
