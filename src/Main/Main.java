package Main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("   8-Puzzle   ");
        System.out.println("enter the state: ");
        int state[][] = new int[3][3] ; // scan it ya ziad ya sharmoot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = scan.nextInt();
            }
        }
        System.out.println("choose the search: ");
        System.out.println("1- DFS");
        System.out.println("2- BFS");
        System.out.println("3- A*");
        int choice = scan.nextInt();
        if (choice == 1){
            dfsSearch dfs = new dfsSearch() ;
            dfs.Search(state); 
        }else if (choice == 2){

        }else if (choice == 3){

        }
        
    }
}
