package com.serviceRegistry.department_registry.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String resourceField;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName, String resourceField, Long fieldValue) {
        super(String.format("%s not found %s: %s",resourceName,resourceField,fieldValue));
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.fieldValue = fieldValue;
    }
}
