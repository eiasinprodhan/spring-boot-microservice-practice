package com.eiasinprodhan.address.repository;

import com.eiasinprodhan.address.dto.response.AddressResponse;
import com.eiasinprodhan.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    public List<Address> getAddressByEmployeeId(Long employeeId);
}
