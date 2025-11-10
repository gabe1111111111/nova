package gabriel.nova;

public class Math {
    private static final HashMap<Character> whiteSpaceChars = new HashMap<>('\n', ' ', '\t', '\r', '\f');
    private static final HashMap<Character> upperCase = new HashMap<>('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static final HashMap<Character> lowerCase = new HashMap<>('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    private static final HashMap<Character> digits = new HashMap<>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    private static final HashMap<Character> special = new HashMap<>('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{', ']', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?', '`', '~');
    public static int absoluteValue(int in){return in & 0x7fffffff;}
    public static boolean isWhiteSpace(char in){
        return whiteSpaceChars.contains(in);
    }
    public static boolean isUpper(char in){
        return upperCase.contains(in);
    }
    public static boolean isLower(char in){
        return lowerCase.contains(in);
    }
    public static boolean isAlpha(char in){
        return isLower(in) || isUpper(in);
    }
    public static boolean isDigit(char in){
        return digits.contains(in);
    }
    public static boolean isSpecial(char in){
        return special.contains(in);
    }
    public static char charAt(String in, int index){return in.charAt(index);}
}
