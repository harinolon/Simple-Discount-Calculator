package com.restapi.mongodbcrud.crudModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class processBody {

   private Object ruleData;

   public Object getRuleData() {
      return ruleData;
   }

   public void setRuleData(Object ruleData) {
      this.ruleData = ruleData;
   }

   public String toString() {
      return "processBody ruleData=" + ruleData;
   }
}
