package kafka;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProducerWithCallback {
    public static void main(String[] args) {

        System.out.println("Hello World");
        final Logger logger = LoggerFactory.getLogger(ProducerWithCallback.class);
        final String bootstrapServer = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,"1");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 100; i++) {
            final ProducerRecord<String, String> record = new ProducerRecord<String, String>("fifth-topic", "new_message" + i);

            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        logger.info("Recieved new Metadata " + "\n" +
                            "Topic " + recordMetadata.topic() + "\n" +
                            "Partition " + recordMetadata.partition() + "\n" +
                            "Offset " + recordMetadata.offset() + "\n" +
                            "Time Stamp " + recordMetadata.timestamp() + "\n"
                        );
                    } else {
                        logger.error("Error in producing message " + Arrays.toString(e.getStackTrace()));
                    }
                }
            });
        }


        producer.close();

    }
}

