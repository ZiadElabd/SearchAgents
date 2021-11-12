package Main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("   8-Puzzle   ");
        System.out.println("enter the state: ");
        System.out.println("choose the search: ");
        System.out.println("1- DFS");
        System.out.println("2- BFS");
        System.out.println("3- A*");
        int state[][] = new int[3][3] ; // scan it ya ziad ya sharmoot
        int choice = scan.nextInt();
        if (choice == 1){
            dfsSearch dfs = new dfsSearch() ;
            dfs.Search(state); 
        }else if (choice == 2){

        }else if (choice == 3){

        }
        
    }
}
