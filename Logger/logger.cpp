#include <bits/stdc++.h>
using namespace std;

class Process {
public:
    Process(const string &_id, const long long &_start_time)
        : process_id(_id), start_time(_start_time), end_time(-1){}

    string getProcessId() const { return process_id; };
    long long getStartTime() const { return start_time; };
    long long getEndTime() const { return end_time; };
    void setEndTime(const long long time) { end_time = time; };

private:
    string process_id;
    long long start_time;
    long long end_time;
};

class LogClient {
public:
    virtual void start(const string &process_id, const long long &start_time) = 0;
    virtual void end(const string &process_id) = 0;
    virtual string poll() = 0;
    virtual void size() = 0;
    virtual ~LogClient() = default;
};


class Logger : public LogClient {
public:
    Logger(): task_list(5) {
        for (auto &scheduler: task_list) {
            scheduler = thread([this]() {
                while (true) {
                    function<void()> task;
                    {
                        unique_lock<std::mutex> lock(queue_mutex);
                        condition.wait(lock, [this] { return !task_queue.empty(); });
                        task = move(task_queue.front());
                        task_queue.pop();
                    }
                    task();
                }
            });
        }
    }

    ~Logger() {
        for (auto& thread: task_list) {
            if (thread.joinable()) {
                thread.join();
            }
        }
    }

    void start(const string &process_id, const long long &start_time) override {
        scheduleTask(process_id, [this, process_id, start_time]() {
            shared_ptr<Process> process = make_shared<Process>(process_id, start_time);
            processes[process_id] = process;
            queue_mp[start_time] = process; 
        });
    }

    void end(const string &process_id) override {
        scheduleTask(process_id, [this, process_id]() {
            lock_guard<std::mutex> lock(mutex);
            auto now = chrono::system_clock::now().time_since_epoch().count();
            processes[process_id]->setEndTime(now);
            if (!queue_mp.empty() && queue_mp.begin()->second->getEndTime() != -1) {
                logCompletedProcess();
            }
        });
    }

    string poll() override {
        lock_guard<std::mutex> lock(mutex);
        if (!queue_mp.empty() && queue_mp.begin()->second->getEndTime() != -1) {
            return logCompletedProcess();
        }
        return "";
    }

    void size() override {
        cout<<"processes "<<processes.size()<<endl;
        cout<<"queue "<<queue_mp.size()<<endl;
        cout<<"task_list "<<task_list.size()<<endl;   
        cout<<"task_queue "<<task_queue.size()<<endl;
        cout<<endl;
    }
    

private:
    map<string, shared_ptr<Process>> processes;
    map<long long, shared_ptr<Process>> queue_mp;
    std::mutex mutex, queue_mutex;
    vector<thread> task_list;
    queue<function<void()>> task_queue;
    condition_variable condition;    

    void scheduleTask(const string& process_id, function<void()> task) {
        lock_guard<std::mutex> lock(queue_mutex);
        task_queue.push(move(task));
        condition.notify_one();
    }

    string logCompletedProcess() {
        shared_ptr<Process> process = queue_mp.begin()->second;
        string log_statement = process->getProcessId() + " started at " + 
                                to_string(process->getStartTime()) + 
                                " and ended at " + to_string(process->getEndTime());  
        cout<<log_statement<<endl;
        processes.erase(process->getProcessId());
        queue_mp.erase(queue_mp.begin());
        return log_statement;
    }    

};

int main() {
    std::unique_ptr<LogClient> logger = std::make_unique<Logger>();
    
    logger->start("1", 1);
    logger->size();
    logger->poll();
    logger->size();
    logger->start("3", 2);
    logger->size();
    logger->poll();
    logger->size();
    logger->end("1");
    logger->size();
    logger->poll();
    logger->size();
    logger->start("2", 3);
    logger->size();
    logger->poll();
    logger->size();
    logger->end("2");
    logger->size();
    logger->poll();
    logger->size();
    logger->end("3");
    logger->size();
    logger->poll();
    logger->size();
    logger->poll();
    logger->size();
    logger->poll();
    logger->size();
    logger->poll();
    logger->size();
    logger->poll();
    logger->size();

    return 0;
}
