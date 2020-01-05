public class Gauss {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        if (matrix.solveGauss()) {
            matrix.printSolution();
        }
    }
}
