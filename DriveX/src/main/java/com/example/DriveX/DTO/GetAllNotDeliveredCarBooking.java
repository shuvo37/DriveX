package com.example.DriveX.DTO;

import jakarta.validation.constraints.NotBlank;

public class GetAllNotDeliveredCarBooking {


       @NotBlank(message = "model name cannot be blank")
       private String modelName;

       public GetAllNotDeliveredCarBooking(String modelName)
       {
            this.modelName = modelName;

       }

       public GetAllNotDeliveredCarBooking(){}

       public String getModelName() {
           return modelName;
       }

       public void setModelName(String modelName) {
           this.modelName=modelName;
       }

}
