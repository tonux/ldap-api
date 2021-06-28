public class TestMain {
        public static void main(String []args){
            System.out.println("Hello World");

            Course c1 = new Course("TP1", "TP1 java", 30);
            Course c2 = new Course("TP2", "TP2 java", 20);
            Course c3 = new Course("TP3", "TP3 java", 50);
            Course c4 = new Course("TP4", "TP4 java", 40);

            CourseList courseList = new CourseList();
            courseList.addCourse(0, c1);
            courseList.addCourse(1, c2);
            courseList.addCourse(2, c3);

            System.out.println(courseList.toString());
            courseList.addCourse(1, c4);
            System.out.println(courseList.toString());
            courseList.removeCourse(1);
            System.out.println(courseList.toString());
            courseList.changeCapacity("TP1", 60);
            System.out.println(courseList.toString());

            // je vais faire une classe de test pour voir ok ok

            // tu peux prendre le code est mettre à jour le tien?
            // apres tu test et me dis?D'accord ca marche

            // J'ai teste et je t'envoie les screenchot sur twitter
        }
    }
