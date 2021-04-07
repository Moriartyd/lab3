package sample.utils;

public class Exampler {

    public static String getVector(int k) {
        String vector = new String();
        for (int i = 0; i < k; i++) {
            vector += Double.toString((double) (Math.round(Math.random() * 100)) / 100);
            vector += ";";
        }
        return vector;
    }

    public static String getMatrix(int k) {
        String matrix = new String();
        for (int i = 0; i < k; i++) {
            matrix += getVector(k);
            matrix += "\n";
        }
        return matrix;
    }
}
