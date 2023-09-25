package sample.databaseHandler;



import java.sql.*;

public class DAO {

    Connection con = null;

    
    public void connector() throws Exception {
        //Class.forName("org.sql.JDBC");

        String url = "jdbc:sqlite:studentregistration.db";

        con = DriverManager.getConnection(url);
    }




    // definitions

    public void insertCourse (String name, String duration,int fee) throws SQLException {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Query = "insert into course (NameOfTheCourse,Duration,TotalFee) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(Query);
        ps.setString(1,name);
        ps.setString(2,duration);
        ps.setInt(3,fee);

        ps.executeUpdate();
        con.close();

    }

    public ResultSet getCourse()  {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String Query = "select * from course";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rs;

    }

    public void delete(int courseID) throws SQLException {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Query = "delete from course where CourseID = ?";
        PreparedStatement ps = con.prepareStatement(Query);
        ps.setInt(1,courseID);


        ps.executeUpdate();
        con.close();



    }

    public void update(String name,String duration,int fee,int id) throws SQLException {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Query = " UPDATE course SET NameOfTheCourse = ?, Duration = ?, TotalFee = ? WHERE CourseID = ?";
        PreparedStatement ps = con.prepareStatement(Query);
        ps.setString(1,name);
        ps.setString(2,duration);
        ps.setInt(3,fee);
        ps.setInt(4,id);


        ps.executeUpdate();
        con.close();



    }




    //METHODS //

    public void insertStudent (String name, String datebirth,int number,int courseid) throws SQLException {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Query = "insert into Student (DateOfBirth,StudentName,ContactNumber,CourseID) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(Query);
        ps.setString(1,datebirth);
        ps.setString(2,name);
        ps.setInt(3,number);
        ps.setInt(4,courseid);

        ps.executeUpdate();
        con.close();

    }


    public ResultSet getStudent()  {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String Query = "select * from student";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rs;

    }

    public void updateS(String name,String datebirth,int number,int id,int course) throws SQLException {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Query = " UPDATE student SET DateOfBirth = ?, StudentName = ?, ContactNumber = ? ,CourseID = ? WHERE StudentID = ?";
        PreparedStatement ps = con.prepareStatement(Query);
        ps.setString(1,datebirth);
        ps.setString(2,name);
        ps.setInt(3,number);
        ps.setInt(4,course);
        ps.setInt(5,id);


        ps.executeUpdate();
        con.close();



    }

    public void deleteS(int StudentID) throws SQLException {
        try {
            connector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Query = "delete from student where StudentID = ?";
        PreparedStatement ps = con.prepareStatement(Query);
        ps.setInt(1,StudentID);


        ps.executeUpdate();
        con.close();



    }








}
