package Managers;

import Entity.Contact;
import Entity.User;

import java.util.List;

public interface phoneManagerActions {
    User createUser(User user);
    Contact createContact(User user, Contact contact);
    void markContactAsSpam(User user, Contact contact);
    void addToFavourite(User user, Contact contact);
    void addToBlockedList(User user, Contact contact);

    List<String> search(String userId, String input);
}
