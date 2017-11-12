package com.choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.entity.Student;
import com.choice.mapper.JedisClient;
import com.choice.mapper.StudentMapper;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired JedisClient jedisClient;
	
	public List<Student> selectAll(){
		List<Student> listStudent = studentMapper.getAllStu();
		System.out.println(listStudent);
		jedisClient.set("list", "success");
		System.out.println(jedisClient.get("list"));
		return listStudent;
		
	}
}
