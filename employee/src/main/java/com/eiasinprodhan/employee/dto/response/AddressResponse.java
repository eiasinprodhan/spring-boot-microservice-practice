package com.eiasinprodhan.employee.dto.response;

import com.eiasinprodhan.employee.entity.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponse {
    private Long id;
    private Long employeeId;
    private String street;
    private Long zipCode;
    private String city;
    private String country;
    private AddressType addressType;
}
