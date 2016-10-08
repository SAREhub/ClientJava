package com.sarehub.client.message;

public class BasicExchange implements Exchange {

	private Message in;
	private Message out;

	private Exception exception;

	@Override
	public Exchange copy() {
		Exchange copy = newInstance();

		if (hasIn()) {
			copy.setIn(getIn().copy());
		}

		if (hasOut()) {
			copy.setOut(getOut().copy());
		}

		if (isFailed()) {
			copy.setException(getException());
		}

		return copy;
	}

	/**
	 * Creates new instance
	 * 
	 * @param in
	 * @return new exchange instance
	 */
	public static Exchange newInstance() {
		return new BasicExchange();
	}

	/**
	 * Creates new instance with sets input message.
	 * 
	 * @param in
	 * @return new instance with sets input message
	 */
	public static Exchange newWithIn(Message in) {
		return (new BasicExchange()).setIn(in);
	}

	@Override
	public Message getIn() {
		return in;
	}

	@Override
	public boolean hasIn() {
		return in != null;
	}

	@Override
	public Exchange setIn(Message in) {
		this.in = in;
		return this;
	}

	@Override
	public Message getOut() {
		if (!hasOut()) {
			out = new BasicMessage();
		}

		return out;
	}

	@Override
	public boolean hasOut() {
		return out != null;
	}

	@Override
	public Exchange setOut(Message out) {
		this.out = out;
		return this;
	}

	@Override
	public Exception getException() {
		return exception;
	}

	@Override
	public Exchange setException(Exception exception) {
		this.exception = exception;
		return this;
	}

	@Override
	public boolean isFailed() {
		return exception != null;
	}

}
