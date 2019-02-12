package grammar;


/*
String implements the interface of CharSequence
 */
public class StringOperation {
    public static char[] splitToCharArray(String s){ //"a b c"
        String r = s.replace(" ", "");
        return r.toCharArray();
    }

    public static void main(String[] args) {
        char[] letters = splitToCharArray("a b  c");
    }
}
