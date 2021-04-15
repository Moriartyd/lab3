package sample.utils;

import java.util.Arrays;

public class Solver {

    public static String[] getResult(double[] vector, double[][] v, double[][] w) {
        int len = vector.length;
        double[][] matrix = new double[4][];

        matrix[0] = mult(vector, w, len);
        matrix[1] = func(matrix[0], len);
        matrix[2] = mult(matrix[1], v, len);
        matrix[3] = func(matrix[2], len);

        return buildResult(matrix, len);
    }

    private static double[] func(double[] m, int len) {
        double[] res = new double[len];

        for (int i = 0; i < len; i++) {
            res[i] = (1 / (1 + Math.exp(-m[i])));
        }
        return res;
    }

    private static String[] buildResult(double[][] m, int len) {
        String[] result = new String[4];
        Arrays.fill(result, "");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < len; j++) {
                result[i] += String.format("%10.4f; ", m[i][j]);
            }
        }
        return result;
    }

    private static double[] mult(double[] v, double[][] m, int len) {
        double[] result = new double[v.length];
        Arrays.fill(result, 0.0);

        for (int j = 0; j < len; j++) {
            for (int i = 0; i < len; i++) {
                result[j] += v[j] * m[i][j];
            }
        }
        return result;
    }

}
