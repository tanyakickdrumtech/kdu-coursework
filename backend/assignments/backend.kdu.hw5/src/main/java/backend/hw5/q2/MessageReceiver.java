package backend.hw5.q2;

import java.util.logging.Level;
import java.util.logging.Logger;

class MessageReceiver implements Runnable {
    private static final Logger logger = Logger.getLogger(MessageReceiver.class.getName());

    private MessageQueue messageQueue;

    /**
     * Initializing a MessageReceiver object,
     * associating it with a particular MessageQueue
     * from which it will receive messages.
     *
     * @param messageQueue The MessageQueue from which the receiver will receive messages.
     */
    public MessageReceiver(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = messageQueue.getMessage();
                if (message != null) {
                    logger.log(Level.INFO, "Receiver received: {0}", message);
                }
            }
        } finally {
            logger.log(Level.INFO, "Receiver thread finished.");
        }
    }
}
