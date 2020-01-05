import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    public static ArrayList<Integer>[] adjacentEdge;
    public static int V, E;
    public static boolean[] visited;

    public static void main(String[] args) {
        initiateGraph();
        distanceFromRootBFS(0);
    }

    public static void initiateGraph() {
        Scanner scanner = new Scanner(System.in);
        V = scanner.nextInt();
        E = scanner.nextInt();
        adjacentEdge = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            adjacentEdge[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < E; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adjacentEdge[a].add(b);
            adjacentEdge[b].add(a);
        }
        scanner.close();
    }

    //TODO fix distanceFromRootBFS
    public static void distanceFromRootBFS(int root) {
        visited = new boolean[V];
        int[] distance = new int[V];
        ArrayList<Integer> queue = new ArrayList<>();

        visited[root] = true;
        distance[0] = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            int parent = queue.remove(0);
            for (int child : adjacentEdge[parent]) {
                if (!visited[child]) {
                    visited[child] = true;
                    distance[child] = distance[parent] + 1;
                    queue.addAll(adjacentEdge[child]);
                }
            }
        }

        printArray(distance);
    }

    public static void dfs(int current) {
        visited[current] = true;
        for (int i = 0; i < adjacentEdge[current].size(); i++) {
            int next = adjacentEdge[current].get(i);
            if (!visited[next])
                dfs(next);
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array[array.length - 1]);
    }

    public static void printRow(Collection<?> row) {
        System.out.println(row.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    public static int connectedComponentsAmountDFS() {
        visited = new boolean[V + 1];
        int count = 0;
        for (int i=1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i);
                ++count;
            }
        }
        return count;
    }
}
