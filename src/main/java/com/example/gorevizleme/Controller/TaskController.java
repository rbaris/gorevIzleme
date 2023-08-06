package com.example.gorevizleme.Controller;

import com.example.gorevizleme.Models.Address;
import com.example.gorevizleme.Models.Medicine;
import com.example.gorevizleme.Models.Task;
import com.example.gorevizleme.Models.User;
import com.example.gorevizleme.Services.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    @PostMapping()
    public ResponseEntity<Task> addnewTask(@RequestBody Task newTask){
        return ResponseEntity.ok(taskService.addTask(newTask));
    }
    @GetMapping()
    public ResponseEntity<?> getTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTaskbyID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task newTask) throws Exception {
        return ResponseEntity.ok(taskService.updateTask(id,newTask));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) throws Exception {
        Optional<Task> task =taskService.getTaskbyID(id);
        taskService.deleteTask(id);
        return ResponseEntity.ok().body(task.get().taskContent + " içeriğine sahip görev başarıyla silindi.");
    }
    @PostMapping("/{id}/medicines")
    public ResponseEntity<?> addMedicinetotask(@PathVariable Long id, @RequestBody Medicine medicine){
        Optional<Task> task = taskService.getTaskbyID(id);
        taskService.addMedicinetoTask(medicine.medicineID,id);
        taskService.addTask(task.get());
        return ResponseEntity.ok(task);
    }
    @GetMapping("{id}/medicines")
    public ResponseEntity<?> getMedicinesofTask(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskbyID(id);
        return ResponseEntity.ok(task.get().taskMedicines);
    }
    @DeleteMapping("{id}/medicines")
    public ResponseEntity<?> removeMedicinetoTask(@PathVariable Long id,@RequestBody Medicine medicine){
        Optional<Task> task = taskService.getTaskbyID(id);
        taskService.removeMedicinetoTask(medicine.getMedicineID(),id);
        taskService.addTask(task.get());
        return ResponseEntity.ok().body("görev id "+task.get().taskID +" olan görevin ilaçlarından "+ medicine.getMedicineName()+" isimli ilaç çıkarılmıştır." );
    }
    @PostMapping("/{id}/pilots")
    public ResponseEntity<?> addPilottoTask(@PathVariable Long id,@RequestBody User pilot){
        Optional<Task> task = taskService.getTaskbyID(id);
        taskService.addPilottoTask(pilot.getUserID(),task.get().getTaskID());
        taskService.addTask(task.get());
        return ResponseEntity.ok(task);
    }
    @GetMapping("/{id}/pilots")
    public ResponseEntity<?> getpilotsofTask(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskbyID(id);
        return ResponseEntity.ok(task.get().pilots);
    }
    @DeleteMapping("/{id}/pilots")
    public ResponseEntity<?> deletepilotofTask(@PathVariable Long id,@RequestBody User deletingPilot){
        Optional<Task> task = taskService.getTaskbyID(id);
        taskService.removePilottoTask(deletingPilot.getUserID(),id);
        return ResponseEntity.ok().body(deletingPilot.username+" isimli pilot, görev id "+id+"  olan görevden çıkarılmıştır. ");
    }
    @PostMapping("/{id}/address")
    public ResponseEntity<Task> addAdresstoTask(@PathVariable Long id,@RequestBody Address address){
        Optional<Task> task = taskService.getTaskbyID(id);
        taskService.addAddresstoTask(address.addressID, task.get().taskID);
        return ResponseEntity.ok(task.get());
    }
    @GetMapping("/{id}/address")
    public ResponseEntity<Address> getAdressofTask(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskbyID(id);
        return ResponseEntity.ok(task.get().taskAddress);
    }
    @DeleteMapping("/{id}/address")
    public ResponseEntity<?> deleteAdressofTask(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskbyID(id);
        taskService.removeAddresstoTask(id);
        return ResponseEntity.ok().body("görev id "+ task.get().taskID +" olan görevin adresi sıfırlanmıştır.");
    }



}
