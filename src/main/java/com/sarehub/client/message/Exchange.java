/*******************************************************************************
 * Copyright 2016 SARE SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.sarehub.client.message;

/**
 * Represents messages exchange between processors. Input message will be message for process by
 * processor. Output message can be sets in processor as reply for input message.
 */
public interface Exchange {

	public Exchange copy();

	/**
	 * Returns input message.
	 */
	public Message getIn();

	public boolean hasIn();

	/**
	 * Sets input message.
	 * 
	 * @return this
	 */
	public Exchange setIn(Message in);

	/**
	 * Returns output message.
	 */
	public Message getOut();

	public boolean hasOut();

	/**
	 * Sets output message.
	 * 
	 * @return this
	 */
	public Exchange setOut(Message out);

	public Exception getException();

	public Exchange setException(Exception exception);

	public boolean isFailed();

}
