package Managers;

import Entity.Contact;
import Entity.Global;
import Entity.User;
import Trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Utils.Utils.isValidPhoneNumber;

public class PhoneManager implements phoneManagerActions{
    private Map<String, User> userMap;
    private Map<String, Contact> contactMap;
    private Map<String, Trie> namesList;
    private Map<String, Trie> numberList;

    private Global globalModel;


    public PhoneManager() {
        userMap = new HashMap<>();
        contactMap = new HashMap<>();
        namesList = new HashMap<>();
        numberList = new HashMap<>();
        globalModel = Global.getInstance();
    }

    @Override
    public User createUser(User user) {
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            System.out.println("wrong phone number " + user.getPhoneNumber() + " of User " + user.getUserId() );
            return null;
        }
        userMap.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Contact createContact(User user, Contact contact) {
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            System.out.println("wrong phone number " + user.getPhoneNumber() + " of User " + user.getUserId() );
            return null;
        }
        user.addContact(contact);
        contactMap.put(contact.getContactId(), contact);

        namesList.computeIfAbsent(user.getUserId(), k -> new Trie()).insert(contact.getName());
        numberList.computeIfAbsent(user.getUserId(), k -> new Trie()).insert(contact.getPhoneNumber());
        return contact;
    }

    @Override
    public void markContactAsSpam(User user, Contact contact) {
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            System.out.println("wrong phone number " + user.getPhoneNumber() + " of User " + user.getUserId() );
            return;
        }
        globalModel.reportSpam(contact.getPhoneNumber());
        user.setBlockedContactList(contact);
    }

    @Override
    public void addToFavourite(User user, Contact contact) {
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            System.out.println("wrong phone number " + user.getPhoneNumber() + " of User " + user.getUserId() );
            return;
        }
        user.setFavouriteContactList(contact);
    }

    @Override
    public void addToBlockedList(User user, Contact contact) {
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            System.out.println("wrong phone number " + user.getPhoneNumber() + " of User " + user.getUserId() );
            return;
        }
        user.setBlockedContactList(contact);
    }

    @Override
    public List<String> search(String userId, String input) {
        if (!namesList.containsKey(userId) || !numberList.containsKey(userId)) {
            System.out.println("No result while searching for user " + userId);
            return new ArrayList<String>();
        }
        return phoneBookSearch(namesList.get(userId), numberList.get(userId), input);
    }

    private List<String> phoneBookSearch(Trie nameTrie, Trie numberTrie, String input) {
        List<String> nameResult = nameTrie.search(input);
        List<String> numberResult = numberTrie.search(input);
        nameResult.addAll(numberResult);
        return nameResult;
    }
}
