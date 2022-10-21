public class Newton {
    public static double f(double x) {
        return x * x * x + 4 * x - 6;
    }

    public static double df(double x) {
        return 3 * x * x + 4;
    }

    public static double ddf(double x) {
        return 6 * x;
    }

    public static double newtonsMethod(double x, double e) {
        double newX = x;
        int n = 1;
        do {
            x = newX;
            newX = x - (f(x)) / (df(x));
            System.out.println(n++ + "\t " + newX);
        } while (Math.abs(newX - x) >= e);
        System.out.println("A posteriori evaluation: " + (n-1));
        return newX;
    }

    public static double modifiedNewtonsMethod(double x, double e) {
        double newX = x;
        double x0 = x;
        int n = 1;
        do {
            x = newX;
            newX = x - (f(x)) / (df(x0));
            System.out.println(n++ + "\t " + newX);
        } while (Math.abs(newX - x) >= e);
        System.out.println("A posteriori evaluation: " + (n-1));
        return newX;
    }

    public static void main(String[] args) {
        double e = 0.0001;

        System.out.println("Newtons Method :");
        double res1 = newtonsMethod(2, e);
        System.out.println("Result: " + res1);


        System.out.println("Modified Newtons Method :");
        double res2 = modifiedNewtonsMethod(2, e);
        System.out.println("Result: " + res2);

    }
}
