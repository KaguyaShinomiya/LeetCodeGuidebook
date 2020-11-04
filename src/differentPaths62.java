public class differentPaths62 {
    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) return 1;
        int upperIdx = n;
        int lowerIdx = 1;
        int upper = n;
        int lower = 1;
        for (int i = 1; i < m; i++) {
            upper *= ++upperIdx;
            lower *= ++lowerIdx;
            System.out.println(upper);
            System.out.println(upperIdx);
            System.out.println(lower);
            System.out.println(lowerIdx);
        }
        return upper / lower;

    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(10, 10));
    }
}
