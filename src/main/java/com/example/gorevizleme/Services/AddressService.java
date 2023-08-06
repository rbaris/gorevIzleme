package com.example.gorevizleme.Services;

import com.example.gorevizleme.Models.Address;
import com.example.gorevizleme.Repos.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;

    public Address saveAddress(Address newAddress){
        addressRepository.save(newAddress);
        return newAddress;
    }
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }
    public Optional<Address> getAddresswithID(Long id){
        Optional<Address> adres = addressRepository.findById(id);
        return adres;
    }
    public Address updateAddress(Long id,Address newAddress) throws Exception {
        if(id == newAddress.getAddressID()){
            addressRepository.save(newAddress);
            return newAddress;
        }
        else{
            throw new Exception("not updated");
        }
    }
    public void deleteAddress(Long id) throws Exception {
        Optional<Address> adres = addressRepository.findById(id);
        if(adres==null) throw new Exception("Address not found!");
        else{
            addressRepository.deleteById(id);
        }

    }

}
