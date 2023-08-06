package com.example.gorevizleme.Controller;

import com.example.gorevizleme.Models.Medicine;
import com.example.gorevizleme.Services.MedicineService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping()
    public ResponseEntity<?> getMedicines(){
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }
    @PostMapping()
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine){
        return ResponseEntity.ok(medicineService.saveMedicine(medicine));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Medicine>> getMedicinebyID(@PathVariable Long id){
        return ResponseEntity.ok(medicineService.getMedicinewithid(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicinewithID(@PathVariable Long id,@RequestBody Medicine newMedicine) throws Exception {
        return ResponseEntity.ok(medicineService.updateMedicine(id,newMedicine));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletewithID(@PathVariable Long id){
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok().body("idsi "+id+" olan ilaç başarıyla silinmiştir.");
    }


}
