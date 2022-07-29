package models;

public class Contacts {
    private int contactID;
    private String contactName;


    public Contacts(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;

    }

    /**
     *
     * @return contactID
     */
    public int getContactID() {

        return contactID;
    }

    /**
     *
     * @return contactName
     */
    public String getContactName() {

        return contactName;
    }


    public String toString() {
        return contactID + " - " + contactName;
    }

}
