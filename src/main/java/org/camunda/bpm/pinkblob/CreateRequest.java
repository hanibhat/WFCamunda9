package org.camunda.bpm.pinkblob;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hbhat on 13-Jul-17.
 */

@ManagedBean(name = "newReq")
@RequestMapping("/submit")
public class CreateRequest implements Serializable {
    static class RequestData {
        public String name;
        public String company;
        public String emailAdd;
        public String classifiedRequirements;
        public String address;
        public int phone;
        public boolean hardcopyRequired;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postForm(@RequestBody RequestData RequestData) {
//        List<String> messages = new ArrayList<String>();
        System.out.println("name: " + RequestData.name);
        System.out.println("company: " + RequestData.company);
        System.out.println("emailAdd: " + RequestData.emailAdd);
        System.out.println("classifiedRequirements: " + RequestData.classifiedRequirements);
        System.out.println("address: " + RequestData.address);
        System.out.println("phone: " + RequestData.phone);
        System.out.println("hardcopy: " + RequestData.hardcopyRequired);

        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }


}
