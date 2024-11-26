package application;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Data {
    private Map<Integer, Integer> values;

    public Data() {
        this.values = new HashMap<>();
    }

    public Map<Integer, Integer> getValues() {
        return this.values;
    }

    public void setValues(ArrayList<String> lines) {
        lines.stream().forEach(line -> {
            String[] parts = line.split(" ");

            int year = Integer.parseInt(parts[0]);
            int rank = Integer.parseInt(parts[1]);

            this.values.put(year, rank);
            System.out.println("Year: " + year + ", Rank: " + rank);
        });
    }
    public InputStream getFile() {
        return getClass().getClassLoader().getResourceAsStream("data.txt");
    }

    public ArrayList<String> readLines() {
        InputStream inputStream = this.getFile();

        if (inputStream == null) {
            System.out.println("File not found!");
            return new ArrayList<>();
        }

        return this.readFile(inputStream);
    }

    public ArrayList<String> readFile(InputStream inputStream) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lines;
    }


}
