package entities;

import java.time.Instant;

public class Message {
    private String payload;
    private Instant timeStamp;

    public Message(String payload) {
        this.payload = payload;
        this.timeStamp = Instant.now();
    }

    public String getPayload() {
        return payload;
    }


    public String toString() {
        return "Message{'payload' : " + payload + "\n'timeStamp' : " + timeStamp.toString() + "}";
    }
}