package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Enrollment;
import com.example.service.CourseService;
import com.example.service.EnrollmentService;
import com.example.service.StudentService;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listEnrollments(Model model) {
        model.addAttribute("enrollments", enrollmentService.getAllEnrollments());
        return "enrollmentList";
    }

    @GetMapping("/new")
    public String newEnrollmentForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "enrollmentForm";
    }

    @PostMapping
    public String saveEnrollment(@ModelAttribute Enrollment enrollment) {
        enrollmentService.saveEnrollment(enrollment);
        return "redirect:/enrollments";
    }

    @GetMapping("/{id}")
    public String editEnrollmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("enrollment", enrollmentService.getEnrollmentById(id));
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "enrollmentForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return "redirect:/enrollments";
    }
}
