// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String str = "+994551447691";
        if(!str.matches("\\+994(50|51|55|70|77)(\\d{7})"))
            System.out.println(0);
        else System.out.println(1);
    }
}