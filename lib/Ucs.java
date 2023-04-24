import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Ucs {

    public static void main(String[] args) {
        System.out.println("Enter the no of nodes");

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] arr = new int[n + 1][n + 1];

        System.out.println("enter the nodes and its connection and their weights");
        boolean flag = true;

        while (flag) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            arr[u][v] = weight;

            System.out.println("Continue? Y/N");
            String s = sc.next();

            if (s.equals("N")) {
                flag = false;
            }

        }

        List<List<int[]>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] > 0) {
                    adj.get(i).add(new int[] { j, arr[i][j] });
                    adj.get(j).add(new int[] { i, arr[i][j] });
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int[] c : adj.get(i)) {
                System.out.print(Arrays.toString(c) + " ");
            }
            System.out.println();
        }

        int[] visited = new int[n + 1];
        List<Integer> path = new ArrayList<>();
        List<Integer> pathCost = new ArrayList<>();
        path.add(1);
        dfs(adj, visited, 1, n, path, pathCost);

        System.out.println();

        System.out.println("The start node is 1 and the goal node is " + n);

        System.out.println("The path followed to reach goal node is: ");
        System.out.println(path);
        System.out.println("The cost at each path is: ");
        System.out.println(pathCost);

        int cost = 0;

        for (int i = 0; i < pathCost.size(); i++)
            cost += pathCost.get(i);

        System.out.println("The total path cost is: " + cost);

    }

    static boolean dfs(List<List<int[]>> adj, int[] visited, int node, int goal, List<Integer> path,
            List<Integer> pathCost) {
        if (node == goal)
            return true;

        visited[node] = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int[] it : adj.get(node)) {

            if (visited[it[0]] == 0) {
                pq.offer(new int[] { it[0], it[1] });
            }
        }

        while (pq.size() > 0) {
            int[] next = pq.poll();
            path.add(next[0]);
            pathCost.add(next[1]);
            if (dfs(adj, visited, next[0], goal, path, pathCost))
                return true;
            path.remove(path.size() - 1);
            pathCost.remove(pathCost.size() - 1);
        }

        return false;

    }
}
