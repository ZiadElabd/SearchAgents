package Main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("   8-Puzzle   ");
        System.out.println("enter the state: ");
        String state = scan.next();
        System.out.println("choose the search: ");
        System.out.println("1- DFS");
        System.out.println("2- BFS");
        System.out.println("3- A*");
        int choice = scan.nextInt();
        if (choice == 1){

        }else if (choice == 2){

        }else if (choice == 3){

        }
        
    }
}
