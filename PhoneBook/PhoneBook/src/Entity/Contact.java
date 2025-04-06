package Entity;

import static Utils.Utils.generateId;

public class Contact {
    private String contactId;
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.contactId = generateId();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
