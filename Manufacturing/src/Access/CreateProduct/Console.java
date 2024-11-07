import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Console {


    private static final String LOG_FILE = "production_log";
    private PrintWriter output;

    public Console() {

        try{
            output = new PrintWriter(new FileWriter(LOG_FILE, true));
        }
        catch(IOException e){
            System.out.println("Error running log file: " + e.getMessage());
        }
    }

    public void start(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Manufacturing Console");
        //ask for inputs for useCases
        System.out.println("Enter 1 to createProduct()");

        String input = scan.nextLine();
        if(input.equals("1")){
            //call to run createProduct
        }
        else{
            System.out.println("Invalid input");
        }

        output.close();
        scan.close();
    }
    //when input is 1 call to output given class information
    private void createProduct(){

        //Call to given Object classes that are related to createProduct


    }
    //when input is 2 call to testProduct
    private void testProduct(){

    }

    private void printToConsole(String message){
        System.out.println(message);
        output.println(message);
        output.flush();
    }

    public static void main(String[] args) {
        Console console = new Console();
        console.start();
    }

}
