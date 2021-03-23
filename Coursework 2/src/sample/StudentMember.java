package sample;

import java.io.Serializable;

//Extends the application because this is a sub class of Default member super class
//Serializable used because i want to write object from this class to data file
public class StudentMember extends DefaultMember implements Serializable {
    private String school;

    public StudentMember(int membershipNumber, String name, String membershipStartDate, String school) {
        super(membershipNumber, name, membershipStartDate, "Student");
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "StudentMember{" +
                " Membership Number=" + membershipNumber +
                ", Name='" + name + '\'' +
                ", Membership Start Date='" + membershipStartDate + '\'' +
                ", School='" + school + '\'' +
                ", Member Type='" + memberType + '\'' +
                '}';
    }
}
