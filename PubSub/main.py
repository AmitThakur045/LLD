from singleton_class import SingletonMeta

class Topic:
    def __init__(self, id, name):
        self._id = id
        self._name = name
        self._message_list = []
        self._size = 0
        
    def insert_message(self, message: dict):
        try:
            self._message_list.append(message)
            self._size += 1
            return True
        except Exception as e:
            print("Error while inserting to topic", e)
            return False
            
    def get_message(self, index: int) -> dict:
        try:
            assert index < self._size, "Wrong index found"
            return self._message_list[index]
        except Exception as e:
            return {}
        
class TopicManager(metaclass=SingletonMeta):
    def __init__(self):
        self._topic_mapping = {}
        self._counter = 0
        self._consumer_manager = ConsumerManager()
    
    def create_topic(self, topic_name: str):
        self._counter += 1
        topic = Topic(self._counter, topic_name)
        self._topic_mapping[topic._name] = topic
    
    def insert_message(self, topic_name: str, message: dict):
        topic_obj = self.get_topic(topic_name)
        topic_obj.insert_message(message)
        self._consumer_manager.trigger_consumer(topic_name)
        
    def get_message(self, topic_name, idx):
        topic_obj = self.get_topic(topic_name)
        return topic_obj.get_message(idx)
        
    def get_topic(self, topic_name):
        return self._topic_mapping.get(topic_name, None)


class Publisher:
    def __init__(self, name):
        self._name = name
        self._topic_manager = TopicManager()
        
    def publish_message(self, message: dict, topic_name: str):
        return self._topic_manager.insert_message(topic_name, message)
        
class Consumer:
    def __init__(self, name, topic_name):
        self._name = name
        self._topic_name = topic_name
        self._counter = 0
        self._topic_manager = TopicManager()
        self.consume()
        
    def consume(self):
        while(True):
            message = self._topic_manager.get_message(self._topic_name, self._counter)
            if (not message):
                break
            self._counter += 1
            print("consumer ", self._name, "consuming ", message)
        
class ConsumerManager(metaclass=SingletonMeta):
    def __init__(self):
        self._consumer_topic_mapping = {}
        
    def create_consumer(self, name: str, topic_name: str):
        consumer = Consumer(name, topic_name)
        if topic_name not in self._consumer_topic_mapping:
            self._consumer_topic_mapping[topic_name] = [consumer]
        else:
            self._consumer_topic_mapping[topic_name].append(consumer)
    
    def trigger_consumer(self, topic_name):
        try:
            assert topic_name in self._consumer_topic_mapping.keys(), "topic does not exist"
            for consumer in self._consumer_topic_mapping[topic_name]:
                consumer.consume()
        except AssertionError:
            print("topic does not exist")
            return
        except Exception as e:
            print("Error while triggering consumer", e)


def main():
    topic_manager = TopicManager()
    consumer_manager = ConsumerManager()
 
    topic_manager.create_topic("test")
    consumer_manager.create_consumer("consumer", "test")
    publisher = Publisher("publisher")
    publisher.publish_message({"hello": "world"}, "test")


    topic_manager.create_topic("test2")
    consumer_manager.create_consumer("consumer2", "test2")
    consumer_manager.create_consumer("consumer3", "test2")
    consumer_manager.create_consumer("consumer4", "test")

    publisher.publish_message({"hello": "world 2"}, "test2")
    publisher.publish_message({"hello": "world 3"}, "test2")
    publisher.publish_message({"hello": "world 4"}, "test2")


if __name__ == "__main__":
    main()