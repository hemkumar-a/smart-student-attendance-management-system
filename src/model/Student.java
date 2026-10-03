package model;

public class Student {

    private int id;
    private String registrationNo;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String department;
    private int year;
    private String section;
    private String qrCode;

    public Student() {
    }

    public Student(int id, String registrationNo, String name,
                   String email, String phone, String gender,
                   String department, int year, String section,
                   String qrCode) {

        this.id = id;
        this.registrationNo = registrationNo;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.department = department;
        this.year = year;
        this.section = section;
        this.qrCode = qrCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}