package com.sarehub.client.processor;

public abstract class ProcessorSupport implements Processor {

	private String label;

	@Override
	public Processor setLabel(String label) {
		this.label = label;
		return this;
	}

	@Override
	public String getLabel() {
		return label != null ? label : getClass().getSimpleName();
	}

}
