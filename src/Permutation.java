import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Permutation {
    public static int k, n;
    public static HashSet<Integer> set;

    public static void main(String[] args) {
        initiate();
        permutation(0);
    }

    public static void initiate() {
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
        n = scanner.nextInt();
        scanner.close();
        set = new LinkedHashSet<Integer>(n);
    }

    public static void permutation(int position) {
        if (position == n) {
            printRow(set);
        } else {
            for (int i = 0; i < k; i++) {
                if (set.add(i)) {
                    permutation(position + 1);
                    set.remove(i);
                }
            }
        }
    }

    public static void printRow(Collection<?> row) {
        System.out.println(row.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
