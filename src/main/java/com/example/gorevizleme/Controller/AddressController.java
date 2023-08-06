package com.example.gorevizleme.Controller;

import com.example.gorevizleme.Models.Address;
import com.example.gorevizleme.Services.AddressService;
import com.example.gorevizleme.Services.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<?> getAddresses(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Address>> getAdress(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddresswithID(id));
    }
    @PostMapping()
    public ResponseEntity<Address> addAdress(@RequestBody Address address){
        return ResponseEntity.ok(addressService.saveAddress(address));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id,@RequestBody Address address) throws Exception {
        return ResponseEntity.ok(addressService.updateAddress(id, address));
    }
    @DeleteMapping("/{id}")
    public void deleteAdres(@PathVariable Long id) throws Exception {
        addressService.deleteAddress(id);
        ResponseEntity.ok().body("idsi  "+ id + " olan adres başarıyla silinmiştir. ");
    }
}
