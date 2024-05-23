package repository;

import exception.CourseNotFoundException;
import exception.DuplicateCourseException;
import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        boolean exists = courses.stream().anyMatch(c -> c.getTitle().equalsIgnoreCase(course.getTitle()));
        if (exists) {
            throw new DuplicateCourseException("A course with the title '" + course.getTitle() + "' already exists.");
        }
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> getCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public List<Course> getCoursesByTitle(String title) {
        List<Course> result = courses.stream()
                .filter(course -> course.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new CourseNotFoundException("No course found with the title '" + title + "'.");
        }
        return result;
    }

    public void removeCourseById(int id) {
        boolean removed = courses.removeIf(course -> course.getId() == id);
        if (!removed) {
            throw new CourseNotFoundException("No course found with the ID '" + id + "'.");
        }
    }
}
