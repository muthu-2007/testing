import java.util.InputMismatchException;
import java.util.Scanner;

public class Getinput {
    static Scanner input = new Scanner(System.in);
    public static String getInputString(){
        while (true) {
            try{
                return input.nextLine().trim();
            }
            catch(Exception e){
                System.out.println("you have entered the invalid input");
                input.nextLine();
            }
        }
    }
    public static int getinputNumber(){
        while (true) {
            try{
                return input.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("you have entered the invalid input");
                input.nextLine();
            }
        }
    }
    public static double getinputDouble(){
        while (true) {
            try{
                return input.nextDouble();
            }
            catch(InputMismatchException e){
                System.out.println("you have entered the invalid input");
                input.nextLine();
            }
        }
    }
    
    public static double getinputLong(){
        while (true) {
            try{
                return input.nextLong();
            }
            catch(InputMismatchException e){
                System.out.println("you have entered the invalid input");
                input.nextLine();
            }
        }
    }
}
