package com.ibm.transformer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

public class KafkaConnectUtil {
	
	private class HandleRebalance implements ConsumerRebalanceListener {
		public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		}

		public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
			consumer.commitSync(currentOffsets);
		}
	}

	KafkaConsumer<String, String> consumer;
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();

	public KafkaConnectUtil() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "rt-event-transformer");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer<String, String>(props);
	}


	public void initConsumer() {
		//shut down hook
		Runtime.getRuntime().addShutdownHook(new Thread() {
	        public void run() {
	            System.out.println("Starting exit...");
	            consumer.wakeup(); 
	            try {
	                this.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    });

		try {
			consumer.subscribe(Arrays.asList("raw.events"), new HandleRebalance());

			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println(
									"topic = "+record.topic()+", partition = "+record.partition()+", offset = "+record.offset()+", key = "+record.key()+", value = "+record.value()+"\n");
					currentOffsets.put(new TopicPartition(record.topic(),record.partition()), new OffsetAndMetadata(record.offset()));
				}
				consumer.commitAsync(currentOffsets, new OffsetCommitCallback() {
			        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
			            if (exception != null){
			            	System.out.println("Commit failed for offsets "+offsets);
			            }
			        }
			      });
			}
		} catch (WakeupException e1) {
			// ignore, we're closing
		} catch (Exception e) {
			// log.error("Unexpected error", e);
		} finally {
			try {
				consumer.commitSync(currentOffsets);
			} finally {
				consumer.close();
			}
		}
	}
	
	public static void main(String[] args){
		KafkaConnectUtil kafkaConnectUtil = new KafkaConnectUtil();
		kafkaConnectUtil.initConsumer();
		
		
	}
}
