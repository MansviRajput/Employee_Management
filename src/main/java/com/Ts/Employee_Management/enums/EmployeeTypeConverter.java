package com.Ts.Employee_Management.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
 
@Converter(autoApply = true)
public class EmployeeTypeConverter implements AttributeConverter<EmployeeType, String> {
 
    @Override
    public String convertToDatabaseColumn(EmployeeType attribute) {
        return attribute == null ? null : attribute.getEmployeeType();
    }
 
    @Override
    public EmployeeType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : EmployeeType.fromValue(dbData);
    }
}