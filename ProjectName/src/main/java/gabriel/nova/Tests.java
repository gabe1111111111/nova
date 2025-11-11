package gabriel.nova;
/**
 * this will hold test cases for various parts of the project
 * @author Gabriel Lacey
 */

public class Tests {

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        Error error = compiler.loadProgram("ProjectName\\test.txt");
        if(error != Error.__NO_ERROR__) System.out.println(error);
        error = compiler.lexer();
        if(error != Error.__NO_ERROR__) System.out.println(error);
        for(Token i : compiler.tokens){
            System.out.println(i.data);
        }
        System.out.println(compiler.loadProgram("doesNotExist.txt"));
    }

}
