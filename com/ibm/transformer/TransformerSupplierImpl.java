package com.ibm.transformer;

import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.TransformerSupplier;

public class TransformerSupplierImpl implements TransformerSupplier {

	TransformerImpl t = new TransformerImpl();
	@Override
	public Transformer get() {
		// TODO Auto-generated method stub
		return t;
	}

}
