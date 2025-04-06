package Entity;

import java.util.ArrayList;
import java.util.List;

import static Utils.Utils.generateId;

public class User {
    private String userId;
    private String name;
    private String phoneNumber;
    private List<Contact> contactList;
    private List<Contact> favouriteContactList;
    private List<Contact> blockedContactList;

    public User(String name, String phoneNumber) {
        this.userId = generateId();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contactList = new ArrayList<>();
        this.favouriteContactList = new ArrayList<>();
        this.blockedContactList = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact contact) {
        if (!contactList.contains(contact)) {
            contactList.add(contact);
        }
    }

    public List<Contact> getFavouriteContactList() {
        return favouriteContactList;
    }

    public void setFavouriteContactList(Contact contact) {
        if (!favouriteContactList.contains(contact)) {
            favouriteContactList.add(contact);
        }
    }

    public List<Contact> getBlockedContactList() {
        return blockedContactList;
    }

    public void setBlockedContactList(Contact contact) {
        if (!blockedContactList.contains(contact)) {
            blockedContactList.add(contact);
        }
    }
}
