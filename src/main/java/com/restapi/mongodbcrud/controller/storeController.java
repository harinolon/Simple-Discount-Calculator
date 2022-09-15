package com.restapi.mongodbcrud.controller;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restapi.mongodbcrud.crudModel.dataFinding;
import com.restapi.mongodbcrud.crudModel.demo;
import com.restapi.mongodbcrud.crudModel.kafkaBody;
import com.restapi.mongodbcrud.crudModel.processBody;
import com.restapi.mongodbcrud.repository.loginRepo;

@RestController
public class storeController {

   @Autowired
   private loginRepo loginRepo;

   @GetMapping("/loginDataAll")
   public ResponseEntity<?> getUserData() {
      List<dataFinding> data = loginRepo.findAll();
      if (data.size() > 0) {
         return new ResponseEntity<List<dataFinding>>(data, HttpStatus.OK);
      } else {
         return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
      }
   }

   @GetMapping("/login")
   public ResponseEntity<?> getUserDataByUsername(@RequestParam String username, String password) {
      Optional<dataFinding> data = loginRepo.findByUsername(username);
      if (data.isPresent()) {
         if (data.get().getPassword().equals(password)) {
            return new ResponseEntity<dataFinding>(data.get(), HttpStatus.OK);
         } else {
            return new ResponseEntity<>("incorrect", HttpStatus.OK);
         }
      } else {
         return new ResponseEntity<>("noData", HttpStatus.OK);
      }
   }

   @PostMapping("/register")
   public ResponseEntity<?> addUserData(@RequestBody dataFinding data) {
      try {
         Optional<dataFinding> userDataAlready = loginRepo.findByUsername(data.getUsername());
         if (userDataAlready.isPresent()) {
            return new ResponseEntity<>("exists", HttpStatus.OK);
         } else {
            loginRepo.save(data);
            return new ResponseEntity<>("success", HttpStatus.OK);
         }
      } catch (Exception e) {
         return new ResponseEntity<>("noData", HttpStatus.OK);
      }
   }

   @PostMapping("/startDiscountProcess")
   public String startProcessInstance(@RequestBody processBody process) {
      String uri1 = "http://localhost:8080/kie-server/services/rest/server/containers/OnlineStore_2.0.2/processes/OnlineStore.discountProcess/instances";
      try {

         // create auth credentials
         String authStr = "rhpamAdmin:hariduke001*";
         String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

         // create headers
         RestTemplate restTemplate = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
         headers.add("Authorization", "Basic " + base64Creds);
         // JSONObject processData = new JSONObject();
         // processData.put("customer", "John");
         // processData.put("amount", "100");
         // String body =
         // "{\"ruleData\":{\"businessRuleData\":{\"orderCount\":30,\"price\":500,}}}";

         // MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new
         // MappingJackson2HttpMessageConverter();
         // jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
         // false);
         // restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

         // JSONObject processData = new JSONObject(body);
         // processData.put("ruleData", "businessRuleData");
         // processData.put("orderCount", 30);
         // processData.put("price", 500);

         // Map<String, Object> processData = new HashMap<String, Object>();
         // processData.put("ruleData", "businessRuleData");
         // processData.put("ruleData",
         // "{\"businessRuleData\":{\"orderCount\":30,\"price\":500,}}");
         // processData.put("orderCount", 30);
         // processData.put("price", 500);

         HttpEntity<Object> request = new HttpEntity<>(process,
               headers);

         // Response Handler
         ResponseEntity processStarted = restTemplate.postForEntity(uri1, request, String.class);
         System.out.println(processStarted.getBody());
         Integer processId = Integer.parseInt(processStarted.getBody().toString());
         // return processStarted;

         // Get Response of data
         if (processStarted.getStatusCodeValue() == 201) {
            String uri2 = "http://localhost:8080/kie-server/services/rest/server/containers/OnlineStore_2.0.2/processes/instances/"
                  + processId + "/variables";

            HttpEntity<String> request2 = new HttpEntity<>(headers);

            ResponseEntity processVariables = restTemplate.exchange(uri2, HttpMethod.GET, request2, String.class);
            System.out.println(processVariables.getBody());

            // Signal Stop
            if (processVariables.getStatusCodeValue() == 200) {
               String uri3 = "http://localhost:8080/kie-server/services/rest/server/containers/OnlineStore_2.0.2/processes/instances/"
                     + processId + "/signal/discountSignal";

               Map<String, Object> signalData = new HashMap<String, Object>();
               signalData.put("", "");

               HttpEntity<Object> request3 = new HttpEntity<>(signalData, headers);

               ResponseEntity processSignal = restTemplate.exchange(uri3, HttpMethod.POST, request3, String.class);
               // System.out.println(processSignal.getBody());
               if (processSignal.getStatusCodeValue() == 200) {
                  return processVariables.getBody().toString();
               } else {
                  return "Process Failed";
               }
            } else {
               return "error";
            }

         } else {
            return "fail";
         }

      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "fail";
   }

   @PostMapping("/startKafkaProcess")
   public String startKafkaProcess(@RequestBody kafkaBody kafka) {
      String kafkaUri1 = "http://localhost:8080/kie-server/services/rest/server/containers/OnlineStore_2.0.2/processes/OnlineStore.kafkaProcess/instances";
      try {
         // kafka.setKafkaMessage("Hello Kafka");
         // create auth credentials
         String authStr = "rhpamAdmin:hariduke001*";
         String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

         // create headers
         RestTemplate restTemplate = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
         headers.add("Authorization", "Basic " + base64Creds);

         HttpEntity<Object> request = new HttpEntity<>(kafka,
               headers);

         // Response Handler
         ResponseEntity processStarted = restTemplate.postForEntity(kafkaUri1, request, String.class);
         System.out.println(processStarted.getBody());
         return processStarted.getBody().toString();
         // Integer kafkaProcessId =
         // Integer.parseInt(processStarted.getBody().toString());

      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "fail";
   }

   // @GetMapping("/twilioProcess")
   // public String gettwilioData(@RequestParam("phoneNumber") String phoneNumber,
   // @RequestParam("message") String message) {
   // System.out.println(phoneNumber);
   // System.out.println(message);
   // twilioSMS twilio = new twilioSMS();
   // twilio.sendSMS(phoneNumber, message);
   // return "success";
   // }

   @GetMapping("/test")
   public Object test(@RequestParam String customerId, String customerName, String customerAddress,
         String customerCity) {
      demo d = new demo(customerId, customerName, customerAddress, customerCity);
      System.out.println(d);
      // d.setCustomerId(customerId);
      System.out.println(d.getCustomerName());
      return d;
   }
}
