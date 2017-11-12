package com.choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.entity.Student;
import com.choice.mapper.JedisClient;
import com.choice.mapper.StudentMapper;

public interface StudentService {
	public List<Student> selectAll();
}
