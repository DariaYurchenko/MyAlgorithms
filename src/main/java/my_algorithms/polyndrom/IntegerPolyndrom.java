package my_algorithms.polyndrom;

public class IntegerPolyndrom {

    public static boolean isPolyndrom(Integer i) {
       int reverse = 0;
       int polyndrom = i;

       while(polyndrom != 0) {
           reverse = reverse*10 + polyndrom%10;
           polyndrom = polyndrom / 10;
       }

       return i == reverse;
    }

    public static void main(String[] args) {
        System.out.println(isPolyndrom(232));

    }
}
