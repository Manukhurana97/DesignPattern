package entities;

import java.util.*;

public class Rider {
    private final String id;
    private String name;
    private String phoneNumber;

    public Rider(String name, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() { return id; }
}
