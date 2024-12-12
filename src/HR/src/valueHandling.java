package src.HR.src;

public class valueHandling {
    public String inputValidator(boolean validate) {
        String input;
        input = System.console().readLine();

        if(input.equals(" ") || input.isEmpty() || input.equals("\n")) {
            System.out.println("Incorrect input, please try again: ");
            input = System.console().readLine();
        }
        if(validate) {
            System.out.println("Is " + input + " correct? (y/n): ");
            String answer = System.console().readLine();
            if(answer.equals("n") || answer.equals("N")) {
                System.out.println("Please re-enter information: ");
                input = System.console().readLine();
            }
        }
        return input;
    }
}
