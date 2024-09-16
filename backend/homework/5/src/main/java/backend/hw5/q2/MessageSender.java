package backend.hw5.q2;

class MessageSender implements Runnable {
    private MessageQueue messageQueue;
    private int senderId;

    /**
     * initializing a MessageSender object,
     * associating it with a specific MessageQueue
     * for sending messages and assigning a
     * unique identifier (senderId) to the sender instance.
     * @param messageQueue
     * @param senderId
     */

    public MessageSender(MessageQueue messageQueue, int senderId) {
        this.messageQueue = messageQueue;
        this.senderId = senderId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String message = "Message from Sender " + senderId + ": " + i;
            messageQueue.addMessage(message);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}


