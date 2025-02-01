#include <bits/stdc++.h>
using namespace std;

class User {
private:
    string user_id;
    string name;
    string get_unique_id();

public:
    User(string name) : name(name) {
        user_id = get_unique_id();
    }

    string getUserId() const { return user_id; }
    string getName() const { return name; }
    
};

class Comment {
private:
    string comment_id;
    string parent_id;
    string description;
    vector<Comment> replies;
    User *user;
    string get_unique_id();

public:
    Comment(string description, User *user, string parent_id = "")
        : description(description), user(user), parent_id(parent_id) {
        comment_id = get_unique_id();
    }

    void addReply(Comment reply) {
        replies.push_back(reply);
    }

    vector<Comment> getReplies() const {
        return replies;
    }

    string getCommentID() const { return comment_id; }
    string getParentID() const { return parent_id; }
    string getDescription() const { return description; }
    User* getUser() const { return user; }
};

class Post {
private:
    string post_id;
    string description;
    User *user;
    vector<Comment> comments;
    string get_unique_id();

public:
    Post(string description, User *user) : description(description), user(user) {
        post_id = get_unique_id();
    }

    void addComment(Comment comment) {
        comments.push_back(comment);
    }

    vector<Comment> getComments() const {
        return comments;
    }

    string getPostID() const { return post_id; }
    string getDescription() const { return description; }
    User* getUser() const { return user; }
};

// this class will manage all the post creation, comment creation, etc
class Platform {
private:
    unordered_map<string, User*> user_map;
    unordered_map<string, Post*> post_map;
    unordered_map<string, Comment*> comment_map;

public:
    Platform() {}

    // create user
    void createUser() {

    }
    // create post
    // delete post and its associated comments
    // create comments and nested comments
    // delete comments and their nested comments
    // get all post by user
    // get all comments by post
    // get all comments by user
};