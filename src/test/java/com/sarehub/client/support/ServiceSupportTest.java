package com.sarehub.client.support;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ServiceSupportTest {

	private TestService service;

	private static class TestService extends ServiceSupport {

		public int doStartInvocations;
		public int doStopInvocations;

		@Override
		protected void doStart() throws Exception {
			doStartInvocations++;
		}

		@Override
		protected void doStop() throws Exception {
			doStopInvocations++;
		}
	}

	@Before
	public void setUp() throws Exception {
		service = new TestService();
	}

	@Test
	public void testIsStartedWhenCreatedThenReturnFalse() {
		assertThat(service.isStarted(), is(false));
	}

	@Test
	public void testIsStartedWhenStartedThenReturnTrue() throws Exception {
		service.start();
		assertThat(service.isStarted(), is(true));
	}

	@Test
	public void testIsStartedWhenStartedAndCallStopThenReturnFalse() throws Exception {
		service.start();
		service.stop();
		assertThat(service.isStarted(), is(false));
	}

	@Test
	public void testIsInitedWhenCreatedThenReturnTrue() {
		assertThat(service.isInited(), is(true));
	}

	@Test
	public void testIsStoppedWhenStartedThenReturnFalse() throws Exception {
		service.start();
		assertThat(service.isStopped(), is(false));
	}

	@Test
	public void testIsStoppedWhenStartedAndCallStopThenReturnTrue() throws Exception {
		service.start();
		service.stop();
		assertThat(service.isStopped(), is(true));
	}

	@Test
	public void testStartWhenInitedThenDoStart() throws Exception {
		service.start();
		assertThat(service.doStartInvocations, is(1));
	}

	@Test
	public void testStartWhenStoppedThenDoStart() throws Exception {
		service.start();
		service.stop();
		service.start();
		assertThat(service.doStartInvocations, is(2));
	}

	@Test
	public void testStartWhenStartedThenNotDoStart() throws Exception {
		service.start();
		service.start();
		assertThat(service.doStartInvocations, is(1));
	}

	@Test
	public void testStopWhenStartedThenDoStop() throws Exception {
		service.start();
		service.stop();
		assertThat(service.doStopInvocations, is(1));
	}

	@Test
	public void testStopWhenInitedThenNotDoStop() throws Exception {
		service.stop();
		assertThat(service.doStopInvocations, is(0));
	}

	@Test
	public void testStopWhenStoppedThenNotDoStop() throws Exception {
		service.start();
		service.stop();
		service.stop();
		assertThat(service.doStopInvocations, is(1));
	}

	private static class TestExceptionDoStartService extends ServiceSupport {

		public int doStopInvocations;

		@Override
		protected void doStart() throws Exception {
			throw new Exception();
		}

		@Override
		protected void doStop() throws Exception {
			doStopInvocations++;
		}
	}

	@Test
	public void testStartWhenExceptionThenIsStopped() throws Exception {
		Service service = new TestExceptionDoStartService();
		try {
			service.start();
			fail("Exception expected");
		} catch (Exception e) {
			assertThat(service.isStopped(), is(true));
		}
	}

	@Test
	public void testStartWhenExceptionThenCallDoStop() throws Exception {
		TestExceptionDoStartService service = new TestExceptionDoStartService();
		try {
			service.start();
			fail("Exception expected");
		} catch (Exception e) {
			assertThat(service.doStopInvocations, is(1));
		}
	}

	private static class TestExceptionDoStartAndDoStopService extends ServiceSupport {

		@Override
		protected void doStart() throws Exception {
			throw new Exception("start");
		}

		@Override
		protected void doStop() throws Exception {
			throw new Exception("stop");
		}
	}

	@Test
	public void testStartWhenExceptionAndStopExceptionThenThrowIsStopped() throws Exception {
		TestExceptionDoStartAndDoStopService service = new TestExceptionDoStartAndDoStopService();
		try {
			service.start();
			fail("Exception expected");
		} catch (Exception e) {
			assertThat(service.isStopped(), is(true));
		}
	}

}
