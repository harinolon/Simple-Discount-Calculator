package com.restapi.mongodbcrud.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class twilioSMS {
   public ResponseEntity<String> sendSMS(String phoneNumber, String message) {

      String accountSID = "None";
      String authToken = "none";
      String twilioNumber = "+18638672212";
      String phoneNo = "+91" + phoneNumber;

      // Twilio.init(username, password, accountSid);
      Twilio.init(accountSID, authToken);
      Message.creator(new PhoneNumber(phoneNo), new PhoneNumber(twilioNumber), message).create();

      return new ResponseEntity<>("success", HttpStatus.OK);
   }
}
