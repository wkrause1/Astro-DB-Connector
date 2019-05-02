import java.sql.*;

/**
 * @author Will Krause
 * @version 2019-05-02
 */
public class AstronautFactory {
    final String user = "wkrause1";
    final String pass = "Legolas1";
    private String DBstatus = "";
    Astronaut astro = new Astronaut();

    AstronautFactory() {
    }

    Astronaut getAstronaut(String AID) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@Picard2:1521:itec2", user, pass);
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            String query = "Select aid, firstname, lastname, nickname, servicebranch, revnum, dateofbirth "
                    + "from astronauts "
                    + "where aid = '" + AID + "'";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                astro.setAID(rset.getString("aid"));
                astro.setFirstName(rset.getString("firstname"));
                astro.setLastName(rset.getString("lastname"));
                astro.setNickName(rset.getString("nickname"));
                astro.setServiceBranch(rset.getString("servicebranch"));
                astro.setRevNum(rset.getInt("revnum"));
                astro.setRevNumAtSelect(rset.getInt("revnum"));
                astro.setDob(rset.getDate("dateofbirth"));
            }
            conn.close();
        } catch (SQLException e) {
            DBstatus = "Error";
            System.out.println(e);
        }
        return astro;
    }
    String saveAstronaut(Astronaut AstronautToSave) {
        try {
            int newRevNum = -1;
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@Picard2:1521:itec2", user, pass);
            conn.setAutoCommit(false);
            Statement state = conn.createStatement();
            String newRevQuery = "Select revnum from astronauts where aid='" + AstronautToSave.getAID() + "'";
            System.out.println(newRevQuery);
            ResultSet rs = state.executeQuery(newRevQuery);
            while (rs.next()) {
                newRevNum = rs.getInt("revnum");
                System.out.println(newRevNum);
            }
                String query = "Update astronauts set firstname=? , lastname=?, nickname=? , servicebranch=? , dateofbirth=? where aid=?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, AstronautToSave.getFirstName());
                preparedStatement.setString(2, AstronautToSave.getLastName());
                preparedStatement.setString(3, AstronautToSave.getNickName());
                preparedStatement.setString(4, AstronautToSave.getServiceBranch());
                preparedStatement.setDate(5, AstronautToSave.getDob());
                preparedStatement.setString(6, AstronautToSave.getAID());
                if (newRevNum == AstronautToSave.getRevNumAtSelect()) {
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected == 1) {
                        conn.commit();
                    }
                }
                else {
                    DBstatus += "Error, record has been updated since initial load. Please repeat query";
                    return "Not Saved";
                }
            conn.close();
        } catch (SQLException e) {
            DBstatus = "Error, problem with database request.";
            System.out.println(e);
            return "Not Saved";
        }
        return "Saved!";
    }

    String getDBstatus() {
        return this.DBstatus;
    }

    void setDBstatus(String DBstatus) {
        this.DBstatus = DBstatus;
    }
}
