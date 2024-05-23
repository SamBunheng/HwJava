import service.CourseService;
import service.CourseServiceImp;
import view.View;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static CourseService courseService = new CourseServiceImp();
    public static void main(String[] args) {
        while (true){
            switch (View.menu()){
                case 0-> {System.exit(0);}
                case 1-> courseService.addNewCourse();
                case 2-> courseService.getCourse();
                case 3-> courseService.getCoursebyId();
                case 4-> courseService.getCoursebyTitle();
                case 5-> courseService.removeCourseByID();
            }
        }
    }
}