package com.eiasinprodhan.address.service;

import com.eiasinprodhan.address.client.EmployeeClient;
import com.eiasinprodhan.address.dto.request.AddressRequest;
import com.eiasinprodhan.address.dto.response.AddressResponse;
import com.eiasinprodhan.address.dto.response.EmployeeResponse;
import com.eiasinprodhan.address.entity.Address;
import com.eiasinprodhan.address.exception.BadRequestException;
import com.eiasinprodhan.address.exception.ResourceNotFoundException;
import com.eiasinprodhan.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final EmployeeClient employeeClient;

    @Override
    public AddressResponse saveAddress(AddressRequest addressRequest) {

        EmployeeResponse employeeResponse = employeeClient.getEmployee(addressRequest.getEmployeeId());

        if (addressRequest == null) {
            throw new BadRequestException("AddressRequest is Null");
        }

        Address address = modelMapper.map(addressRequest, Address.class);

        address.setId(null);

        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressResponse.class);
    }

    @Override
    public List<AddressResponse> getAllAddress() {

        List<Address> addressList = addressRepository.findAll();

        if (addressList.isEmpty()) {
            throw new ResourceNotFoundException("No Address Found.");
        }

        List<AddressResponse> addressResponseList = addressList.stream().map(address -> modelMapper.map(address, AddressResponse.class)).toList();

        return addressResponseList;
    }

    @Override
    public AddressResponse getAddress(Long addressId) {
        if (addressId == null) {
            throw new BadRequestException("AddressId is Null");
        }

        Address  address = addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address Not Found."));

        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);

        return addressResponse;
    }

    @Override
    public AddressResponse updateAddress(Long addressId, AddressRequest addressRequest) {

        if (addressId == null) {
            throw new BadRequestException("AddressId is Null");
        }

        if (addressRequest == null) {
            throw new BadRequestException("AddressRequest is Null");
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address Not Found."));

        address.setEmployeeId(addressRequest.getEmployeeId());
        address.setStreet(addressRequest.getStreet());
        address.setZipCode(addressRequest.getZipCode());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setAddressType(addressRequest.getAddressType());

        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressResponse.class);
    }

    @Override
    public AddressResponse deleteAddress(Long addressId) {
        if (addressId == null) {
            throw new BadRequestException("AddressId is Null");
        }

        Address address = addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address Not Found."));

        if(address == null) {
            throw new ResourceNotFoundException("Address Not Found.");
        }

        addressRepository.delete(address);

        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);

        return addressResponse;
    }

    @Override
    public List<AddressResponse> getAddressByEmployeeId(Long employeeId) {
        if (employeeId == null) {
            throw new BadRequestException("EmployeeId is Null");
        }

        List<Address> addressList = addressRepository.getAddressByEmployeeId(employeeId);

        List<AddressResponse> addressResponseList = addressList.stream().map(address -> modelMapper.map(address, AddressResponse.class)).toList();

        return addressResponseList;
    }
}
