package sample.utils;

public class Parser {

    public static final String SQR_ERROR = "Матрцы должны быть квадратными";

    public static double[] parseVector(String str) {
        //Поддерживает паттерны: "1,4;1,43" и "1.4;1.43"
        String[] line = str.indexOf(",") == -1 ? str.split(";") : str.replace(",", ".").split(";");
        double[] vector = new double[line.length];
        for (int i = 0; i < line.length; i++) {
            vector[i] = Double.parseDouble(line[i]);
        }
        return vector;
    }

    public static double[][] parseMatrix(String str) {
        String[] rows = str.split("\n");
        int len = rows.length;
        if (len != rows[0].split(";").length) {
            throw new RuntimeException(SQR_ERROR);
        }
        double[][] matrix = new double[len][len];
        for (int i = 0; i < len; i++) {
            matrix[i] = parseVector(rows[i]);
        }
        return matrix;
    }
}
