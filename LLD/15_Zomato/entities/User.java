package entities;

import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private String mobileNumber;
    private String address;

    public User(String name, String mobileNumber, String address) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public String getId() { return id; }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
