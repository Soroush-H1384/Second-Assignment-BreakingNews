package AP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Infrastructure test = new Infrastructure("a2372b61298f45a3a115a6fd2e02df8d");
        boolean quit = false;
        while (true) {
            if (quit){
                break;
            }
            System.out.println("1-Show news");
            System.out.println("2-Quit");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 1:
                    test.displayNewsList();
                    break;
                case 2:
                    quit = true;
                    break;
                default:
                    System.out.println("Please Select number between 1 or 2");

            }
        }

    }
}