package gabriel.nova;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * this class handles the whole compilation process 
 * @author Gabriel Lacey
 */
public class Compiler{
public String fileName;
public ArrayList<String> sourceCode;
public ArrayList<Token> tokens;

    public Compiler() {
    sourceCode = new ArrayList<>();
    tokens = new ArrayList<>();
    }
    public Error loadProgram(String directory){
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
                fileName = directory;
                String line;
                while ((line = reader.readLine()) != null) {
                    sourceCode.add(line);
                }
            } catch (FileNotFoundException e) {
                return Error.__FILE_NOT_FOUND_EXCEPTION__;
            } catch(IOException e){
                return Error.__UNEXPECTED_ERROR_WHILE_LOADING_FILE_;
            }
        return Error.__NO_ERROR__;
    }
    public Error preProcessor(){return Error.__NO_ERROR__;}
    public Error lexer(){
        addWhitespace();
        tokenize();
        debugLog("clean", 2);
        Error error = clean();
        if(error != Error.__NO_ERROR__)return error;
        combineStrings();
        combineOperators();
        return Error.__NO_ERROR__;
    }
    private void addWhitespace(){
        for(int i = 0; i < sourceCode.size(); i++){
            sourceCode.replace(sourceCode.get(i) + "\n", i);
        }
    }
    private void tokenize(){
        String token = "";
        TokenType type = TokenType.whitespace;
        for(int line = 0; line < sourceCode.size(); line++){
            for(int column = 0; column < sourceCode.get(line).length();column++){
                switch(type){
                    case whitespace -> {
                        if(!Math.isWhiteSpace(Math.charAt(sourceCode.get(line), column))){
                            tokens.add(new Token(TokenType.whitespace, line, token, fileName));
                            token = "" + Math.charAt(sourceCode.get(line), column);
                            type = identifyType(Math.charAt(sourceCode.get(line), column));
                        }
                        else{
                            token += Math.charAt(sourceCode.get(line), column);
                        }
                    }
                    case alphabetical -> {
                        if(!Math.isAlpha(Math.charAt(sourceCode.get(line), column))){
                            tokens.add(new Token(TokenType.alphabetical, line, token, fileName));
                            token = "" + Math.charAt(sourceCode.get(line), column);
                            type = identifyType(Math.charAt(sourceCode.get(line), column));
                        }
                        else{
                            token += Math.charAt(sourceCode.get(line), column);
                        }
                    }
                    case numeric -> {
                        if(!Math.isDigit(Math.charAt(sourceCode.get(line), column)) && Math.charAt(sourceCode.get(line), column) != '.'){
                            tokens.add(new Token(TokenType.numeric, line, token, fileName));
                            token = "" + Math.charAt(sourceCode.get(line), column);
                            type = identifyType(Math.charAt(sourceCode.get(line), column));
                        }
                        else{
                            token += Math.charAt(sourceCode.get(line), column);
                        }
                    }
                    default -> {
                        if(!token.equals("")){
                            tokens.add(new Token(TokenType.special, line, token, fileName));
                            token = "" + Math.charAt(sourceCode.get(line), column);
                            type = identifyType(Math.charAt(sourceCode.get(line), column));
                        }
                        else{
                            token += Math.charAt(sourceCode.get(line), column);
                        }
                    }  
                }
            }
        }
    }
    private static TokenType identifyType(char in){
        if(Math.isAlpha(in))return TokenType.alphabetical;
        if(Math.isDigit(in))return TokenType.numeric;
        if(Math.isWhiteSpace(in))return TokenType.whitespace;
        else return TokenType.special;
    }
    private Error clean(){
        /*
        comment in string dropped ~
        back slash does not nullify strings
        */
        ArrayList<Token> temp = new ArrayList<>();
        boolean isString = false;
        boolean backslashParody = false; 
        for(int i = 0; i < tokens.size(); i++){
            Token token = tokens.get(i);
            debugLog(token.data, 0); 
            if(token.type == TokenType.whitespace) continue;
            if(token.type != TokenType.special){temp.add(token); continue;}
            
            switch (token.data) {
                case "`" ->{
                        backslashParody = false;
                        /*this is a multi line comment
                        * if in string add to temp
                        * else loop through the tokens tracking nesting dropping whole comment
                        */
                        int tokensSize = tokens.size();
                        if(i+1 >= tokensSize) return Error.__NO_ERROR__;
                        if(!tokens.get(i+1).data.equals("~")){
                            debugLog("found multiLine comment", 0);
                            temp.add(token);
                            continue;
                        }       if(isString) temp.add(token);
                        else{
                            
                            int commentTracker = 0;
                            do{
                                String debugVar = tokens.get(i).data;
                                debugLog(debugVar, -1);
                                if(i+1 >= tokensSize)return Error.__SYNTAX_ERROR_INCOMPLETE_COMMENT__;
                                if(tokens.get(i).data.equals("`")&&tokens.get(i+1).data.equals("~"))commentTracker++;
                                if(tokens.get(i).data.equals("~")&&tokens.get(i+1).data.equals("`"))commentTracker--;
                                i++;
                            }while(commentTracker > 0);
                        }
                        break;
                    }
                case "~" ->{
                        debugLog("found single Line comment", 0);
                        backslashParody = false;
                        /*this is a single line comment
                        *  if in string add to temp
                        * else loop through the rest of line and drop
                        */
                        if(isString){
                            temp.add(token);
                            continue;

                        }
                        int lineToSkip = token.line;
                        int tokensSize = tokens.size();
                        while(tokens.get(i).line == lineToSkip){
                            if(i >= tokensSize)return Error.__NO_ERROR__;
                            i++;
                        }
                        break;
                    }
                case "\"", "'" -> {
                    debugLog("char or string", 0);
                    boolean parodyTemp = backslashParody;
                    backslashParody = false;
                    /*this is a string or a char
                    * this can not be in a comment
                    * if in backslash ignore
                    * else flip string bit and add to temp
                    */
                    if(parodyTemp){ temp.add(token); continue;}
                    isString = !isString;
                    temp.add(token);
                    break;
                }
                case "\\" -> {
                    debugLog("found \\", 0);
                    /*this can not be in a comment
                    * flip backslash parody bit and add to temp
                    */
                    backslashParody = !backslashParody;
                    temp.add(token);
                }
                default -> {
                    debugLog("other", 0);
                    backslashParody = false;
                    /*this is some other special char
                    * add to temp
                    */
                    temp.add(token);
                    break;
                }
            }
        }
        
        tokens = temp;
        return Error.__NO_ERROR__;
    }
    private void combineStrings(){}
    private void combineOperators(){}
    public Error parser(){return Error.__NO_ERROR__;}
    public Error codeGeneration(){return Error.__NO_ERROR__;}
    public Error flowChart(){return Error.__NO_ERROR__;}
    public Error assemblyGeneration(){return Error.__NO_ERROR__;}
    public Error assembler(){return Error.__NO_ERROR__;}
    private void debugLog(Object what, int when){
        int currentTest = 2;
        if(when == currentTest)System.err.println(what);
    }
}

/**** DEFINED PARTS OF THE LANGUAGE ****
 * ~ single line comment
 * `~ ~` multi line, partial line, and nested comments
 * " string
 * ' char
 * 
 * 
 */