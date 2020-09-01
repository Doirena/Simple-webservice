package com.dovile.simplewebservice.domain;

/**
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public class Customer {
    private String first_name;
    private String last_name;

    public Customer(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
