package com.restapi.mongodbcrud.crudModel;

import org.springframework.beans.factory.annotation.Value;

public class demo {

   private String customerId;

   private String customerName;

   private String customerAddress;

   private String customerCity;

   public String getCustomerId() {
      return customerId;
   }

   public void setCustomerId(String customerId) {
      this.customerId = customerId;
   }

   public String getCustomerName() {
      return customerName;
   }

   public void setCustomerName(String customerName) {
      this.customerName = customerName;
   }

   public String getCustomerAddress() {
      return customerAddress;
   }

   public void setCustomerAddress(String customerAddress) {
      this.customerAddress = customerAddress;
   }

   public String getCustomerCity() {
      return customerCity;
   }

   public void setCustomerCity(String customerCity) {
      this.customerCity = customerCity;
   }

   public demo(String customerId, String customerName, String customerAddress, String customerCity) {
      this.customerId = customerId;
      this.customerName = customerName;
      this.customerAddress = customerAddress;
      this.customerCity = customerCity;
   }

}
