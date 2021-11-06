package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.TreeMap;

public class dfsSearch implements Runnable {

    static TreeMap<String, Boolean> vis = new TreeMap<>();

    static Stack<int[][]> pathStack = new Stack<>();

    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };

    private static String stateGenerator(int[][] state) {

        StringBuilder ret = new StringBuilder("");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ret.append(Integer.toString(state[i][j]));
            }
        }
        String s = ret.toString();
        return s;
    }

    private static boolean isCorrect(int[][] state) {
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != x++)
                    return false;
            }
        }
        return true;
    }

    private static void swap(int[][] state, int i, int j, int x, int y) {
        int tmp = state[i][j];
        state[i][j] = state[x][y];
        state[x][y] = tmp;
        return;
    }

    private static boolean dfs(int[][] state, int i, int j) {

        if (isCorrect(state)) {
            pathStack.push(state);
            return true;
        }

        if (i < 0 || j < 0 || i >= 3 || j >= 3) {
            return false;
        }

        if (vis.containsKey(stateGenerator(state))) {
            return false;
        }

        vis.put(stateGenerator(state), true);
        pathStack.push(state);
        for (int p = 0; p < 4; p++) {

            int ni = i + dx[p];
            int nj = j + dy[p];

            if (ni < 0 || nj < 0 || ni >= 3 || nj >= 3)
                continue;

            int newState[][] = new int[3][3];

            for (int ii = 0; ii < 3; ii++) {
                for (int jj = 0; jj < 3; jj++) {
                    newState[ii][jj] = state[ii][jj];
                }
            }

            swap(newState, i, j, ni, nj);
            if (dfs(newState, ni, nj))
                return true;
        }
        pathStack.pop();
        return false;
    }

    private static boolean isSolvable(int[][] initial) {
        Integer[] arr = new Integer[9];
        int k = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                arr[k++] = initial[i][j];

        int cnt = 0;
        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++)
                if (arr[i] != 0 && arr[j] != 0 && arr[i] > arr[j])
                    cnt++;
        return cnt % 2 == 0;
    }

    public static void main(String[] args) {
        new Thread(null, new dfsSearch(), "", 1 << 30).start();
    }

    public void run() {

        int[][] arr = { { 2,1, 0 }, { 3, 4, 5 }, { 6, 8, 7 } };
        if (isSolvable(arr)) {
            dfs(arr, 0, 2);
        } else {
            System.out.println("There is no solution for this puzzle !!");
            return;
        }

        ArrayList<int[][]> path = new ArrayList<>();
        while (!pathStack.isEmpty()) {
            path.add(pathStack.pop());
        }
        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            int a[][] = path.get(i);
            for (int k = 0; k < a.length; k++) {
                System.out.print("* ");
                for (int m = 0; m < a[0].length; m++) {
                    System.out.print(a[k][m] + " ");
                }
                System.out.println("*");
            }
            System.out.println("*********");
        }
        System.out.println("Total steps to get to the goal = " + (path.size() - 1) + " step");
    }

    public Integer Search(int[][] initial) {

        return 0;
    }
}
