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

import java.util.Map;

/**
 * Represents wrapper for data for processing by processors. Massage has optional defined headers
 * and body where data can be sets.
 */
public interface Message {

	/**
	 * Creates copy of that message.
	 * 
	 * @return the copy of original message
	 */
	public Message copy();

	/**
	 * Returns value of selected header or null when header isn't exists.
	 * 
	 * @param name
	 *            the name of header
	 * @return value of header
	 */
	public Object getHeader(String name);

	/**
	 * Returns value of selected header or default value.
	 * 
	 * @param name
	 *            the name of header
	 * @param defaultValue
	 *            the value when header doesn't exists
	 * @return value of header or default value
	 */
	public Object getHeader(String name, Object defaultValue);

	/**
	 * Returns casted to concrete type value of selected header.
	 * 
	 * @param name
	 *            the name of header
	 * @param type
	 *            the type for casting header value
	 * @return value of header
	 */
	public <T> T getHeader(String name, Class<T> type);

	/**
	 * Returns all defined headers
	 * 
	 * @return headers of message
	 */
	public Map<String, Object> getHeaders();

	/**
	 * Sets value of header, when header exists will replace value of that header.
	 * 
	 * @param name
	 *            the name of header
	 * @param value
	 *            the header value
	 * @return this
	 */
	public Message setHeader(String name, Object value);

	/**
	 * Removes selected header from this message.
	 * 
	 * @param name
	 * @return this
	 */
	public Message removeHeader(String name);

	/**
	 * Returns body of this message.
	 * 
	 * @return body
	 */
	public Object getBody();

	/**
	 * Returns body of this message casted to selected type.
	 * 
	 * @param type
	 *            the type for casting body
	 * @return casted body
	 */
	public <T> T getBody(Class<T> type);

	public boolean hasBody();

	/**
	 * Sets body of this message.
	 * 
	 * @param body
	 *            the new body
	 * @return this
	 */
	public Message setBody(Object body);

}
