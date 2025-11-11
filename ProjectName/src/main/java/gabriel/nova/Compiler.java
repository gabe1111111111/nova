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
        clean();
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
                            tokens.add(new Token(TokenType.numeric, line, token, fileName));
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
    private void clean(){}
    private void combineStrings(){}
    private void combineOperators(){}
    public Error parser(){return Error.__NO_ERROR__;}
    public Error codeGeneration(){return Error.__NO_ERROR__;}
    public Error flowChart(){return Error.__NO_ERROR__;}
    public Error assemblyGeneration(){return Error.__NO_ERROR__;}
    public Error assembler(){return Error.__NO_ERROR__;}

}