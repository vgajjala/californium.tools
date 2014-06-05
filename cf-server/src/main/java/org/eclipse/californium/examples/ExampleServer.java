/*******************************************************************************
 * Copyright (c) 2014 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
package org.eclipse.californium.examples;

import java.util.concurrent.Executors;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.server.Server;

import org.eclipse.californium.examples.resources.FibonacciResource;
import org.eclipse.californium.examples.resources.HelloWorldResource;
import org.eclipse.californium.examples.resources.ImageResource;
import org.eclipse.californium.examples.resources.LargeResource;
import org.eclipse.californium.examples.resources.MirrorResource;
import org.eclipse.californium.examples.resources.StorageResource;

/**
 * This is an example server that contains a few resources for demonstration.
 */
public class ExampleServer {
	
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.setExecutor(Executors.newScheduledThreadPool(4));
		
		server.add(new HelloWorldResource("hello"));
		server.add(new FibonacciResource("fibonacci"));
		server.add(new StorageResource("storage"));
		server.add(new ImageResource("image"));
		server.add(new MirrorResource("mirror"));
		server.add(new LargeResource("large"));
		
		server.start();
	}
	
	/*
	 *  Sends a GET request to itself
	 */
	public static void selfTest() {
		try {
			Request request = Request.newGet();
			request.setURI("localhost:5683/hello");
			request.send();
			Response response = request.waitForResponse(1000);
			System.out.println("received "+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}