package com.sarehub.client.support;

public interface Service {

	public void start() throws Exception;

	public void stop() throws Exception;

	public boolean isInited();

	public boolean isStarting();

	public boolean isStarted();

	public boolean isStopped();

	public State getServiceState();

	public enum State {
		INITED, STARTING, STARTED, STOPPED
	}
}
