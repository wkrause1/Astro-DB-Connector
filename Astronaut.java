import java.sql.*;

/**
 * @author Will Krause
 * @version 2019-05-02
 */

class Astronaut {
    private String AID, firstName, lastName, nickName, serviceBranch;
    private int revNum;
    private int revNumAtSelect;
    private Date dob;

    Astronaut() {
    }

    void setRevNum(int num) {
        this.revNum = num;
    }

    void setRevNumAtSelect(int num) {
        this.revNumAtSelect = num;
    }

    void setAID(String AID) {
        this.AID = AID;
    }

    void setFirstName(String fname) {
        this.firstName = fname;
    }

    void setLastName(String lname) {
        this.lastName = lname;
    }

    void setNickName(String nname) {
        this.nickName = nname;
    }

    void setServiceBranch(String serviceBranch) {
        this.serviceBranch = serviceBranch;
    }

    void setDob(Date date) {
        this.dob = date;
    }

    String getAID() {
        return this.AID;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    String getNickName() {
        return this.nickName;
    }

    String getServiceBranch() {
        return this.serviceBranch;
    }

    Date getDob() {
        return this.dob;
    }

    int getRevNum() {
        return this.revNum;
    }

    int getRevNumAtSelect() {
        return this.revNumAtSelect;
    }
}