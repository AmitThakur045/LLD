import Entity.Contact;
import Entity.User;
import Managers.PhoneManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PhoneManager manager = new PhoneManager();

        User user = new User("Avinash","9314632311");
        manager.createUser(user);

        Contact c1 = new Contact("Abhishek", "8302999879");
        Contact c2 = new Contact("Ankur", "9829761019");
        Contact c3 = new Contact("Madan", "9414048879");
        Contact c4 = new Contact("Mamta", "9414048873");
        Contact c5 = new Contact("Abhijeet", "8302999873");

        manager.createContact(user,c1);
        manager.createContact(user,c2);
        manager.createContact(user,c3);
        manager.createContact(user,c4);
        manager.createContact(user,c5);

        manager.addToBlockedList(user,c1);
        manager.addToBlockedList(user,c2);

        manager.markContactAsSpam(user, c2);
        manager.markContactAsSpam(user, c3);

        User user2 = new User("Avinashs","9314632312");
        manager.createUser(user2);

        Contact c11 = new Contact("Abhishesk", "8302999879");
        Contact c22 = new Contact("Anskur", "9829761019");
        Contact c33 = new Contact("Madsan", "9414048879");
        Contact c44 = new Contact("Mamsta", "9414048873");
        Contact c55 = new Contact("Abhsijeet", "8302999873");

        manager.createContact(user2, c11);
        manager.createContact(user2, c22);
        manager.createContact(user2, c33);
        manager.createContact(user2, c44);
        manager.createContact(user2, c55);

        List<String> searchRes1 = manager.search(user.getUserId(),"Abhi");
        System.out.println(searchRes1);

        List<String> searchRes2 = manager.search(user.getUserId(),"ma");
        System.out.println(searchRes2);

        List<String> searchRes3 = manager.search(user.getUserId(),"av");
        System.out.println(searchRes3);

        List<String> searchRes11 = manager.search(user2.getUserId(),"abhi");
        System.out.println(searchRes11);

        List<String> searchRes22 = manager.search(user2.getUserId(),"94");
        System.out.println(searchRes22);

        List<String> searchRes33 = manager.search(user2.getUserId(),"av");
        System.out.println(searchRes33);

        List<String> searchRes44 = manager.search(user2.getUserId(),"Abh");
        System.out.println(searchRes44);

    }
}
