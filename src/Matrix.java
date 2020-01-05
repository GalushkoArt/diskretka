import java.util.Scanner;

public class Matrix {
    private int n, m;
    private double[][] A;
    private double[] b, x;

    public Matrix() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        A = new double[n][m];
        b = new double[n];
        x = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = scanner.nextDouble();
            }
            b[i] = scanner.nextDouble();
        }

        scanner.close();
    }

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.A = new double[n][m];
        this.b = new double[n];
        this.x = new double[n];
    }

    public void forwardGauss() {
        for (int i = 0; i < n; i++) {
            swapRows(i, findMaxRow(i));

            if (Math.abs(A[i][i]) < 1e-9) {
                System.out.println("NO");
                return;
            }

            for (int j = i + 1; j < n; j++) {
                double alpha = A[j][i] / A[i][i];
                b[j] -= alpha * b[i];

                for (int k = i; k < m; k++) {
                    A[j][k] -= alpha * A[i][k];
                }
            }
        }
    }

    public int findMaxRow(int column) {
        int max = column;

        for (int j = column + 1; j < n; j++) {
            if (Math.abs(A[j][column]) > Math.abs(A[max][column])) {
                max = j;
            }
        }

        return max;
    }

    public void solutionEchelon() {
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;

            for (int j = i + 1; j < m; j++) {
                sum += A[i][j] * x[j];
            }

            x[i] = (b[i] - sum) / A[i][i];
        }
    }

    public boolean solveGauss() {
        if (checkMatrix()) {
            forwardGauss();
            solutionEchelon();
            return true;
        }
        return false;
    }

    public Matrix leastSquaresMatrix() {
        Matrix result = new Matrix(m, m);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                result.A[i][j] = scalarProduct(this.getColumn(j), this.getColumn(i));
            }
            result.b[i] = scalarProduct(this.b, this.getColumn(i));
        }
        result.solveGauss();
        return result;
    }

    public void swapRows(int row1, int row2) {
        double[] tempA = A[row1];
        A[row1] = A[row2];
        A[row2] = tempA;

        double tempB = b[row1];
        b[row1] = b[row2];
        b[row2] = tempB;
    }

    public double scalarProduct(double[] a, double[] b) {
        double result = 0.0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    public boolean checkMatrix() {
        if (n < m) {
            System.out.println("INF");
            return false;
        } else if (n > m) {
            System.out.println("NO");
            return false;
        } else
            return true;
    }

    public boolean checkSolutions() {
        for (int i = 0; i < n; i++) {
            if (Math.abs(x[i]) < 1e-9) {
                return false;
            }
        }
        return true;
    }

    public void printMatrix() {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println(b[i]);
        }
    }

    public void printSolution() {
        if (n < m && !checkSolutions()) {
            System.out.println("INF");
        } else {
            System.out.println("YES");

            for (int i = 0; i < n; i++) {
                System.out.print(x[i] + " ");
            }
            System.out.println();
        }
    }

    public double[] getColumn(int i) {
        double[] column = new double[A.length];
        for (int j = 0; j < A.length; j++) {
            column[j] = A[j][i];
        }
        return column;
    }

    public double[] getRow(int i) {
        return A[i];
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public double[][] getA() {
        return A;
    }

    public double[] getB() {
        return b;
    }

    public double[] getX() {
        return x;
    }
}
