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

    @Column(name = "height")
    private double height;

    public BasicUserData(long basicUserDataId, int age, Sex sex, PhysicalActivity physicalActivity,double height) {
        this.basicUserDataId = basicUserDataId;
        this.age = age;
        this.sex = sex;
        this.physicalActivity = physicalActivity;
        this.height = height;
    }
    public BasicUserData() {

    }
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "physicalActivity")
    private PhysicalActivity physicalActivity;

}
