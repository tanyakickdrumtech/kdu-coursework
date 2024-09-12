package backend.hw5.q1;

import java.util.logging.Level;
import java.util.logging.Logger;

class MessageReceiver extends Thread {

    private final MessageQueue messageQueue;
    private final int receiverId;
    private static final Logger logger = Logger.getLogger(MessageReceiver.class.getName());

    /**
     * constructor initializing a MessageReceiver object with a specific
     * MessageQueue for message retrieval and a unique identifier (receiverId)
     * for this particular receiver instance.
     * @param messageQueue
     * @param receiverId
     */
    public MessageReceiver(MessageQueue messageQueue, int receiverId) {
        this.messageQueue = messageQueue;
        this.receiverId = receiverId;
    }

    @Override
    public void run() {
        while (true) {
            String message = messageQueue.getMessage();
            if (message != null) {
                logger.log(Level.INFO, "Receiver {0} received: {1}", new Object[]{receiverId, message});
            }
        }
    }
}

