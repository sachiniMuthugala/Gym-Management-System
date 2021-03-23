package sample;

//Encapsulation ---> binding data with methods
//it means using getters and setters

//Inheritance ---> using super class methods in sub classes

import java.io.Serializable;

//Serializable used because i want to write object from this class to data file
public class DefaultMember implements Serializable {

    protected int membershipNumber;     //Protected is used because its a child parent modifier
    protected String name;
    protected String membershipStartDate;
    protected String memberType;

    public DefaultMember(int membershipNumber, String name, String membershipStartDate, String memberType) {
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.membershipStartDate = membershipStartDate;
        this.memberType = memberType;
    }

    //Adding getters , setters

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return "DefaultMember{" +
                " Membership Number=" + membershipNumber +
                ", Name='" + name + '\'' +
                ", Membership Start Date='" + membershipStartDate + '\'' +
                ", Member Type='" + memberType + '\'' +
                '}';
    }
}


























