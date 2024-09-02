#include <bits/stdc++.h>
using namespace std;

class Book {
private:
    long book_id;
    string name;
    string description;
    long total_pages;

public:
    Book(const long &_id, const string &_name, const string &_description, const long &_total_pages)
        : book_id(_id), name(_name), description(_description), total_pages(_total_pages){};
    
    void set_id(long id) { book_id = id; }
    void set_total_pages(long pages) { total_pages = pages; }
    void set_name(string name) { name = name; }
    void set_description(string _desc) { description = _desc; }
    long get_id() { return book_id; }
    long get_total_pages() {  return total_pages; }
    string get_name() { return name; }
    string get_description() { return description; }

};

class User {
private: 
    long user_id;
    string name;
    string details;

public:
    User(const long &_id, const string &_name, const string &_details)   
        : user_id(_id), name(_name), details(_details){};
    
    void set_id(long id) { user_id = id; }
    void set_name(string _name) { name = _name; }
    void set_details(string _details) { details = _details; }
    long get_id() { return user_id; }
    string get_name() { return name; }
    string get_details() { return details; }
};

class Library {
private:
    unordered_map<long, Book*> book_map;

public:
    Library() {
        book_map = unordered_map<long, Book*>();
    }
    bool addBook(long id, string name, string description, long pages) {
        if (book_map.find(id) != book_map.end()) {
            return false;
        }
        Book *book = new Book(id, name, description, pages);
        book_map[id] = book;
        return true;
    }
    bool addBook(Book *book) {
        if (book_map.find(book->get_id()) != book_map.end()) {
            return false;
        }
        book_map[book->get_id()] = book;
        return true;
    }
    bool remove_book(long id) {
        if (book_map.find(id) != book_map.end()) {
            return false;
        }
        book_map.erase(id);
        return true;
    }
    bool remove_book(Book *book) {
        long id = book->get_id();
        return remove_book(id);
    }
    Book* find(long id) {
        return book_map.find(id) != book_map.end() ? book_map[id] : nullptr;
    }
};

class UserManager {
private:
    unordered_map<long, User*> user_map;

public:
    UserManager() {
        user_map = unordered_map<long, User*>();
    }
    bool addUser(long id, string name, string details) {
        if (user_map.find(id) != user_map.end()) {
            return false;
        }
        User *user = new User(id, name, details);
        user_map[id] = user;
        return true;
    }
    bool addUser(User *user) {
        if (user_map.find(user->get_id()) != user_map.end()) {
            return false;
        }
        user_map[user->get_id()] = user;
        return true;
    }
    bool remove_user(long id) {
        if (user_map.find(id) != user_map.end()) {
            return false;
        }
        user_map.erase(id);
        return true;
    }
    bool remove_user(User *user) {
        long id = user->get_id();
        return remove_user(id);
    }
    User* find(long id) {
        return user_map.find(id) != user_map.end() ? user_map[id] : nullptr;
    }
};

class Display {
private:
    Book *current_book;
    User *current_user;
    long current_page_no;

    void print_user_name() {
        cout << "User name " << current_user->get_name() << endl;
    }
    void print_book_title() {
        cout << "Title of the book " << current_book->get_name() << endl; 
    }
    void print_book_description() {
        cout << "Book Description: \n " << current_book->get_description() << endl;
    }
    void print_page() {
        cout << "Page no " << current_page_no << endl;
    }

public:
    Display() {
        current_book = nullptr;
        current_user = nullptr;
        current_page_no = 0;
    }

    void display_user(User *user) {
        current_user = user;
        print_user_name();
    }
    void display_book(Book *book) {
        current_book = book;
        current_page_no = 0;
        print_book_title();
        print_book_description();
        print_page();
    }
    void turn_page_forward() {
        if (current_page_no == current_book->get_total_pages()) {
            cout << "Cannot move forward " << endl;
        }
        current_page_no += 1;
        cout << "Turing forward to page no " << current_page_no 
            << " of book " << current_book->get_name() << endl;
    }
    void turn_page_backward() {
        if (current_page_no == 1) {
            cout << "cannot move backward " << endl;
            return;
        }
        current_page_no -= 1;
        cout << "Turing backward to page no " << current_page_no 
            << " of book " << current_book->get_name() << endl;
    }
};  

