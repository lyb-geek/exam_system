package com.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.system.po.Student;
import com.system.po.StudentExample;

public interface StudentMapper {
	int countByExample(StudentExample example);

	int deleteByExample(StudentExample example);

	int deleteByPrimaryKey(Integer userid);

	int insert(Student record);

	int insertSelective(Student record);

	List<Student> selectByExample(StudentExample example);

	Student selectByPrimaryKey(Integer userid);

	int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

	int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	List<Student> selectAllByParams(Map<String, Object> params);
}