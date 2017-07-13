package org.camunda.bpm.pinkblob.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;

public class ContinueProcessServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		String corrID = request.getParameter("corrID");
		String additionalInformationForAgency = request.getParameter("additionalInformationForAgency");
		if (null == corrID) {
			out.println("<h2>Error</h2><p>Parameter corrID missing!</p>");
		} else {
			try {
				runtimeService.createMessageCorrelation("continueMessage").processInstanceVariableEquals("corrID", corrID)
				.setVariable("additionalInformationForAgency",additionalInformationForAgency).correlate();
				out.println("<h1>Message delivered to process</h1><p>ID: " + corrID + "</p>");
			} catch (MismatchingMessageCorrelationException e) {
				out.println("<h2>Error</h2><p>No correlating process instance.</p><p>" + corrID + "</p>");
			}
		}
		out.println("</body></html>");
	}
}
