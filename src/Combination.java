import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Combination {
    public static List<Integer> array;
    public static int k, n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
        n = scanner.nextInt();
        scanner.close();
        initiate();
        combinationSearch(0, 0);
    }

    public static BigInteger lucasNumber(int n) {
        BigInteger[] lucas = new BigInteger[n + 1];
        lucas[0] = BigInteger.TWO;
        lucas[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            lucas[i] = lucas[i-1].add(lucas[i-2]);
        }
        return lucas[n];
    }

    public static BigInteger fibonacciNumber(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }

        int signum = 1;
        if (n < 0) {
            signum = (n + 1) % 2 == 0 ? 1 : -1;
            n = -n;
        }

        BigInteger[] fibonacci = new BigInteger[n];
        fibonacci[0] = BigInteger.ONE;
        fibonacci[1] = BigInteger.ONE;
        for (int i = 2; i < n; i++) {
            fibonacci[i] = fibonacci[i-1].add(fibonacci[i-2]);
        }
        return fibonacci[n -1].multiply(BigInteger.valueOf(signum));
    }

    public static void initiate() {
        array = new ArrayList<Integer>(k);
        for (int i = 0; i < k; i++) {
            array.add(i);
        }
    }

    public static void combinationSearch(int pos, int maxUsed) {
        if (pos == k) {
            printRow(array);
        } else {
            for(int i = maxUsed; i < n; i++) {
                array.set(pos, i);
                combinationSearch(pos+1, i+1);
            }
        }
    }

    public static long surjective(int n, int k) {
        long S = 0;
        for (int i = 1; i <= k; i++) {
            S += (int) (combination(k, i) * Math.pow(i, n)) * ((k - i) % 2 == 0 ? 1 : -1);
        }
        return S;
    }

    public static long combination(int n, int k) {
        long result = 1;
        for (int i = n; i > n - k; i--) {
            result *= i;
        }
        System.out.println(result + " " + factorial(k));
        return (int) (result / factorial(k));
    }

    public static long factorial(int i) {
        if (i > 1) {
            return i * factorial(i - 1);
        } else  if (i == 1) {
            return 1;
        } else throw new UnsupportedOperationException("Negative factorial");
    }

    public static void printRow(Collection<?> row) {
        System.out.println(row.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
