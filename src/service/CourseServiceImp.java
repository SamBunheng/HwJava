package service;

import exception.CourseNotFoundException;
import exception.DuplicateCourseException;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.CourseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository = new CourseRepository();

    @Override
    public void addNewCourse() {
        System.out.println("=".repeat(40));
        String title;
        String[] name;
        String[] require;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert course title:");
        title = scanner.nextLine();
        System.out.print("Insert instructor names:");
        name = scanner.nextLine().split(",");

        System.out.print("Insert course requirement:");
        require = scanner.nextLine().split(",");

        Course course = new Course(new Random().nextInt(100), title, name, require, LocalDate.now());
        try {
            courseRepository.addCourse(course);
            System.out.println("[+] Added new course successfully");
        } catch (DuplicateCourseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void getCourse() {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        table.addCell("course_id");
        table.addCell("course_title");
        table.addCell("instructor name");
        table.addCell("requirement");
        table.addCell("started date");

        courseRepository.getAllCourses().forEach(course -> {
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getTitle());
            table.addCell(String.join(", ", course.getInstructorName()));
            table.addCell(String.join(", ", course.getRequirement()));
            table.addCell(course.getStartDate().toString());
        });
        System.out.println(table.render());
    }

    @Override
    public void getCoursebyId() {
        System.out.println("Enter Course ID:");
        int id = new Scanner(System.in).nextInt();
        try {
            Course course = courseRepository.getCourseById(id)
                    .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + id));

            Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            table.addCell("course_id");
            table.addCell("course_title");
            table.addCell("instructor name");
            table.addCell("requirement");
            table.addCell("started date");

            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getTitle());
            table.addCell(String.join(", ", course.getInstructorName()));
            table.addCell(String.join(", ", course.getRequirement()));
            table.addCell(course.getStartDate().toString());

            System.out.println(table.render());
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    public void getCoursebyTitle() {
        System.out.println("Enter Course Title:");
        String title = new Scanner(System.in).nextLine();
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        table.addCell("course_id");
        table.addCell("course_title");
        table.addCell("instructor name");
        table.addCell("requirement");
        table.addCell("started date");
        try {
            List<Course> courses = courseRepository.getCoursesByTitle(title);
            for (Course course : courses) {
                table.addCell(String.valueOf(course.getId()));
                table.addCell(course.getTitle());
                table.addCell(String.join(", ", course.getInstructorName()));
                table.addCell(String.join(", ", course.getRequirement()));
                table.addCell(course.getStartDate().toString());
            }
            System.out.println(table.render());
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    @Override
    public void removeCourseByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Course ID to remove:");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            try {
                courseRepository.removeCourseById(id);
                System.out.println("Course with ID " + id + " has been removed successfully.");
            } catch (CourseNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Please enter a valid integer for the course ID.");
        }
    }
}
