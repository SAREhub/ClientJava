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
package com.sarehub.client.processor;

import com.sarehub.client.message.Exchange;

/**
 * Processor can process message exchange.
 */
public interface Processor {

	public void process(Exchange exchange);

	/**
	 * Sets label for processor
	 * 
	 * @param label
	 * @return this
	 */
	public Processor setLabel(String label);

	/**
	 * Gets label of that processor
	 * 
	 * @return the label of processor
	 */
	public String getLabel();
}
