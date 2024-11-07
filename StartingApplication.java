import Modeling.ModelingDepartment;

import java.util.Scanner;

public class StartingApplication {
    static ModelingDepartment modelingDepartment = new ModelingDepartment();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("--WELCOME TO FASHION EMPIRE--\n You are logged in as: IT SPECIALIST\n\nWhich Department would you like to go to?" +
                "\n1: HR Department" +
                "\n2: Treasury" +
                "\n3: Manufacturing" +
                "\n4: Modeling" +
                "\n5: Inventory");
        int choice = s.nextInt();

        switch (choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                modelingDepartment.runTasks();
                break;
            case 5:
                break;
        }

    }
}


