package org.camunda.bpm.pinkblob.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class CreateProcessServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();
		String corrID = request.getParameter("corrID");
		String company = request.getParameter("company");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String classifiedRequirements = request.getParameter("classifiedRequirements");
		String hardcopyRequired = request.getParameter("hardcopyRequired");

		
		ProcessInstance processInstance;
		if (null == corrID) {
			processInstance = runtimeService.startProcessInstanceByMessage("InstantiationMessage2");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("corrID", corrID);
			map.put("company", company);
			map.put("address", address);
			map.put("phone", phone);
			map.put("classifiedRequirements", classifiedRequirements);
			map.put("hardcopyRequired", hardcopyRequired);
			processInstance = runtimeService.startProcessInstanceByMessage("InstantiationMessage2", map);
		}
		out.println("<html><body>");
		out.println("<h1>Process Instance Started</h1>");
		out.println("<p>ID: " + processInstance.getId() + "</p>");
		out.println("<p>corrID: " + corrID + "</p>");
		out.println("</body></html>");
	}
}