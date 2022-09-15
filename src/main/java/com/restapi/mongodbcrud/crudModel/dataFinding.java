package com.restapi.mongodbcrud.crudModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "testing")
public class dataFinding {

   @Id
   private String id;

   private String username;

   private String password;

   private Long phoneNumber;

   private Integer orderCount;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Long getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(Long phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public Integer getOrderCount() {
      return orderCount;
   }

   public void setOrderCount(Integer orderCount) {
      this.orderCount = orderCount;
   }

}
