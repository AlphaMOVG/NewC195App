package models;


/**
 * Users class.
 * */
public class Users {

    public int userID;
    public String userName;
    public String userPassword;

    /**
     * Users constructor.
     * @param userID
     * @param userName
     * @param userPassword
     * */
    public Users(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;

    }

    /**
     * @return userID
     */
    public int getUserID() {

        return userID;
    }

    /**
     * @return userName
     */
    public String getUserName() {

        return userName;
    }

    /**
     * @return userPassword
     */
    public String getUserPassword() {

        return userPassword;
    }

    @Override
    public String toString(){

        return userID + "-" + userName;
    }
}

