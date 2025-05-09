package com.example.controller;

import com.example.model.Course;
import com.example.service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET /courses: Fetch courses from DB and display in courses.jsp
    @GetMapping("/courses")
    public String showCourses(HttpSession session, Model model) {
        // Check if user is logged in
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "redirect:/login";
        }

        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses"; // resolves to /WEB-INF/views/courses.jsp
    }
    
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpSession session, Model model) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "redirect:/login";
        }

        boolean success = courseService.enrollStudent(userEmail, courseId);
        if (success) {
            logger.info("User '{}' successfully registered for course ID: {}", userEmail, courseId);
            model.addAttribute("courseId", courseId);
            return "success";
        } else {
            logger.error("User '{}' failed to register for course ID: {}", userEmail, courseId);
            model.addAttribute("error", "Registration failed.");
            model.addAttribute("courses", courseService.getAllCourses());
            return "courses";
        }
    }

    
}
