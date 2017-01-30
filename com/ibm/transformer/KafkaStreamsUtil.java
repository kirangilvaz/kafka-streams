package com.ibm.transformer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;

public class KafkaStreamsUtil {
	
	public void init(){
		Map<String, Object> props = new HashMap<>();
	    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
	    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, StringSerde.class);
	    props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, StringSerde.class);
	    StreamsConfig config = new StreamsConfig(props);

	    KStreamBuilder builder = new KStreamBuilder();
	    builder.stream("raw.events").transform(new TransformerSupplierImpl()).to("processed.events");

	    KafkaStreams streams = new KafkaStreams(builder, config);
	    streams.start();
	}
	
	public static void main(String[] args){
		KafkaStreamsUtil k = new KafkaStreamsUtil();
		k.init();
	}
}
