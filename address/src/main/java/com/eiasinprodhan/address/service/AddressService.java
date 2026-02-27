package com.eiasinprodhan.address.service;

import com.eiasinprodhan.address.dto.request.AddressRequest;
import com.eiasinprodhan.address.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    public AddressResponse saveAddress(AddressRequest addressRequest);

    public List<AddressResponse> getAllAddress();

    public AddressResponse getAddress(Long addressId);

    public AddressResponse updateAddress(Long addressId, AddressRequest addressRequest);

    public AddressResponse deleteAddress(Long addressId);

    public List<AddressResponse> getAddressByEmployeeId(Long employeeId);

}
