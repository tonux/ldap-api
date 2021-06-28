import java.util.Arrays;

/**
 * This CourseList class stores a list of courses
 */

public class CourseList {

    /**
     * @param listOfCourses an array of course objects
     * @param size the size of the list
     */
    private Course[] listOfCourses = new Course[10];
    private int size = 0;

    public int size() {

        return size;

    }

    /**
     * Adds a new Course object before the i-th element of the list
     */
    public void addCourse(int i, Course course) {

        // create variable list
        Course[] list = new Course[10];

        // move other course from i to end
        for (int j = i; j <= size; j++) {
            list[j + 1] = listOfCourses[j];
        }

        for (int j = i; j < size; j++) {
            listOfCourses[j + 1] = list[j + 1];
        }

        // set course at the indice i
        listOfCourses[i] = course;
        // update size
        size++;

    }

    /**
     * Deletes the i-th element of the list and return true.
     * If list is less than i-th element return false.
     */
    public boolean removeCourse(int i) {

        if (i > size)
            return false;

        // create variable list
        Course[] list = new Course[10];

        for (int j = i; j <= size; j++) {
            list[j + 1] = listOfCourses[j + 1];
        }

        for (int j = i; j < size; j++) {
            listOfCourses[j] = list[j + 1];
        }
        size--;

        return true;
    }

    /**
     * Changes the capacity of the course with this CourseID if this course is in the list and
     * then returns true. Otherwise, returns false and do nothing else.
     */
    public boolean changeCapacity(String courseID, int capacity) {

        if (courseID == null) return false;

        for (int i = 0; i < size; i++) {
            if (courseID.equals(listOfCourses[i].getCourseID())) {
                listOfCourses[i].setCapacity(capacity);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the i-th element of the list. If the list has less than i elements returns null.
     */
    public Course getCourseWithIndex(int i) {
        if (i > size)
            return null;

        return listOfCourses[i];
    }

    /**
     * Returns the index of the course with this courseID in the list.
     * If the course is not in the list, return -1 instead.
     */
    public int SearchCourseID(String courseID) {

        if (courseID == null) return -1;
        for (int i = 0; i < size; i++) {
            if (courseID.equals(listOfCourses[i].getCourseID()))
                return i;

        }

        return -1;
    }

    /**
     * Returns the index of the course with this courseName in the list
     * If the course is not in the list, return -1 instead.
     */
    public int SearchCourseName(String courseName) {
        if (courseName == null) return -1;

        for (int i = 0; i < size; i++) {
            if (courseName.equals(listOfCourses[i].getCourseName()))
                return i;

        }

        return -1;

    }

    @Override
    public String toString() {

        String result = "";

        for (int i = 0; i < size; i++) {
            if (listOfCourses[i] == null) {
                result += "-";
                continue;
            }
            result += i + ". courseID: " + listOfCourses[i].getCourseID() + ", courseName: " + listOfCourses[i].getCourseName() +
                    ", capacity: " + listOfCourses[i].getCapacity() + "\n";
        }

        return result;
    }
}
// C'est le toString() la que j'ai essaye mais j'ai pas les course dans l'ordre/ attend je regarde ok
/**
 * public String toString() {
 * <p>
 * String result="";
 * <p>
 * for (int i=0; i< size;i++)
 * {
 * result= i + "." + "courseID: " + listOfCourses[i].getCourseID() + ", courseName: " + listOfCourses[i].getCourseName()  +
 * ", capacity: " + listOfCourses[i].getCapacity();
 * }
 * return result;
 * <p>
 * }
 */