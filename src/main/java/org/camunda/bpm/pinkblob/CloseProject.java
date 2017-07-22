package org.camunda.bpm.pinkblob;

import java.util.logging.Logger;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CloseProject implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("DESIGN-REQUESTS");

    public void execute(DelegateExecution execution) throws Exception {
        HttpClient client = HttpClients.createDefault();
        RequestBuilder requestBuilder = RequestBuilder.get()
                .addParameter("corrID", String.valueOf(execution.getVariable("corrID")));
        LOGGER.info("Closing project with corrID: '" + execution.getVariable("corrID"));
    }
}
