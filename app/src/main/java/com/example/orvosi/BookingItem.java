package com.example.orvosi;

public class BookingItem {
    private String diseaseName;
    private String doctorName;
    private String consultingHours;
    private float rating;

    public BookingItem(String diseaseName, String doctorName, String consultingHours, float rating) {
        this.diseaseName = diseaseName;
        this.doctorName = doctorName;
        this.consultingHours = consultingHours;
        this.rating = rating;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getConsultingHours() {
        return consultingHours;
    }

    public float getRating() {
        return rating;
    }
}
