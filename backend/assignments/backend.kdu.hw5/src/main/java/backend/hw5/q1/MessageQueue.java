package backend.hw5.q1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class MessageQueue {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    /**
     *adding a message to the queue, notifies waiting threads
     * that a new message is available, and handles any potential
     * interruptions during the process.
     * @param message
     */
    public void addMessage(String message) {
        try {
            queue.put(message);
            synchronized (this) {
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * method retrieves a message from the queue
     * while handling synchronization and interruptions
     * to ensure safe and reliable message retrieval.
     * @return
     */

    public String getMessage() {
        try {
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait();
                }
            }
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}