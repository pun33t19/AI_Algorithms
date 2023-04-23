public class HillClimbing {
    public static void main(String[] args) {
        double x = 0;
        double stepSize = 0.1;
        int maxIter = 1000;

        double res = hillClimb(x, stepSize, maxIter);

        System.out.println("The point found is: " + x + " , and f(x) is: " + objectiveFunc(res));
    }

    private static double hillClimb(double x, double stepSize, int maxIter) {
        double fx = objectiveFunc(x);

        for (int i = 0; i < maxIter; i++) {
            double dx = (Math.random() * 2 - 1);
            double xNext = x + dx;

            double fxNext = objectiveFunc(xNext);

            if (fxNext > fx) {
                fx = fxNext;
                x = xNext;
            }
        }

        return x;
    }

    static double objectiveFunc(double x) {
        return Math.sin(x) - Math.cos(2 * x);
    }
}
