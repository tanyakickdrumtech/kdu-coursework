package backend.hw5.q2;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            senderThreadPool.submit(new MessageSender(messageQueue, i));
        }

        for (int i = 1; i <= 3; i++) {
            receiverThreadPool.submit(new MessageReceiver(messageQueue));
        }

        //to close or shut down the thread when the work is done
        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}

