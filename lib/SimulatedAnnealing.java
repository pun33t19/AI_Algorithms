public class SimulatedAnnealing {
  
    
    public static double objectiveFunction(double x) {
      return Math.sin(x) - Math.cos(2*x);
    }
    
    
    public static double simulatedAnnealing(double x, double t, double alpha, double minT) {
      double fx = objectiveFunction(x);
      while (t > minT) {
        double dx = (Math.random() * 2 - 1) * t; 
        double xNext = x + dx;
        double fxNext = objectiveFunction(xNext);
        double delta = fxNext - fx;
        if (delta > 0) {
          x = xNext;
          fx = fxNext;
        } else {
          double p = Math.exp(delta / t); 
          if (Math.random() < p) {
            x = xNext;
            fx = fxNext;
          }
        }
        t *= alpha; 
      }
      return x;
    }
    
    public static void main(String[] args) {
      double x = 0; 
      double t = 1;
      double alpha = 0.95;
      double minT = 0.001;
      double result = simulatedAnnealing(x, t, alpha, minT);
      System.out.println("Maximum point found: x = " + result + ", f(x) = " + objectiveFunction(result));
    }
  }