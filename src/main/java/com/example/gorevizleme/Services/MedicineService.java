package com.example.gorevizleme.Services;

import com.example.gorevizleme.Models.Medicine;
import com.example.gorevizleme.Repos.MedicineRepository;
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
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }
    public Medicine saveMedicine(Medicine medicine){
        medicineRepository.save(medicine);
        return medicine;
    }
    public Optional<Medicine> getMedicinewithid(Long id){
        return medicineRepository.findById(id);
    }
    public void deleteMedicine(Long id){
        medicineRepository.deleteById(id);
    }
    public Medicine updateMedicine(Long id,Medicine newMedicine) throws Exception {
        if(id==newMedicine.getMedicineID()){
            medicineRepository.save(newMedicine);
            return newMedicine;
        }else{
            throw new Exception("not updated ! ");
        }
    }
}
