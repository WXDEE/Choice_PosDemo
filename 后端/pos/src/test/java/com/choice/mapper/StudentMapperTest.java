package com.choice.mapper;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choice.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class StudentMapperTest {
	private static final Logger log = Logger.getLogger(StudentMapperTest.class);
	@Resource
	private StudentMapper studentMapper;
	@Test
	public void testGetAllStu() {
		
		List<Student> listStudent = studentMapper.getAllStu();
		System.out.println(listStudent);
		log.debug("list" + listStudent);
	}

}
