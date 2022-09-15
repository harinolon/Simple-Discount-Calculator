package com.restapi.mongodbcrud.crudModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class kafkaBody {

   private Object message;

   public Object getMessage() {
      return message;
   }

   public void setMessage(Object message) {
      this.message = message;
   }

   public String toString() {
      return "kafkaMessage: " + message;
   }
}
