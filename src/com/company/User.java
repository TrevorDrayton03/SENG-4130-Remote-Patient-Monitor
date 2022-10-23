package com.company;

import javax.swing.*;
import java.util.HashMap;

public class User {

    private static User user = new User();

    private HashMap<String, String> users = new HashMap<>();

    /**
     * Singleton method for User object
     *
     * @return User object.
     */
    public static User getInstance() {
        return user;
    }

    /**
     * User Singleton constructor which runs getInstance() upon being called.
     *
     * @return User object.
     * @see public static User getInstance()
     */
    public User() {
        addUser("trevor", "pw123");
        addUser("sanyam", "pw123");
        getInstance();
    }

    public void removeUser(String username) {
        this.users.remove(username);
    }

    public void addUser(String username, String password) {
        this.users.put(username, password);
    }

    public boolean isCorrectUser(String username) {
        for (String i : this.users.keySet()) {
            if (i.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrectPassword(String username, String password) {
        for (String i : this.users.keySet()) {
            if (i.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean doLogin(String username, String password) {
        if (user.isCorrectUser(username)) {
            if (user.isCorrectPassword(username, password)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Password");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username");
        }
        return false;
    }
}