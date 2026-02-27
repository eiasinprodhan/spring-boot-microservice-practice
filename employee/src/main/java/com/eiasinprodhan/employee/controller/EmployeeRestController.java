package com.eiasinprodhan.employee.controller;

import com.eiasinprodhan.employee.dto.request.EmployeeRequest;
import com.eiasinprodhan.employee.dto.response.EmployeeResponse;
import com.eiasinprodhan.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.saveEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employeeResponseList = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.getEmployee(id);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
