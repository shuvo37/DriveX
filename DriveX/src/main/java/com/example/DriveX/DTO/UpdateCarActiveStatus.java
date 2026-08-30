package com.example.DriveX.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCarActiveStatus {

    @NotBlank(message = "modelName is requre")
    private String modelName;

    private boolean isActive;

    public UpdateCarActiveStatus(String modelName  ,boolean isActive) {
        this.modelName = modelName;
        this.isActive = isActive;
    }
    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }



}
