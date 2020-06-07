package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import controllers.Accounts;
import play.Logger;
import play.mvc.Controller;

import play.Logger;
import play.db.jpa.Model;


@Entity
public class Member extends Model {
    public String name;
    public int age;
    public String email;
    public String gender;
    public String password;
    public String address;
    public double height;
    public double weight;
    public double bmi;
    public String bmiresult;
    public double isIdealBodyWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String name, int age, String email, String password, String address, String gender, double height, double weight, double bmi, String bmiresult, double isIdealBodyWeight) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiresult = bmiresult;
        this.isIdealBodyWeight = isIdealBodyWeight;


    }

    public Member(String name, int age, String email, String password, String gender, String address, double height, double weight) {
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }


    public void getBMI() {
        double bmi;
        if (assessments.size() > 0) {
            Assessment assessment = assessments.get(assessments.size() - 1);
            bmi = assessment.weight / (height * height);

            this.bmi = bmi;
        }

    }

    public void bmiAnalytics() {

        String result = " ";
        if (bmi < 16) {
            result = "Severly Underwight";
        } else if ((bmi >= 16) && (bmi < 18.5)) {
            result = "Underweight";
        } else if ((bmi >= 18.5) && (bmi < 25)) {
            result = "Normal";
        } else if ((bmi >= 25) && (bmi < 30)) {
            result = "Overweight";
        } else if ((bmi >= 30) && (bmi < 35)) {
            result = "Moderately Obese";
        } else if (bmi >= 35) {
            result = "Severly Obese";
        }

        this.bmiresult = result;

    }

}