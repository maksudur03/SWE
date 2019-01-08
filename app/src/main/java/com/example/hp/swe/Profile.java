package com.example.hp.swe;

public class Profile {
    String name,registration_number,blood_group,dob,phone_number,emergency_number,email,role;

    public Profile(String name,String registration_number,String blood_group,String dob,String phone_number,String emergency_number, String email, String role){
        this.name = name;
        this.registration_number = registration_number;
        this.blood_group = blood_group;
        this.dob = dob;
        this.phone_number = phone_number;
        this.emergency_number = emergency_number;
        this.email = email;
        this.role = role;
    }

    public Profile(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmergency_number(String emergency_number) {
        this.emergency_number = emergency_number;
    }

    public String getEmergency_number() {
        return emergency_number;
    }



    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
