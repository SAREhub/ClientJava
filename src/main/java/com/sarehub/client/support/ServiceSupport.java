package com.sarehub.client.support;

public abstract class ServiceSupport implements Service {

	private State state = State.INITED;

	@Override
	public void start() throws Exception {
		if (isInited() || isStopped()) {
			state = State.STARTING;
			try {
				doStart();
				state = State.STARTED;
			} catch (Exception e) {
				try {
					stop();
				} catch (Exception e2) {
					// ignore, we want throw original exception
				} finally {
					state = State.STOPPED;
				}

				throw e;
			}
		}

	}

	protected abstract void doStart() throws Exception;

	@Override
	public void stop() throws Exception {
		if (!isInited() && !isStopped()) {
			doStop();
			state = State.STOPPED;
		}

	}

	protected abstract void doStop() throws Exception;

	@Override
	public boolean isInited() {
		return state == State.INITED;
	}

	@Override
	public boolean isStarting() {
		return state == State.STARTING;
	}

	@Override
	public boolean isStarted() {
		return state == State.STARTED;
	}

	@Override
	public boolean isStopped() {
		return state == State.STOPPED;
	}

	@Override
	public State getServiceState() {
		return state;
	}

}