class OnlineBookReaderSystem {
private:
    Library* library;
    UserManager* user_manager;
    Display* display;
    Book* current_book;
    User* current_user;

public:
    OnlineBookReaderSystem() {
        library = new Library();
        user_manager = new UserManager();
        display = new Display();
        current_book = nullptr;
        current_user = nullptr;
    }
    Library* get_library() {
        return library;
    }
    UserManager* get_user_manager() {
        return user_manager;
    }
    Display* get_display() {
        return display;
    }
    Book* get_active_book() {
        return current_book;
    }
    void setActiveBook(Book* book) {
        current_book = book;
        display->display_book(book);
    }
    User* get_active_user() {
        return current_user;
    }
    void set_active_user(User* user) {
        current_user = user;
        display->display_user(user);
    }
};

int main() {
    OnlineBookReaderSystem* onlineBookReaderSystem = new OnlineBookReaderSystem();

    // Add some books to the library
    onlineBookReaderSystem->get_library()->addBook(1, "Data Structures", "A comprehensive book on data structures.", 300);
    onlineBookReaderSystem->get_library()->addBook(2, "Algorithms", "An in-depth guide to algorithms.", 450);
    onlineBookReaderSystem->get_library()->addBook(3, "Operating Systems", "An overview of modern operating systems.", 500);

    // Attempt to add a duplicate book
    bool addedDuplicateBook = onlineBookReaderSystem->get_library()->addBook(2, "Duplicate Book", "This should not be added.", 100);
    cout << "Attempt to add a duplicate book: " << (addedDuplicateBook ? "Success" : "Failed") << endl;

    // Add some users to the system
    onlineBookReaderSystem->get_user_manager()->addUser(1, "Alice", "Alice loves reading about algorithms.");
    onlineBookReaderSystem->get_user_manager()->addUser(2, "Bob", "Bob is interested in data structures.");
    onlineBookReaderSystem->get_user_manager()->addUser(3, "Charlie", "Charlie is a fan of operating systems.");

    // Attempt to add a duplicate user
    bool addedDuplicateUser = onlineBookReaderSystem->get_user_manager()->addUser(1, "Duplicate User", "This should not be added.");
    cout << "Attempt to add a duplicate user: " << (addedDuplicateUser ? "Success" : "Failed") << endl;

    // Set the active user and book
    User* activeUser = onlineBookReaderSystem->get_user_manager()->find(2); // Bob
    onlineBookReaderSystem->set_active_user(activeUser);

    Book* activeBook = onlineBookReaderSystem->get_library()->find(1); // Data Structures
    onlineBookReaderSystem->setActiveBook(activeBook);

    // Simulate turning pages forward
    onlineBookReaderSystem->get_display()->turn_page_forward();
    onlineBookReaderSystem->get_display()->turn_page_forward();

    // Simulate turning pages backward
    onlineBookReaderSystem->get_display()->turn_page_backward();

    // Display a different book
    Book* anotherBook = onlineBookReaderSystem->get_library()->find(3); // Operating Systems
    onlineBookReaderSystem->setActiveBook(anotherBook);

    // Simulate user switching
    User* anotherUser = onlineBookReaderSystem->get_user_manager()->find(3); // Charlie
    onlineBookReaderSystem->set_active_user(anotherUser);

    // Display the book for the new user
    onlineBookReaderSystem->get_display()->turn_page_forward();
    onlineBookReaderSystem->get_display()->turn_page_backward();

    // Remove a book and try to find it again
    onlineBookReaderSystem->get_library()->remove_book(1); // Remove Data Structures
    Book* removedBook = onlineBookReaderSystem->get_library()->find(1);
    cout << "Trying to find a removed book: " << (removedBook ? "Found" : "Not Found") << endl;

    // Remove a user and try to find them again
    onlineBookReaderSystem->get_user_manager()->remove_user(1); // Remove Alice
    User* removedUser = onlineBookReaderSystem->get_user_manager()->find(1);
    cout << "Trying to find a removed user: " << (removedUser ? "Found" : "Not Found") << endl;

    // Clean up dynamically allocated memory
    delete onlineBookReaderSystem;

    return 0;
}
