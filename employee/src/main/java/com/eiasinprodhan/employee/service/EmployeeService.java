package com.eiasinprodhan.employee.service;

import com.eiasinprodhan.employee.dto.request.EmployeeRequest;
import com.eiasinprodhan.employee.dto.response.AddressResponse;
import com.eiasinprodhan.employee.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

    public List<EmployeeResponse> getAllEmployee();

    public EmployeeResponse getEmployee(Long employeeId);

    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest);

    public EmployeeResponse deleteEmployee(Long employeeId);
}
