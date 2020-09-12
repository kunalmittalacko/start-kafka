Download Link
https://www.apache.org/dyn/closer.cgi?path=/kafka/2.6.0/kafka_2.13-2.6.0.tgz

Extract Kafka
tar -xvf kafka_2.12-2.0.0.tgz

cd kafka_2.12-2.0.0

Install java 


Start Zookeeper
-> zookeeper-server-start config/zookeeper.properties 

Kafka Server Start
-> kafka-server-start config/server.properties

Create Topic 
-> kafka-topics --zookeeper 127.0.0.1:2181 --topic {topic-name} --create --partitions {number of partition} --replication-factor {no of replication}

List Topic
-> kafka-topics --zookeeper 127.0.0.1:2181 --list

Describe Topic 
    All Topic Description
    -> kafka-topics --zookeeper 127.0.0.1:2181 --describe
    Specific Topic Description  
    -> kafka-topics --zookeeper 127.0.0.1:2181 --topic {topic-name} --describe

Delete a Topic 
-> kafka-topics --zookeeper 127.0.0.1:2181 --topic {topic-name}--delete

Producer 
-> kafka-console-producer --broker-list 127.0.0.1:9092 --topic {topic-name}

Consumer
-> kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic second-topic
-> kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic second-topic





Consumer Group

If we have multiple consumers listening to the same partition then the message will be received by only of the consumer group member.
-> kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic second-topic --group my-first-application --from-beginning

List Consumer Groups 
-> kafka-consumer-groups --bootstrap-server localhost:9092 --list
Describe a Consumer Group 
-> kafka-consumer-groups --bootstrap-server localhost:9092 --group {group-name} --describe














