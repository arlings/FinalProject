/*
L Necakov, N Wang, A Zalli
May 21-June 10, 2026
Create a user class
*/
package finalproject;

import java.util.Objects;

public class User {
    private String userName;
    private int score;
    private String skin;

    /**
     * User constructor
     * @param userName- user name
     * @param score - user number of wins
     */
    public User(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }
    
    /**
     * user constructor
     * @param userName- user name
     * @param score- user number of wins
     * @param skin - user skin
     */
    public User(String userName, int score, String skin) {
        this.userName = userName;
        this.score = score;
        this.skin = skin;
    }

    /**
     * get the user name
     * @return - the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * set the user name
     * @param userName - the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the number of wins(score)
     * @return - the number of wins
     */
    public int getScore() {
        return score;
    }

    /**
     * set the number of wins(score)
     * @param score - the new number of wins
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * set the skin
     * @param skin - the skin
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    /**
     * get the skin
     * @return - the skin
     */
    public String getSkin() {
        return skin;
    }

    /**
     * comparing an object to the current instance of the user object
     * @param obj
     * @return 
     */
    public boolean equals(Object obj) {
        if (this == obj) {//if they have the same reference
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {//comparing classes
            return false;
        }
        final User other = (User) obj;
        if (this.score != other.score) {//if the scores are different
            return false;
        }
        return Objects.equals(this.userName, other.userName);
    }

    /**
     * returns the status of the current instance of the user class
     * @return - the status as a String
     */
    public String toString() {
        return userName + ", score = " + score;
    }
}
