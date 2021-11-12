package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class dfsSearch {

    static HashMap<String, Boolean> vis = new HashMap<>();

    static Stack<int[][]> pathStack = new Stack<>();
    static Stack<String> plainPath = new Stack<>();
    static Integer Depth = 0;
    static Integer NodesExpanded = 0;

    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };

    private static String stateGenerator(int[][] state) {

        StringBuilder ret = new StringBuilder("");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ret.append(Integer.toString(state[i][j]));
            }
        }
        return ret.toString();
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

    private static boolean dfs(int[][] state, int i, int j, int dir, int depth) {

        if (i < 0 || j < 0 || i >= 3 || j >= 3)
            return false;

        if (vis.containsKey(stateGenerator(state)))
            return false;

        vis.put(stateGenerator(state), true);
        NodesExpanded++;
        pathStack.push(state);

        if (dir == 1)
            plainPath.push("DOWN");
        else if (dir == 2)
            plainPath.push("RIGHT");
        else if (dir == 3)
            plainPath.push("UP");
        else if (dir == 4)
            plainPath.push("LEFT");
        else
            plainPath.push("INITIAL STATE!");

        if (isCorrect(state)) {
            pathStack.push(state);
            Depth = depth;
            return true;
        }

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

            if (dfs(newState, ni, nj, p + 1, depth + 1))
                return true;
        }

        pathStack.pop();
        plainPath.pop();
        return false;
    }

    // public static void main(String[] args) {
    // new Thread(null, new dfsSearch(), "", 1 << 30).start();
    // }

    // public void run() {

    // int[][] arr = { { 1, 0, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    // long st = System.currentTimeMillis();
    // if (!dfs(arr, 0, 1, -1, 0)) {
    // System.out.println("There is no solution for this puzzle !!");
    // return;
    // } else {
    // ArrayList<String> path = new ArrayList<>();
    // while (!plainPath.isEmpty()) {
    // path.add(plainPath.pop());
    // }
    // Collections.reverse(path);
    // System.out.println("*********** THE PATH ***********");
    // for (int ii = 0; ii < path.size(); ii++) {
    // System.out.println(path.get(ii));
    // System.out.println("***********");
    // }

    // System.out.println("################################################");
    // System.out.println("Total steps to get to the goal = " + (path.size() - 1) +
    // " step");
    // System.out.println("################################################");

    // }
    // System.out.println("Time taken : " + (System.currentTimeMillis() - st) + "
    // ms");
    // System.out.println("################################################");
    // System.out.println("Nodes Expanded : " + NodesExpanded);
    // System.out.println("################################################");
    // System.out.println("Search depth : " + Depth);
    // System.out.println("################################################");

    // }

    public void Search(int[][] initial) {
        
        int i = -1, j = -1;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (initial[a][b] == 0) {
                    i = a;
                    j = b;
                    break;
                }
            }
        }
        if (i == -1) {
            System.out.println("Invalid puzzle!");
            return;
        }
        long st = System.currentTimeMillis();
        if (!dfs(initial, i, j, -1, 0)) {
            System.out.println("There is no solution for this puzzle !!");
            return;
        } else {
            ArrayList<String> path = new ArrayList<>();
            while (!plainPath.isEmpty()) {
                path.add(plainPath.pop());
            }
            Collections.reverse(path);
            System.out.println("*********** THE PATH ***********");
            for (int ii = 0; ii < path.size(); ii++) {
                System.out.println("***********");
                System.out.println("*" + path.get(ii) + "*");
                System.out.println("***********");
            }

            System.out.println("################################################");
            System.out.println("Total steps to get to the goal = " + (path.size() - 1) + " step");
            System.out.println("################################################");

        }
        System.out.println("Time taken : " + (System.currentTimeMillis() - st) + " ms");
        System.out.println("################################################");
        System.out.println("Nodes Expanded : " + NodesExpanded);
        System.out.println("################################################");
        System.out.println("Search depth : " + Depth);
        System.out.println("################################################");
        return;
    }
}
