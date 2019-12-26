import java.util.Scanner;

public class Matrix {
    int n, m;
    double[][] A;
    double[] b, x;

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

    public void forwardGauss() {
        for (int i = 0; i < n; i++) {
            int max = i;

            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[max][i])) {
                    max = j;
                }
            }

            swapRows(i, max);

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

    public void solutionEchelon() {
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;

            for (int j = i + 1; j < m; j++) {
                sum += A[i][j] * x[j];
            }

            x[i] = (b[i] - sum) / A[i][i];
        }
    }

    public void swapRows(int row1, int row2) {
        double[] tempA = A[row1];
        A[row1] = A[row2];
        A[row2] = tempA;

        double tempB = b[row1];
        b[row1] = b[row2];
        b[row2] = tempB;
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
