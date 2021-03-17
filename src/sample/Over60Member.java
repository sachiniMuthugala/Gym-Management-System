package sample;

import java.io.Serializable;

//Extends the application because this is a sub class of Default member super class
//Serializable used because i want to write object from this class to data file
public class Over60Member extends DefaultMember implements Serializable {
    private  int age;

    public Over60Member(int membershipNumber, String name, String membershipStartDate, int age) {
        super(membershipNumber, name, membershipStartDate, "Over60");
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Over60Member{" +
                " membershipNumber=" + membershipNumber +
                ", name='" + name + '\'' +
                ", membershipStartDate='" + membershipStartDate + '\'' +
                "age=" + age +
                ", memberType='" + memberType + '\'' +
                '}';
    }
}
