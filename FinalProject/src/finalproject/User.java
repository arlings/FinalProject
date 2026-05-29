/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.util.Objects;

/**
 *
 * @author NeWan5443
 */
public class User {
    private String userName;
    private int numWins;
    private static int numUsers;
    
    public User(){
    }
    
    public User(String userName, int numWins) {
        this.userName = userName;
        this.numWins = numWins;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public static int getNumUsers() {
        return numUsers;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.numWins != other.numWins) {
            return false;
        }
        return Objects.equals(this.userName, other.userName);
    }

    public String toString() {
        return "User{" + "userName=" + userName + ", numWins=" + numWins + '}';
    }
    

    
    
    
    
}
