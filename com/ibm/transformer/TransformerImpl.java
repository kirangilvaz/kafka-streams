package com.ibm.transformer;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;

public class TransformerImpl implements Transformer {

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(ProcessorContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object punctuate(long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object transform(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		String temp = (String)arg1;
		temp = temp.replace(",", "$");
		return new KeyValue(arg0, temp);
	}

}
