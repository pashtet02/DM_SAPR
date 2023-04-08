package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String filepath = reader.readLine();

        List<String> strings;
        try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
            strings = stream.filter(str -> str.length() > 2).collect(Collectors.toList());
        }
        int verticesCount = strings.size();
        int[][] matrix = new int[verticesCount][verticesCount];

        //Fill graph with values
        for (int i = 0; i < verticesCount; i++) {
            String[] valuesFor0Node = strings.get(i).split(" ");
            for (int j = 0; j < valuesFor0Node.length; j++) {
                matrix[i][j] = Integer.parseInt(valuesFor0Node[j]);
            }
        }
        int from = 0;
        int to = verticesCount-1;
        FordFulkersonAlgorithm fordFulkersonAlgorithm = new FordFulkersonAlgorithm();
        int maxFlor = fordFulkersonAlgorithm.maxFlow(matrix, from, to);
        System.out.println("Maximum flow from source " + from + " to sink " + to + " is " + maxFlor);
    }
}
