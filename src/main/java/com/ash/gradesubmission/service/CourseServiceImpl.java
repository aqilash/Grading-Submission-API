package com.ash.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.gradesubmission.entity.Course;
import com.ash.gradesubmission.entity.Student;
import com.ash.gradesubmission.exception.CourseNotFoundException;
import com.ash.gradesubmission.repository.CourseRepository;
import com.ash.gradesubmission.repository.StudentRepository;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent())
            return course.get();
        else
            throw new CourseNotFoundException(id);
    }

    @Override
    public Course saveCourse(Course course, Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        course.setStudent(student);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

}
