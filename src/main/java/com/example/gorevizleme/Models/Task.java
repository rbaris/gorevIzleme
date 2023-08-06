package com.example.gorevizleme.Models;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long taskID;
    public String taskContent;
    @OneToMany(cascade = CascadeType.ALL)
    public List<User> pilots; //pilot ve copilot
    @OneToOne(cascade = CascadeType.ALL)
    public Address taskAddress;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Medicine> taskMedicines;
    public Float taskPay; //kastımız maliyet mi görevden kazanılan ücret mi sormak lazım kazanılan ücretmiş.
    public int isDone ; // 1-yapıldı,2-yapılmadı,3-yapılıyor,4- iptal edildi.


}
