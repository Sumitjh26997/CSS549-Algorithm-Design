/**
 * Class to represent a student
 * The student is represented by name and student number and
 * stores an array of courses.
 * Functionality includes adding courses and computing the GPA 
 * @author Clark Olson
 */
public class Student
{
    public static final int MAX_COURSES = 50;
    
    private String name;
    private int studentNumber;
    private int numCourses;
    private Course[] courses;

    /**
     * constructor
     * pre: none
     * post: instance variables are initialized
     */
    public Student(String name, int number)
    {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }
        studentNumber = number;
        numCourses = 0;
        courses = new Course[MAX_COURSES];
    }
    
    /**
     * copy constructor
     * pre: none
     * post: Student s is copied into this object
     */
    public Student(Student s)
    {
        if (s == null) {
            System.out.println("Fatal error.");
            System.exit(0);
        }
            
        name = s.name;
        studentNumber = s.studentNumber;
        numCourses = s.numCourses;
        courses = new Course[MAX_COURSES];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(s.courses[i]);
        }
    }
    
    /**
     * accessor for name
     * pre: none
     * post: returns student name
     */
    public String getName() {
        return name;
    }
    
    /**
     * accessor for student number
     * pre: none
     * post: returns student number
     */
    public int getNumber() {
        return studentNumber;
    }
    
    /**
     * mutator to add a course to the course array
     * pre: courses has been allocated memory
     * post: course is added to array if there is sufficient space
     */
    public void addCourse(Course c)
    {
        if (c == null) return;
        
        if (numCourses < MAX_COURSES) {
            courses[numCourses] = new Course(c);
            numCourses++;
        }
    }
    
    /**
     * mutator to add a course to the course array
     * pre: courses has been allocated memory
     * post: course is created an added to array if there is sufficient space
     */
    public void addCourse(String n, int c, double g)
    {
        if (n == null) {
            n = "";
        }
        
        if (numCourses < MAX_COURSES) {
            courses[numCourses] = new Course(n, c, g);
            numCourses++;

        }
    }
    
    /**
     * access to compute and return student gpa
     * pre: courses has been allocated memory and 
     *      at least numCourses have been added to the courses array
     * post: returns the student gpa (or 0.0 if the student has not taken a class)
     */
    public double getGPA() {
        double totalcredits = 0;
        double totalpoints = 0;
        
        for (int i = 0; i < numCourses; i++) {
            totalcredits += courses[i].getCredits();
            totalpoints += courses[i].getCredits() * courses[i].getGrade();
        }
        
        if (totalcredits > 0) {
            return totalpoints / totalcredits;
        }
        else {
            return 0.0;
        }
    }
    
    /**
     * access to compute and return number of credits that student has taken
     * pre: courses array has been alocated memory and numCourses have been added to it
     * post: the total number of credits is returned
     */
    public int getCredits() {
        int totalcredits = 0;
        
        for (int i = 0; i < numCourses; i++) {
            totalcredits += courses[i].getCredits();
        }
        return totalcredits;
    }
    
    /**
     * Output the courses that a student has taken
     * pre: courses has been allocated memory and numCourses elements have been added
     * post: All of the courses are output to System.out
     */
    public void outputHistory() {
        System.out.println("History for: " + name);
        for (int i = 0; i < numCourses; i++) {
            System.out.println(courses[i]);
        }
    }
    
    /**
     * toString
     * pre: courses has been allocated memory and numCourses elements have been added correctly
     * post: The student name, student number, and gpa are concatenated in a string and returned
     */
    public String toString() {
        return name + " (" + studentNumber + ") GPA: " + getGPA();
    }
}
