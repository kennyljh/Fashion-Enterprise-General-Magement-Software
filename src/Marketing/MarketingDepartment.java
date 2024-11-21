package src.Marketing;

import src.App;

import src.Marketing.src.*;

import java.util.Scanner;

public class MarketingDepartment {
    static HOD hod;
    public static FileManager fileManager;

    public MarketingDepartment() {
        fileManager = new FileManager();
        hod = fileManager.getHOD();
    }

    public void start() throws Exception {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 4) {
            System.out.println("\nPlease choose an action you want to take: \n" +
                    " 1: advertiseEvent \n" +
                    " 2: advertiseProduct\n" +
                    " 3: Admin Activities\n" +
                    " 4: Back");
            c = s.nextInt();
            switch (c) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> initiateAdmin();
            }
            App.prompt();
        }
    }

    private void initiateAdmin() {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 2) {
            System.out.println("Please choose an action you want to take: \n" +
                    " 1: manage HOD\n" +
                    " 2: Back");
            c = s.nextInt();
            if (c == 1) {
                HOD hod = new HOD();
                System.out.println(hod);
            }
        }
    }
}
