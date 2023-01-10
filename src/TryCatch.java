
import java.util.Scanner;

public class TryCatch {
    public static int tryCatchInt(Scanner scanner){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException a){
                System.out.println(a.getMessage());
                System.out.println("Pls enter number format");
            }
        }
    }
    public static String tryCatchString(Scanner scanner){
        while (true){
            String s = scanner.nextLine();
            if( !s.equals("")){
                return s;
            } else{
                System.out.println("Blank input!!Pls write something ");
            }
        }
    }
}
