package sample.utils;

import java.util.Arrays;

public class Exampler {
    public static String getVector(int k, boolean denom) {
        String vector = new String();
        for (int i = 0; i < k; i++) {
            vector += Double.toString((double) (Math.round((denom ? (1/(double) k) :Math.random()) * 100)) / 100);
            vector += ";";
        }
        return vector;
    }

    public static String getMatrix(int k) {
        String matrix = new String();
        for (int i = 0; i < k; i++) {
            matrix += getVector(k, true);
            matrix += "\n";
        }
        return matrix;
    }

}
