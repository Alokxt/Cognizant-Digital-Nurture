package DataStructuresAndAlgorithms;

public class FinanceRecursion{

    public static double futureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return futureValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double currentValue = 10000;
        double growthRate = 0.08;
        int years = 5;

        double predictedValue = futureValue(currentValue, growthRate, years);

        System.out.println("Current Value: " + currentValue);
        System.out.println("Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Years: " + years);
        System.out.println("Future Value: " + predictedValue);
    }
}