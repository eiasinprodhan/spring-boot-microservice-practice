package com.eiasinprodhan.address.controller;

import com.eiasinprodhan.address.dto.request.AddressRequest;
import com.eiasinprodhan.address.dto.response.AddressResponse;
import com.eiasinprodhan.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressRestController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> saveAddress(@RequestBody  AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.saveAddress(addressRequest);

        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddress() {
        List<AddressResponse> addressResponseList = addressService.getAllAddress();
        return new  ResponseEntity<>(addressResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Long id) {
        AddressResponse addressResponse = addressService.getAddress(id);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.updateAddress(id, addressRequest);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AddressResponse> deleteAddress(@PathVariable Long id) {
        AddressResponse addressResponse = addressService.deleteAddress(id);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<AddressResponse>> getAddressByEmployeeId(@PathVariable Long id) {
        List<AddressResponse> addressResponseList = addressService.getAddressByEmployeeId(id);
        return new ResponseEntity<>(addressResponseList, HttpStatus.OK);
    }
}
