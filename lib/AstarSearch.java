import java.util.*;

public class AstarSeach {
    public static void main(String[] args) {
        int m, n;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the no of rows and columns in the maze ");
        m = sc.nextInt();// rows
        n = sc.nextInt();// columns

        // intialized the maze
        int[][] maze = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(
                        "Enter 1 for a blocked cell and 0 for non-blocked cell at row " + i + " and column " + j);
                int b = sc.nextInt();
                maze[i][j] = b;
            }
        }

        Node start = new Node(0, 0, 0);
        Node goal = new Node(m - 1, n - 1);

        start.h = heuristic(start, goal);
        start.f = start.g + start.h;
        goal.h = 0;

        List<int[]> path = aSearch(maze, start, goal);

        for (int[] p : path)
            System.out.print(Arrays.toString(p) + " -> ");

        System.out.println("END");

        sc.close();
    }

    private static List<int[]> aSearch(int[][] maze, Node start, Node goal) {

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.f, b.f));
        pq.offer(start);
        Map<Node, Node> cameFrom = new HashMap<>();
        int[][] visited = new int[maze.length][maze[0].length];
        visited[0][0] = 1;

        while (pq.size() > 0) {
            Node curr = pq.poll();

            if (curr.equals(goal)) {
                return reconstructPath(cameFrom, curr);
            }
            List<Node> neighbors = getAllNeighbors(maze, curr);

            for (Node adj : neighbors) {
                if (visited[adj.x][adj.y] == 0) {
                    adj.g = curr.g + 1;
                    adj.h = heuristic(adj, goal);
                    adj.f = adj.g + adj.h;
                    visited[adj.x][adj.y] = 1;
                    cameFrom.put(adj, curr);
                    pq.offer(adj);
                }
            }

        }
        return null;
    }

    private static List<int[]> reconstructPath(Map<Node, Node> cameFrom, Node curr) {
        List<int[]> path = new ArrayList<>();

        while (cameFrom.containsKey(curr)) {
            path.add(0, new int[] { curr.x, curr.y });
            curr = cameFrom.get(curr);
        }
        path.add(0, new int[] { curr.x, curr.y });

        return path;
    }

    private static List<Node> getAllNeighbors(int[][] maze, Node curr) {
        List<Node> neighbors = new ArrayList<>();

        int[][] D = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        for (int[] d : D) {
            int r = curr.x + d[0];
            int c = curr.y + d[1];

            if (r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] == 0) {
                neighbors.add(new Node(r, c, Integer.MAX_VALUE));
            }
        }

        return neighbors;
    }

    public static int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

}

class Node {
    int x;
    int y;
    int f;
    int g;
    int h;

    Node(int x, int y, int g) {
        this.x = x;
        this.y = y;
        this.g = g;

    }

    Node(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return ((Node) obj).x == x && ((Node) obj).y == y;

    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
