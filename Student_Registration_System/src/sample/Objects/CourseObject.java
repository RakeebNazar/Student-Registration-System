package sample.Objects;

public class CourseObject {
    int CourseID;
    String NameOfTheCourse;
    String Duration;
    int CourseFee;

    public CourseObject(int courseID, String nameOfTheCourse, String duration, int courseFee) {
        CourseID = courseID;
        NameOfTheCourse = nameOfTheCourse;
        Duration = duration;
        CourseFee = courseFee;
    }
    public CourseObject(){}


    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getNameOfTheCourse() {
        return NameOfTheCourse;
    }

    public void setNameOfTheCourse(String nameOfTheCourse) {
        NameOfTheCourse = nameOfTheCourse;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public int getCourseFee() {
        return CourseFee;
    }

    public void setCourseFee(int courseFee) {
        CourseFee = courseFee;
    }

    @Override               //// The ChoiceBox uses the toString() method of our object to display options in the dropdown. //
    public String toString() {
        String value = getNameOfTheCourse()+" ("+getCourseID()+")";
        return value;
    }
}
