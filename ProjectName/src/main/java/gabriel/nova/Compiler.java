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
public ArrayList<String> sourceCode;

    public Compiler() {
    sourceCode = new ArrayList<>();
    }
    public Error loadProgram(String directory){
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
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
    public Error preProcessor(){
        
        return Error.__NO_ERROR__;
    }
    public Error lexer(){return Error.__NO_ERROR__;}
    public Error parser(){return Error.__NO_ERROR__;}
    public Error codeGeneration(){return Error.__NO_ERROR__;}
    public Error flowChart(){return Error.__NO_ERROR__;}
    public Error assemblyGeneration(){return Error.__NO_ERROR__;}
    public Error assembler(){return Error.__NO_ERROR__;}

}