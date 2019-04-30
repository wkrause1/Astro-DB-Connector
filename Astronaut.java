import java.sql.*;
import java.io.*;

public class Astronaut
{
    public  String AID, firstName, lastName, nickName, serviceBranch;
    public  int revNum;
    private Connection conn;
    private Statement stmt;
    public Date dob;
    private int connected;
    private String user, pass;
    private String query;
    private String DBstatus;

    public Astronaut(String ID)
    {
        AID = ID;
        firstName = "";
        lastName = "";
        nickName = "";
        serviceBranch = "";
        revNum = 0;
        dob = new Date(0000,00,00);
        DBstatus = "";
        user = "wkrause1";
        pass = "Legolas1";

        try
        {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@Picard2:1521:itec2",user,pass);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            query = "Select aid, firstname, lastname, nickname, servicebranch, revnum, dateofbirth "
                    + "from astronauts "
                    + "where aid = '" + this.AID + "'";
            System.out.println(query);

            ResultSet rset = stmt.executeQuery(query);
            while(rset.next())
            {
                AID = rset.getString("aid");
                firstName = rset.getString("firstname");
                lastName = rset.getString("lastname");
                nickName = rset.getString("nickname");
                serviceBranch = rset.getString("servicebranch");
                revNum = rset.getInt("revnum");
                dob = rset.getDate("dateofbirth");
                DBstatus = "Found";
            }
            conn.close();
        }
        catch(SQLException e)
        {
            DBstatus = "Error";
            System.out.println(e);
        }
    }

    public int save(){
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@Picard2:1521:itec2", user, pass);
            conn.setAutoCommit(false);
            String query = "Update astronauts set firstname=? , lastname=?, nickname=? , servicebranch=? , dateofbirth=? where aid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, this.firstName);
            preparedStatement.setString(2, this.lastName);
            preparedStatement.setString(3, this.nickName);
            preparedStatement.setString(4, this.serviceBranch);
            preparedStatement.setDate(5, this.dob);
            preparedStatement.setString(6, this.AID);
            System.out.println(preparedStatement);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected ==1) {
                conn.commit();
            }
            conn.close();
            return rowsAffected;
        }
        catch (SQLException e) {
            DBstatus = "Error";
            System.out.println(e);
            return 0;
        }
    }

}


