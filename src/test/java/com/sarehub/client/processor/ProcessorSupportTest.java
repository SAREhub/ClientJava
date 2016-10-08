package com.sarehub.client.processor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sarehub.client.message.Exchange;

public class ProcessorSupportTest {

	private Processor processor;

	@Before
	public void setUp() throws Exception {
		processor = new ProcessorSupport() {
			@Override
			public void process(Exchange exchange) {
			}
		};
	}

	@Test
	public void testSetLabel() throws Exception {
		processor.setLabel("label");
		assertThat(processor.getLabel(), is("label"));
	}

	@Test
	public void testSetLabelReturnThis() throws Exception {
		assertThat(processor.setLabel("label"), sameInstance(processor));
	}

}
