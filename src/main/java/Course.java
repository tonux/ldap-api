/**
 * This Course class stores information of a course
 */

public class Course {
	
	/**
	 * @param courseID the ID of the course
	 * @param courseName the complete name of the course 
	 * @param capacity the initial capacity limit of this course
	 */
	private String courseID; 
	private String courseName;
	private int capacity;
	
	public Course()
	{
		   this.courseID = courseID;
	       this.courseName = courseName;
	       this.capacity = capacity;
	}
	
	public Course(String courseID, String courseName, int capacity) {
	       
	       this.courseID = courseID;
	       this.courseName = courseName;
	       this.capacity = capacity;
	       
	   }
	   public String getCourseID() {
	       return courseID;
	   }
	   public String getCourseName() {
	       return courseName;
	   }
	   
	   public int getCapacity() {
	       return capacity;
	   }

	   // ajoute les setters ok
	   // pour les setters j'ai besoin de user input ou pas?
	   // non tu dois ajouter juste la variable sur le header like this ok
	   // correct? good
	   public void setCourseID(String courseID)
	   {
			this.courseID = courseID;
	   }
	 public void setCourseName(String courseName)
	   {
		   this.courseName = courseName;
	   }

	    public void setCapacity(int capacity)
	   {
		   this. capacity = capacity;
	   }
}
