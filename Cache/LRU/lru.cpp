#include <bits/stdc++.h>
using namespace std;
using namespace chrono;

typedef time_point<steady_clock> DateTime;

template <typename Cache_Key, typename Cache_Value>
class LRUCache {
    private:
    struct Item {
        Item(
            const Cache_Key &_key,
            const Cache_Value &_value,
            const milliseconds &_duration
        ): key(_key), value(_value), expiration_time(steady_clock::now() + _duration) {}

        Cache_Key key;
        Cache_Value value;
        DateTime expiration_time;

        // Method to check if the item is expired
        bool isExpired() const {
            return steady_clock::now() > expiration_time;
        }

        // Method to get the time remaining until expiration in milliseconds
        milliseconds timeRemaining() const {
            return duration_cast<milliseconds>(expiration_time - steady_clock::now());
        }
    };

    // Defined common datatypes
    typedef shared_ptr<Item> ItemPtr;
    typedef list<ItemPtr> LruList;
    typedef typename list<ItemPtr>::iterator ItemPtrIndex;
    typedef unordered_map<Cache_Key, ItemPtrIndex> LruMap;

    // Declaring variables via custom datatypes
    LruList lru_list;
    milliseconds max_time_to_live;
    LruMap lru_map;
    long max_capacity;

    bool isKeyExist(const Cache_Key &key) {
        bool has_key = lru_map.count(key) != 0;
        if (has_key) {
            ItemPtrIndex key_index = lru_map[key];
            ItemPtr &curr_key = *key_index;

            if (curr_key->isExpired()) {
                lru_map.erase(key);
                lru_list.erase(key_index);
                has_key = false;
            }
        }
        return has_key;
    }
    
    DateTime getNewExpirationDatetime() {
        return steady_clock::now() + max_time_to_live;
    }

    void makeKeyLatest(const Cache_Key &key) {
        if (lru_map.count(key)) {
            ItemPtrIndex key_index = lru_map[key];
            const ItemPtr &curr_key = *key_index;

            lru_list.push_front(curr_key);
            lru_map[key] = lru_list.begin();

            lru_list.front()->expiration_time = getNewExpirationDatetime();            

        }
    }

    void removeLastElement() {
        Cache_Key key = lru_list.back()->key;
        lru_list.pop_back();
        lru_map.erase(key);
    }

    public:
    LRUCache(const long &_max_capacity, const milliseconds &_ttl = milliseconds(3600))
        : max_capacity(_max_capacity), max_time_to_live(_ttl) {}

    Cache_Value get(const Cache_Key &key) {
        if (!isKeyExist(key)) {
            throw runtime_error("Key not found");
        }
        makeKeyLatest(key);
        return lru_list.front()->value;
    }

    void put(const Cache_Key &key, const Cache_Value &value) {
        if (isKeyExist(key)) {
            makeKeyLatest(key);
            lru_list.front()->value = value;
        } else {
            if (lru_map.size() >= max_capacity) {
                removeLastElement();
            }
            ItemPtr item_ptr = make_shared<Item>(key, value, max_time_to_live);
            lru_list.push_front(item_ptr);
            lru_map[key] = lru_list.begin();
        }
    }
};


int main() {
    LRUCache<int, int> cache(5, milliseconds(5000));

    while (true) {
        cout << "Enter the choice:" << endl;
        cout << "1. Get Element" << endl;
        cout << "2. Put Element" << endl;
        cout << "3. Exit" << endl;

        int ch, key, val;
        cin >> ch;

        switch (ch) {
        case 1:
            cout << "Enter the key: ";
            cin >> key;
            try {
                int value = cache.get(key);
                cout << "Value: " << value << endl;
            } catch (const runtime_error &e) {
                cout << e.what() << endl;
            }
            break;

        case 2:
            cout << "Enter the key: ";
            cin >> key;
            cout << "Enter the value: ";
            cin >> val;
            cache.put(key, val);
            cout << "Inserted (" << key << ", " << val << ") into the cache." << endl;
            break;

        case 3:
            cout << "Exiting..." << endl;
            return 0;

        default:
            cout << "Invalid choice, please try again." << endl;
        }
    }


    return 0;
}
