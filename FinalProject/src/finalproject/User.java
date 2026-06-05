package finalproject;

import java.util.Objects;

public class User {
    private String userName;
    private int score;
    private static int numUsers;
    
    public User(){
    }
    
    public User(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        if (this.score != other.score) {
            return false;
        }
        return Objects.equals(this.userName, other.userName);
    }

    public String toString() {
        return "User{" + "userName=" + userName + ", score=" + score + '}';
    }
}
