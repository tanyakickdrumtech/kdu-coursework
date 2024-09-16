package backend.hw5.q3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorialAndFactors {
    private static final Logger logger = Logger.getLogger(FactorialAndFactors.class.getName());

    public static void main(String[] args) {
        int number = 8;

        // here we are creating the threads for the factorial and get factors functionality
        FactorialThread factorialThread = new FactorialThread(number);
        FactorsThread factorsThread = new FactorsThread(number);

        // Both the threads are starting
        factorialThread.start();
        factorsThread.start();

        // Waiting for both threads to finish
        try {
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Main thread interrupted", e);
            Thread.currentThread().interrupt();
        }

        // Main thread finishes last , it terminates when our program terminates
        logger.log(Level.INFO, "Main thread finished.");
    }

    static class FactorialThread extends Thread {
        private int number;

        FactorialThread(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                long factorialResult = calculateFactorial(number);
                logger.log(Level.INFO, "Factorial of {0}: {1}", new Object[]{number, factorialResult});
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "FactorialThread interrupted", e);
                Thread.currentThread().interrupt();
            }
        }

        /**
         *
         * @param n
         * @return
         * @throws InterruptedException
         */

        private long calculateFactorial(int n) throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            if (n == 0 || n == 1) {
                return 1;
            }

            Thread.sleep(500);

            return n * calculateFactorial(n - 1);
        }
    }

    static class FactorsThread extends Thread {
        private int number;

        FactorsThread(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                logger.log(Level.INFO, "Factors of {0}: {1}", new Object[]{number, getFactors(number)});
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "FactorsThread interrupted", e);
                Thread.currentThread().interrupt();
            }
        }

        /**
         * this method is getting the factors of the input number
         * @param n
         * @return
         * @throws InterruptedException
         */
        private String getFactors(int n) throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            StringBuilder factors = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    factors.append(i).append(" ");
                }
            }

            Thread.sleep(500);

            return factors.toString();
        }
    }

    /**
     * this private method is calculating the factorial
     * @param n
     * @return
     */

    private static long calculateFactorial(int n) {
        return (n == 0 || n == 1) ? 1 : n * calculateFactorial(n - 1);
    }
}
