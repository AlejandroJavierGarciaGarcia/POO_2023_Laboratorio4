package com.biblioteca.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.biblioteca.bean.BaseUser;
import com.biblioteca.bean.PremiumUser;
import com.biblioteca.bean.User;
import com.biblioteca.controller.CSVController;

public class UserManager {
    private List<HashMap<String, String>> userCredentials = new ArrayList<HashMap<String, String>>();
    public User activeUser = null;

    public Boolean addUser(String username, String password, String name, boolean isPremium) {
        List<User> userList = loadUsers();
        for (User user : userList) {
            if (user.getUser().equals(username)) {
                activeUser = user;
                return false;
            }
        }
        HashMap<String, String> newUser = new HashMap<>();
        newUser.put("username", username);
        newUser.put("password", password);
        newUser.put("name", name);
        newUser.put("isPremium", String.valueOf(isPremium));
        appendData(newUser);
        return true;
    }

    public boolean checkUser(String username, String password) {
        List<User> userList = loadUsers();

        for (User user : userList) {
            if (user.getUser().equals(username) && user.getPassword().equals(password)) {
                activeUser = user;
                return true;
            }
        }
        return false;
    }

    private List<User> loadUsers() {
        CSVController csvController = new CSVController("resources\\users.csv", userCredentials);
        userCredentials = csvController.importData();
        List<User> userList = new ArrayList<>();

        for (HashMap<String, String> userData : userCredentials) {
            String name = userData.get("name");
            String username = userData.get("username");
            String password = userData.get("password");
            boolean isPremium = Boolean.parseBoolean(userData.get("isPremium"));
            if (isPremium) {
                PremiumUser user = new PremiumUser(name, username, password);
                userList.add(user);
            } else {
                BaseUser user = new BaseUser(name, username, password);
                userList.add(user);
            }
        }

        return userList;
    }

    private void appendData(HashMap<String, String> user) {
        CSVController csvController = new CSVController("resources\\users.csv");
        csvController.appendData(user);
    }

    public User getActiveUser() {
        return activeUser;
    }
}
