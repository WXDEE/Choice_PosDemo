package com.choice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choice.entity.Student;
import com.choice.mapper.StudentMapper;
import com.choice.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;

	public boolean save(Student student) {
		// TODO Auto-generated method stub
		try {
			studentMapper.save(student);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	
	}

}
