package backend.hw5.q1;


class MessageSender extends Thread {

    private final MessageQueue messageQueue;
    private final int senderId;

    /**
     *constructor initializing a MessageSender object with a specific
     * MessageQueue for message retrieval and a unique identifier (senderId)
     * for this particular receiver instance.
     * @param messageQueue
     * @param senderId
     */
    public MessageSender(MessageQueue messageQueue, int senderId) {
        this.messageQueue = messageQueue;
        this.senderId = senderId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) { // Sending 5 messages
            String message = "Message from Sender " + senderId + ": " + i;
            messageQueue.addMessage(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
