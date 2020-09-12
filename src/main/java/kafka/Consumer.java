package kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(Consumer.class);
        final String bootstrapServers = "127.0.0.1:9092";
        final String groupId = "my-new-application";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Collections.singletonList("fourth-topic"));

        while (true) {
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String ,String> record : records){
                logger.info("Key "+ record.key() + " Value " + record.value());
                logger.info("Partition " + record.partition() + " Offset "  + record.offset());
            }
        }


    }
}
