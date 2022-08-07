import java.util.Scanner;

public class Tiki {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.printf("Moi ban nhap ten: ");
        String ip = sc.nextLine();
        System.out.println("Hello ban " + ip);
    }
}
