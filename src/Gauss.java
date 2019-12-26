public class Gauss {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        matrix.printMatrix();

        if (matrix.checkMatrix()) {
            matrix.forwardGauss();
            matrix.printMatrix();
            matrix.solutionEchelon();
            matrix.printSolution();
            matrix.printMatrix();
        }
    }
}
