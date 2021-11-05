package com.example.demo.model.BasicUserDataInfo;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "basicUserData")
@Data
public class BasicUserData {
    @Id
    @Column(name = "basicUserData_id")
    private long basicUserDataId;

    @Column(name = "age")
    private int age;

    public BasicUserData(long basicUserDataId, int age, Sex sex, PhysicalActivity physicalActivity) {
        this.basicUserDataId = basicUserDataId;
        this.age = age;
        this.sex = sex;
        this.physicalActivity = physicalActivity;
    }
    public BasicUserData() {

    }
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "physicalActivity")
    private PhysicalActivity physicalActivity;

}
