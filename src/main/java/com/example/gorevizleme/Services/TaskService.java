package com.example.gorevizleme.Services;

import com.example.gorevizleme.Models.Address;
import com.example.gorevizleme.Models.Medicine;
import com.example.gorevizleme.Models.Task;
import com.example.gorevizleme.Models.User;
import com.example.gorevizleme.Repos.AddressRepository;
import com.example.gorevizleme.Repos.MedicineRepository;
import com.example.gorevizleme.Repos.TaskRepository;
import com.example.gorevizleme.Repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final MedicineRepository medicineRepository;

    public Optional<Task> getTaskbyID(Long id){
        return taskRepository.findById(id);
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task addTask(Task newTask){
        taskRepository.save(newTask);
        return newTask;
    }
    public Task updateTask(Long id,Task newTask) throws Exception {
        if(id==newTask.getTaskID()){
            taskRepository.save(newTask);
            return newTask;
        }
        else {
            throw new Exception("not updated!");
        }
    }
    public void deleteTask(Long id) throws Exception {
        Optional<Task> task = taskRepository.findById(id);
        if(task == null){
            throw new Exception("not found!");
        }else{
            taskRepository.deleteById(id);
        }
    }
    public void addPilottoTask(Long pilotID,Long taskID){
        Optional<User> user = userRepository.findById(pilotID);
        Optional<Task> task = taskRepository.findById(taskID);
        task.get().pilots.add(user.get());
        taskRepository.save(task.get());
        log.info("{} isimli pilot, görev id {} olan göreve atandı. ", user.get().username ,task.get().taskID);
    }
    public void addAddresstoTask(Long addressID,Long taskID){
        Optional<Address> address = addressRepository.findById(addressID);
        Optional<Task> task = taskRepository.findById(taskID);
        task.get().taskAddress = address.get();
        taskRepository.save(task.get());
        log.info("görev id {} olan görevin adresi idsi {} olan adres ile şeklinde değiştirilmiştir.",taskID,address.get().addressID);
    }
    public void addMedicinetoTask(Long medicineID,Long taskID){
        Optional<Medicine> medicine = medicineRepository.findById(medicineID);
        Optional<Task> task = taskRepository.findById(taskID);
        task.get().getTaskMedicines().add(medicine.get());
        taskRepository.save(task.get());
        log.info("görev id {} olan görevin ilaçlarına {} isimli ilaç eklenmiştir.",taskID,medicine.get().medicineName);
    }
    public void removeMedicinetoTask(Long medicineID,Long taskID){
        Optional<Medicine> medicine = medicineRepository.findById(medicineID);
        Optional<Task> task = taskRepository.findById(taskID);
        task.get().getTaskMedicines().remove(medicine.get());
        taskRepository.save(task.get());
        log.info("görev id {} olan görevin ilaçlarından {} isimli ilaç çıkarılmıştır.",taskID,medicine.get().medicineName);
    }
    public void removePilottoTask(Long pilotID,Long taskID){
        Optional<User> pilot = userRepository.findById(pilotID);
        Optional<Task> task = taskRepository.findById(taskID);
        task.get().getPilots().remove(pilot.get());
        taskRepository.save(task.get());
        log.info("{} isimli pilot, görev id {} olan görevden çıkarılmıştır. ", pilot.get().username ,task.get().taskID);
    }
    public void removeAddresstoTask(Long taskID){
        Optional<Task> task = taskRepository.findById(taskID);
        task.get().taskAddress = null;
        taskRepository.save(task.get());
        log.info("görev id {} olan görevin adresi sıfırlanmıştır.",taskID);
    }
}
