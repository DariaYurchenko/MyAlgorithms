package my_algorithms.polyndrom;

public class StringPolyndrom {

    public static boolean isPolyndrom(String s) {
        if(s.length() == 1) {
            return true;
        }
        else {
            if(s.substring(0,1).equals(s.substring(s.length()-1))) {
                if(s.length() == 2) {
                    return true;
                }
                else {
                    return isPolyndrom(s.substring(1, s.length()-1));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "kusdfdsuk";
        System.out.println(isPolyndrom(s));
    }
}
