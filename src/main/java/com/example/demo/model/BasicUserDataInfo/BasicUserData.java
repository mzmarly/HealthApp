package com.example.demo.model.BasicUserDataInfo;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "basicUserData")
@Data
public class BasicUserData implements Comparable<BasicUserData> {
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

    @Override
    public int compareTo(BasicUserData o) {
        return Long.compare(this.getBasicUserDataId(),o.getBasicUserDataId());
    }
}
