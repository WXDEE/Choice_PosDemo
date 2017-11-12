package com.choice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.entity.Student;
import com.choice.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/selectall")
	@ResponseBody
	public List<Student> selectAll(){
		List<Student> flag = studentService.selectAll();		
		
		return flag;
	}
}
