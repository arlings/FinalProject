/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
User class that describes a user, and stores information on their score in chess, their username, adn their customizable skin.
*/

package finalproject;

import java.util.Objects;

public class User {
    private String userName;
    private int score;
    private String skin;

    /**
     * The primary constructor for a user.
     * @param userName The users username as a String.
     * @param score The users score as an integer.
     */
    public User(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }
    
    /**
     * The secondary constructor for a user.
     * @param userName The users username as a String.
     * @param score The users score as an integer.
     * @param skin The users skin.
     */
    public User(String userName, int score, String skin) {
        this(userName, score);
        this.skin = skin;
    }

    /**
     * Return the users username as a string.
     * @return The users username as a string.
     */
    public String getUserName() {
        return userName;
    }
    
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Return the users score as an integer.
     * @return The users username as an integer.
     */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    /**
     * Return the users skin as a String. 
     * @return The users skin as a String. This is a prefix that is placed at the start of each file name for the image files of a piece.
     */
    public String getSkin() {
        return skin;
    }

    /**
     * Comparing an object to the current instance of the user object.
     * @param obj The user object that is being compared
     * @return Whether or not they have the same score stored in their objects
     */
    public boolean equals(Object obj) {
        if (this == obj) { // If they have the same reference,
            return true; 
        }
        if (obj == null) { // If the object being compared is null,
            return false;
        }
        if (getClass() != obj.getClass()) { // If they are of differing classes,
            return false;
        }
        final User other = (User) obj; 
        if (this.score != other.score) { // If their scores are different from one another,
            return false;
        }
        return Objects.equals(this.userName, other.userName); 
        // Finally, if none of these cases are hit, compare if the objects are identical as a fail safe.
    }

    /**
     * Returns basic information of the current instance of the user class.
     * @return The information as a string
     */
    public String toString() {
        return userName + ", score = " + score;
    }
}
