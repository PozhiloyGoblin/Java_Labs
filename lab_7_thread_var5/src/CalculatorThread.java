import java.math.BigInteger;

public class CalculatorThread implements Runnable {
    private int number;
    private boolean isActive;
    private BigInteger bigNumber;

    public CalculatorThread(int number) {
        this.number = number;
        this.isActive = true;
        this.bigNumber = BigInteger.valueOf(1);
    }

    public BigInteger getBigNumber() {
        return bigNumber;
    }

    @Override
    public void run() {
        while (isActive) {
            for (int i = 0; i < number; i++) {
                if (isPrime(i)) {
                    bigNumber = bigNumber.multiply(BigInteger.valueOf(i));
                }
            }
            isActive = false;
        }
    }

    private static boolean isPrime(int i) {
        if (i < 1) return false;
        else if (i <= 3) return true;
        else if (i % 2 == 0 || i % 3 == 0) return false;
        else {
            int n = 5;
            while (n * n <= i) {
                if (i % n == 0 || i % (n + 2) == 0) return false;
                n = n + 6;
            }
            return true;
        }
    }
}
