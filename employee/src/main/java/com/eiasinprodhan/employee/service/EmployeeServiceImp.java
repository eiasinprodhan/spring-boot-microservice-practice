package com.eiasinprodhan.employee.service;

import com.eiasinprodhan.employee.client.AddressClient;
import com.eiasinprodhan.employee.dto.request.EmployeeRequest;
import com.eiasinprodhan.employee.dto.response.AddressResponse;
import com.eiasinprodhan.employee.dto.response.EmployeeResponse;
import com.eiasinprodhan.employee.entity.Employee;
import com.eiasinprodhan.employee.exception.BadRequestException;
import com.eiasinprodhan.employee.exception.ResourceNotFoundException;
import com.eiasinprodhan.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressClient addressClient;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employeesList = employeeRepository.findAll();

        if(employeesList.isEmpty()){
            throw new ResourceNotFoundException("No Employee Found.");
        }

        List<EmployeeResponse> employeeResponseList = employeesList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .toList();

        return employeeResponseList;
    }

    @Override
    public EmployeeResponse getEmployee(Long employeeId) {
        if (employeeId == null) {
            throw new BadRequestException("Employee Id is Not Found.");
        }

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->new ResourceNotFoundException("Employee Not Found."));

        List<AddressResponse> addressResponseList = addressClient.getAddressByEmployeeId(employeeId);

        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        employeeResponse.setAddressResponseList(addressResponseList);
        return employeeResponse;
    }

    @Override
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        if (employeeId == null) {
            throw new BadRequestException("Employee Id Not Found.");
        }

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->new ResourceNotFoundException("Employee Not Found."));

        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setCode(employeeRequest.getCode());
        employee.setCompanyName(employeeRequest.getCompanyName());


        Employee updatedEmployee = employeeRepository.save(employee);

        return modelMapper.map(updatedEmployee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse deleteEmployee(Long employeeId) {
        if (employeeId == null) {
            throw new BadRequestException("Employee Id Not Found.");
        }

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->new ResourceNotFoundException("Employee Not Found."));

        employeeRepository.deleteById(employeeId);

        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        return employeeResponse;
    }
}
