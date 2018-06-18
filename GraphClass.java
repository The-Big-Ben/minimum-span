/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kattis.cats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Benjamin
 */
public class GraphClass {
    static Scanner sc;

    public static void main(String[] args)throws Exception {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int milk = sc.nextInt();
            int cats = sc.nextInt();
            int spiltMilk = milk -prims(buildGraph(cats));
            if (spiltMilk >= cats) {
                System.out.println("yes");
            }
            else {System.out.println("no");}
            
        }

    }

    static int minKey(int costKey[], boolean isMarked[]) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < costKey.length; v++) {
            if (!isMarked[v] && costKey[v] < min) {
                min = costKey[v];
                min_index = v;
            }

        }
        return min_index;
    }

    void printMST(int parent[], int n, int graph[][]) {
        System.out.println("Edge   Weight");
        for (int i = 0; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "    "
                    + graph[i][parent[i]]);
        }
    }

    static public int prims(int[][] graph) {
        
        int[] cost = new int[graph.length];
        //int[] parentTree = new int[graph.length];
        boolean[] marked = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            cost[i] = Integer.MAX_VALUE;
            marked[i] = false;
        }

        //int markCount=0;
        cost[0] = 0;
        int totalcost = 0;

        for (int count = 0; count < graph.length; count++) {
            int u = minKey(cost, marked);
            marked[u] = true; totalcost+= cost[u];

            for (int v = 0; v < graph.length; v++) {
                if (!marked[v] && graph[u][v] < cost[v]) {
                    //parentTree[v] = u;
                    cost[v] = graph[u][v];
                }
            }
        }
            return totalcost;
    }

    static public int[][] buildGraph(int ncats) throws Exception {
        int theGraph[][] = new int[ncats][ncats];

        for (int i = 0; i < ncats*(ncats - 1)/2; i++) {
            //Scanner newsc = new Scanner(System.in);
            int j = sc.nextInt();
            int k = sc.nextInt();
            theGraph[j][k] = theGraph[k][j] = sc.nextInt();
        }
        return theGraph;

    }
}
/*2
20 5
0 1 4
0 2 3
0 3 10
0 4 15
1 2 7
1 3 3
1 4 5
2 3 4
2 4 3
3 4 8
16 5
0 1 4
0 2 3
0 3 10
0 4 15
1 2 7
1 3 3
1 4 5
2 3 4
2 4 3
3 4 8*/
