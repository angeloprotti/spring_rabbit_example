package br.com.example.rabbitone.rabbit.receive;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 *  This class no longer usable. See {@link RabbitReceiver}
 */
@Deprecated
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received in Rabbit One < " + message + "> ");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
