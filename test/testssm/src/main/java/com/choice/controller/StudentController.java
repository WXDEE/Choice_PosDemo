package com.choice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choice.entity.Student;
import com.choice.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studnetService;
	
	@RequestMapping("/save")
	public String save(Student student){
		/*Student student = new Student(11, "kkkkk", 99, 16, "male");*/
		System.out.println(student.getSid());
		boolean flag = studnetService.save(student);
		if(flag){
			return "success";
		}
		return "error";
		
	}
}
