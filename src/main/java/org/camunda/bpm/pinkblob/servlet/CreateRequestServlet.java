package org.camunda.bpm.pinkblob.servlet;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createRequest")
public class CreateRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // read form fields
        String corrID = request.getParameter("corrID");
        String name = request.getParameter("name");
        String company = request.getParameter("company");
        String address = request.getParameter("address");
        String emailAdd = request.getParameter("emailAdd");
        String phone = request.getParameter("phone");
        String classifiedRequirements = request.getParameter("classifiedRequirements");
        String hardcopyRequired = request.getParameter("hardcopyRequired");
        if (null == hardcopyRequired) {
            hardcopyRequired = "false";
        }

        /*
        System.out.println("name: " + name);
        System.out.println("company: " + company);
        System.out.println("address: " + address);
        System.out.println("email: " + emailAdd);
        System.out.println("phone number: " + phone);
        System.out.println("Classified Requirements: " + classifiedRequirements);
        System.out.println("Hardcopy: " + hardcopyRequired);
        */

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        PrintWriter out = response.getWriter();
        ProcessInstance processInstance;
        if (null == corrID) {
            processInstance = runtimeService.startProcessInstanceByMessage("InstantiationMessage2");
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("corrID", corrID);
            map.put("name", name);
            map.put("company", company);
            map.put("address", address);
            map.put("email", emailAdd);
            map.put("phone", phone);
            map.put("classifiedRequirements", classifiedRequirements);
            map.put("hardcopyRequired", hardcopyRequired);
            processInstance = runtimeService.startProcessInstanceByMessage("InstantiationMessage2", map);
        }

//        redirect to thank you page
        RequestDispatcher view = request.getRequestDispatcher("/request-submitted.html");
        view.forward(request, response);

        /*
        out.println("<html><body>");
        out.println("<h1>Process Instance Started</h1>");
        out.println("<p>ID: " + processInstance.getId() + "</p>");
        out.println("<p>corrID: " + corrID + "</p>");
        out.println("</body></html>");
        */
    }


}
