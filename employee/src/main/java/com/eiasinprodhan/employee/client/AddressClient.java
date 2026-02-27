package com.eiasinprodhan.employee.client;

import com.eiasinprodhan.employee.dto.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ADDRESS")
public interface AddressClient {

    @GetMapping("/api/address/employee/{id}")
    List<AddressResponse> getAddressByEmployeeId(@PathVariable Long id);
}
