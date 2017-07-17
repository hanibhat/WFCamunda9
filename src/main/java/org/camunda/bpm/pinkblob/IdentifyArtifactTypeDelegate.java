package org.camunda.bpm.pinkblob;

import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class IdentifyArtifactTypeDelegate implements JavaDelegate {
	
	
	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");
	
	public void execute(DelegateExecution execution) throws Exception { // build
		// HTTP
		// request
		// with
		// all
		// variables
		// as
		// parameters
		HttpClient client = HttpClients.createDefault();
		RequestBuilder requestBuilder = RequestBuilder.get()
				.addParameter("corrID", String.valueOf(execution.getVariable("corrID")))
				.addParameter("hardcopyRequired", String.valueOf(execution.getVariable("hardcopyRequired")));
				
		
		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request); // log debug
		// information
	System.out.println(request.getURI());
	System.out.println(response.getStatusLine()); 
		
	LOGGER.info("Processing request by '" + execution.getVariable("hardcopyRequired") + "'...");
	}
}