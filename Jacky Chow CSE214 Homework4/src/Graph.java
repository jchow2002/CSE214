import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Graph {
    private final double[][] adjacencyMatrix;

    private Graph(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public static Graph fromMatrixString(String s) {
        if (s.trim().isEmpty()) {
            return new Graph(new double[0][0]);
        }
        String[] arr1 = s.split("\n"); // "0.0 0.0 0.5 0.7 0.0 0.0"
//        if (arr1.length == 1 || arr1.length == 0) {
//            throw new IllegalArgumentException();
//        }
        double[][] graph = new double[arr1.length][arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            String row = arr1[i];
            String[] nums = row.split(" ");
            for (int j = 0; j < nums.length; j++) {
                double num = Double.parseDouble(nums[j]);
                graph[i][j] = num;
            }
        }
        return new Graph(graph);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                builder.append(String.format("%1.2f ", adjacencyMatrix[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Graph getTree() {
        int length = adjacencyMatrix.length;

        if (length == 0) {
            //System.out.println("Empty Matrix");
            return new Graph(new double[0][0]);
        }

        int[] parent = new int[length];
        double[] weight = new double[length];
        boolean[] discovered = new boolean[length];
        double[][] resultMatrix = new double[length][length];

        for (int i = 0; i < length; i++) {
            discovered[i] = false;
            weight[i] = Double.MAX_VALUE;
        }
        weight[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < length - 1; i++) {
            int index = minIndex(weight, discovered);
            discovered[index] = true;
            for (int j = 0; j < length; j++) {
                if (adjacencyMatrix[index][j] != 0 && adjacencyMatrix[index][j] < weight[j] && !discovered[j]) {
                    weight[j] = adjacencyMatrix[index][j];
                    parent[j] = index;
                }
            }
        }
        for (int i = 1; i < resultMatrix.length; i++) {
            resultMatrix[i][parent[i]] = weight[i];
            resultMatrix[parent[i]][i] = weight[i];
        }
//        System.out.println(Arrays.deepToString(resultMatrix));
//        System.out.println("MST in adjacency matrix form: ");
        return new Graph(resultMatrix);
    }

    int minIndex(double[] weight, boolean[] discovered) {
        int minIndex = -1;
        double minWeight = Integer.MAX_VALUE;
        for (int i = 0; i < adjacencyMatrix.length; i++)
            if (weight[i] < minWeight && !discovered[i]) {
                minIndex = i;
                minWeight = weight[i];
            }
        return minIndex;
    }

    private static final String s =
            "0.0 0.0 0.5 0.7 0.0 0.0\n" +
                    "0.0 0.0 0.7 0.0 1.0 0.0\n" +
                    "0.5 0.7 0.0 1.0 0.0 0.9\n" +
                    "0.7 0.0 1.0 0.0 0.0 0.0\n" +
                    "0.0 1.0 0.0 0.0 0.0 0.7\n" +
                    "0.0 0.0 0.9 0.0 0.7 0.0";

    public static void main(String... args) {
        // you can assume that incorrect strings will not be provided as input,
        // i.e., any input string will be a valid adjacency matrix
        // representation of an undirected weighted graph
        String s2 = "";
        String s3 = "1";
        String s4 = "0 12 0 0 10 0\n" +
                "12 0 0 7 0 0\n" +
                "0 0 0 15 0 0\n" +
                "0 7 15 0 50 0.5\n" +
                "10 0 0 50 0 13\n" +
                "5 0 0 0 13 0";

        Graph g = Graph.fromMatrixString(s);
        Graph g2 = Graph.fromMatrixString(s2);
        Graph g3 = Graph.fromMatrixString(s3);
        Graph g4 = Graph.fromMatrixString(s4);

//        System.out.println("Normal input:");
        System.out.println(g);
//        System.out.println("Empty input: ");
        System.out.println(g2);
//        System.out.println("1 vertex: ");
        System.out.println(g3);
//        System.out.println("Normal input 2:");
        System.out.println(g4);

//        System.out.println("Normal input:");
        System.out.println(g.getTree());
//        System.out.println("Empty input: ");
        System.out.println(g2.getTree());
//        System.out.println("1 vertex: ");
        System.out.println(g3.getTree());
//        System.out.println("Normal input2: ");
        System.out.println(g4.getTree());

    }
}

//        int length = adjacencyMatrix.length;
//        if (length == 0 || length == 1) {
//            throw new IllegalArgumentException();
//        }
//        double[][] resultMatrix = new double[length][length];
//        ArrayList<Integer> cycletrack = new ArrayList<>();
//        int index = 0;
//        int index2 = 0;
//        int counter = 0;
//
//        while (counter < adjacencyMatrix.length) {
//            double min = adjacencyMatrix[index][0];
//            for (int j = 0; j < adjacencyMatrix.length; j++) {
//                if (min == 0) {
//                    if (adjacencyMatrix[index][j] != 0) {
//                        min = adjacencyMatrix[index][j];
//                        index2 = j;
//
//                    } else {
//                        if (adjacencyMatrix[index][j] < min) {
//                            min = adjacencyMatrix[index][j];
//                            index2 = j;
//
//                        }
//                    }
//                }
//            }
//            System.out.println(index2);
//
//            cycletrack.add(index);
//
//            if (cycletrack.contains(index2)) {
//                int count = cycletrack.size() - 2;
//                boolean found = false;
//                while (count > -1 && !found) {
//                    int temp = cycletrack.get(count);
//                    for (int j = 0; j < adjacencyMatrix.length; j++) {
//                        if (adjacencyMatrix[temp][j] != 0) {
//                            index = temp;
//                            found = true;
//                        }
//                    }
//                    count--;
//                }
//            } else {
//            adjacencyMatrix[index][index2] = 0;
//            adjacencyMatrix[index2][index] = 0;
//
//            resultMatrix[index][index2] = min;
//            resultMatrix[index2][index] = min;
//
//            index = index2;
//        }
//        counter++;
//    }
//        System.out.println("MST:");
//        return new Graph(resultMatrix);