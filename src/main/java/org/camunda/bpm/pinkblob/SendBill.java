package org.camunda.bpm.pinkblob;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendBill implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		
		HttpClient client = HttpClients.createDefault();
		RequestBuilder requestBuilder = RequestBuilder.get().setUri("http://localhost:8080/iss-process/receiveAgencyBill")
				.addParameter("corrID", String.valueOf(execution.getVariable("corrID")))
				.addParameter("amount", String.valueOf(execution.getVariable("amount")))
				.addParameter("iban", String.valueOf(execution.getVariable("iban")));

						
		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request); // log debug
															// information
			System.out.println(request.getURI());
			System.out.println(response.getStatusLine());
	}
}