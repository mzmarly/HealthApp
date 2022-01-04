package com.example.demo.serviceImpl.diseasesCheckerImpl;

import com.example.demo.model.UserReport.DiabetesLevel;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.model.UserReport.HypertensionLevel;
import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.example.demo.repository.MonitoredHealthParametersRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BodyDimensionsService;
import com.example.demo.service.diseasesChecker.DiseasesCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class DiseasesCheckerServiceImpl implements DiseasesCheckerService {

    @Autowired
    MonitoredHealthParametersRepository monitoredHealthParametersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BodyDimensionsService bodyDimensionsService;

    @Override
    public void checkHypertension(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        UserReport userReport = getLastUserReport(login);
        List<MonitoredHealthParameters> monitoredHealthParametersList = getListMonitoredHealthParametersForUser(login);
        MonitoredHealthParameters recentHealthParameters = monitoredHealthParametersList.get(monitoredHealthParametersList.size() - 1);
        int systolicPressure = recentHealthParameters.getSystolicPressure();
        int diaSystolicPressure = recentHealthParameters.getDiaSystolicPressure();
        if (systolicPressure <= 120 && diaSystolicPressure <= 80) {
            userReport.setHypertension(HypertensionLevel.OPTIMAL);
        } else if (systolicPressure <= 129 && diaSystolicPressure <= 84) {
            userReport.setHypertension(HypertensionLevel.NORMAL);
        } else if (systolicPressure <= 139 && diaSystolicPressure <= 89) {
            userReport.setHypertension(HypertensionLevel.HIGH_NORMAL);
        } else if (systolicPressure <= 159 && diaSystolicPressure <= 99) {
            userReport.setHypertension(HypertensionLevel.GRADE_1_HYPERTENSION);
        } else if (systolicPressure <= 179 && diaSystolicPressure <= 109) {
            userReport.setHypertension(HypertensionLevel.GRADE_2_HYPERTENSION);
        } else if (systolicPressure >= 180 && diaSystolicPressure >= 110) {
            userReport.setHypertension(HypertensionLevel.GRADE_3_HYPERTENSION);
        } else if (systolicPressure >= 140 && diaSystolicPressure <= 90) {
            userReport.setHypertension(HypertensionLevel.ISOLATED_SYSTOLIC_HYPERTENSION);
        }
        userRepository.save(user);
    }

    @Override
    public void chekDiabetes(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        UserReport userReport = getLastUserReport(login);
        List<MonitoredHealthParameters> monitoredHealthParametersList = getListMonitoredHealthParametersForUser(login);
        MonitoredHealthParameters recentHealthParameters = monitoredHealthParametersList.get(monitoredHealthParametersList.size() - 1);
        int bloodSugarLevel = recentHealthParameters.getBloodSugarLevel();
        if (bloodSugarLevel >= 70 && bloodSugarLevel <= 99) {
            userReport.setDiabetes(DiabetesLevel.OPTIMAL);
        } else if (bloodSugarLevel >= 100 && bloodSugarLevel <= 125) {
            userReport.setDiabetes(DiabetesLevel.HIGH_LEVEL);
        } else if (bloodSugarLevel >= 126) {
            userReport.setDiabetes(DiabetesLevel.POTENTIAL_DIABETES);
        }else if(bloodSugarLevel<70){
            userReport.setDiabetes(DiabetesLevel.HYPOGLYCEMIA);
        }
        userRepository.save(user);
    }

    @Override
    public double checkBMI(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        UserReport userReport = getLastUserReport(login);
        double bmi=bodyDimensionsService.calculateBMI(login);
        userReport.setBmi(bmi);
        userRepository.save(user);
        return bmi;
    }

    @Override
    public double checkWHR(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        UserReport userReport = getLastUserReport(login);
        double whr=bodyDimensionsService.calculateWHR(login);
        userReport.setWhr(whr);
        userRepository.save(user);
        return whr;
    }

    public List<MonitoredHealthParameters> getListMonitoredHealthParametersForUser(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<MonitoredHealthParameters> monitoredHealthParametersList = new ArrayList<>();
        for (MonitoredHealthParameters i : user.getMonitoredHealthParameters()) {
            System.out.println("ID" + i.getMonitoredHealthParametersId());
            monitoredHealthParametersList.add(i);
        }
        System.out.println(monitoredHealthParametersList.size());
        Collections.sort(monitoredHealthParametersList);
        Collections.reverse(monitoredHealthParametersList);

        return monitoredHealthParametersList;
    }

    public UserReport getLastUserReport(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        Set<UserReport> set = user.getUserReports();
        if(set.isEmpty()){
            return new UserReport();
        }else{
        UserReport[] userArray = set.toArray(set.toArray(new UserReport[0]));
        return userArray[0];
    }}
}
/*
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background"
    tools:context="com.example.healthappfinal.ui.UserDataSegment.UserDataActivity">


    <Button
        android:id="@+id/btn_userDataHelper"
        android:layout_width="300dp"
        android:layout_height="58dp"
        android:layout_marginBottom="44dp"
        android:backgroundTint="@color/design_default_color_secondary"
        android:text="@string/modifyUserData_button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.855"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/healthApp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="50dp"
        android:layout_marginTop="16dp"
        android:gravity="center"
        android:text="@string/userData_button"
        android:textColor="@color/white"
        android:textSize="35sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        tools:layout_editor_absoluteX="49dp" />


    <TextView
        android:id="@+id/age"
        android:layout_width="wrap_content"
        android:layout_height="0dp"
        android:layout_margin="50dp"

        android:layout_marginStart="48dp"
        android:layout_marginTop="60dp"
        android:text="@string/txt_age"
        android:textColor="@color/white"
        android:textSize="25sp"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/healthApp" />

    <TextView
        android:id="@+id/txt_age"
        android:layout_width="219dp"
        android:layout_height="48dp"
        android:layout_marginBottom="12dp"
        android:background="#30ffffff"
        android:gravity="center"

        android:text="@string/txt_age"
        android:textColor="@color/white"
        android:textSize="28sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toTopOf="@+id/height"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.26"
        app:layout_constraintStart_toStartOf="parent"
        tools:ignore="TextContrastCheck" />

    <TextView
        android:id="@+id/height"
        android:layout_width="130dp"
        android:layout_height="wrap_content"
        android:layout_margin="50dp"

        android:layout_marginBottom="48dp"
        android:text="@string/txt_height"
        android:textColor="@color/white"
        android:textSize="25sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="@+id/txt_height"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/txt_height"
        android:layout_width="219dp"
        android:layout_height="48dp"
        android:background="#30ffffff"
        android:gravity="center"
        android:text="TextView"
        android:textColor="@color/white"
        android:textSize="28sp"
        android:textStyle="bold"

        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.26"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.424"
        tools:ignore="TextContrastCheck" />

    <TextView
        android:id="@+id/sex"
        android:layout_width="130dp"
        android:layout_height="wrap_content"
        android:layout_margin="50dp"

        android:layout_marginStart="16dp"
        android:layout_marginEnd="231dp"
        android:text="@string/txt_sex"
        android:textColor="@color/white"
        android:textSize="25sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="@+id/txt_sex"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/txt_sex"
        android:layout_width="219dp"
        android:layout_height="48dp"
        android:layout_marginStart="48dp"
        android:layout_marginBottom="32dp"
        android:background="#30ffffff"
        android:gravity="center"
        android:text="TextView"
        android:textColor="@color/white"
        android:textSize="28sp"
        android:textStyle="bold"

        app:layout_constraintBottom_toBottomOf="@+id/physicalActivity"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.013"
        app:layout_constraintStart_toStartOf="parent"
        tools:ignore="TextContrastCheck" />

    <TextView
        android:id="@+id/physicalActivity"
        android:layout_width="130dp"
        android:layout_height="wrap_content"
        android:layout_margin="50dp"

        android:layout_marginStart="232dp"
        android:layout_marginEnd="364dp"
        android:layout_marginBottom="48dp"
        android:text="@string/txt_physicalActivity"

        android:textColor="@color/white"
        android:textSize="25sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="@+id/txt_physicalActivity"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/txt_physicalActivity"
        android:layout_width="219dp"
        android:layout_height="48dp"
        android:layout_marginBottom="228dp"
        android:background="#30ffffff"
        android:gravity="center"
        android:text="TextView"
        android:textColor="@color/white"
        android:textSize="28sp"
        android:textStyle="bold"

        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.26"
        app:layout_constraintStart_toStartOf="parent"
        tools:ignore="TextContrastCheck" />

</androidx.constraintlayout.widget.ConstraintLayout>
 */