package com.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.system.es.model.FilterParam;
import com.system.es.model.SearchParam;
import com.system.es.service.SearchService;
import com.system.po.Student;
import com.system.po.StudentCustom;
import com.system.service.StudentService;

/**
 * Created by Jacey on 2017/6/29.
 */
public class StudentServiceImplTest {

	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"classpath:/spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml",
				"classpath:spring/applicationContext-es.xml" });
	}

	@Test
	public void updataById() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");

		StudentCustom studentCustom = new StudentCustom();
		studentCustom.setUserid(10004);
		studentCustom.setUsername("小拉");
		// studentCustom.setBirthyear(new Date(1996, 9, 2));

		// 指定时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
		// 指定一个日期
		Date date = dateFormat.parse("1990-09-06");
		studentCustom.setBirthyear(date);

		studentCustom.setCollegeid(1);
		studentCustom.setSex("男");
		studentCustom.setGrade(new Date());
		studentService.updataById(10004, studentCustom);
	}

	@Test
	public void removeById() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");
		studentService.removeById(10007);
	}

	@Test
	public void findByPaging() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");
		List<StudentCustom> list = studentService.findByPaging(1);
		System.out.println();
	}

	@Test
	public void save() throws Exception {
		StudentCustom studentCustom = new StudentCustom();
		studentCustom.setUserid(10007);
		studentCustom.setUsername("中华屋");
		// studentCustom.setBirthyear(new Date(1996, 9, 2));

		// 指定时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
		// 指定一个日期
		Date date = dateFormat.parse("1996-09-02");
		studentCustom.setBirthyear(date);

		studentCustom.setCollegeid(1);
		studentCustom.setSex("男");
		studentCustom.setGrade(new Date());

		StudentService studentService = (StudentService) applicationContext.getBean("studentService");
		studentService.save(studentCustom);
	}

	@Test
	public void getCountStudent() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");
		int i = studentService.getCountStudent();
		System.out.println();
	}

	@Test
	public void findById() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");

		Student student = studentService.findById(10001);
		System.out.println();
	}

	@Test
	public void findByName() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");
		List<StudentCustom> list = studentService.findByName("小");
		System.out.println();
	}

	@Test
	public void testFindAll() throws Exception {
		StudentService studentService = (StudentService) applicationContext.getBean("studentService");
		Map<String, Object> params = new HashMap<>();
		List<Student> students = studentService.findAllByParams(params);

		for (Student student : students) {
			System.out.println(student);
		}
	}

	// select * from student where t.name = '小' and t.sex = '男'
	@Test
	public void testMustAndSearch() {
		SearchService searchService = (SearchService) applicationContext.getBean("searchService");
		SearchParam search = new SearchParam();
		search.setFlag(1);
		search.setPage(0);
		search.setSize(10);

		search.setIndex("exam_system");
		search.setType("student");

		List<FilterParam> filters = new ArrayList<FilterParam>();
		FilterParam filterParam = new FilterParam();
		filterParam.setKey("username");
		filterParam.setValue("小");
		filterParam.setNested(false);
		filterParam.setBoolType((byte) 1);
		filterParam.setType(1);

		FilterParam filterParam1 = new FilterParam();
		filterParam1.setKey("sex");
		filterParam1.setValue("男");
		filterParam1.setNested(false);
		filterParam1.setBoolType((byte) 1);
		filterParam1.setType(1);

		filters.add(filterParam);
		filters.add(filterParam1);
		search.setFilters(filters);

		searchService.search(search);
	}

	// select * from student where t.sex = '男' and t.userid > 10003
	@Test
	public void testMustRangeSearch() {
		SearchService searchService = (SearchService) applicationContext.getBean("searchService");
		SearchParam search = new SearchParam();
		search.setFlag(1);
		search.setPage(0);
		search.setSize(10);

		search.setIndex("exam_system");
		search.setType("student");

		List<FilterParam> filters = new ArrayList<FilterParam>();
		FilterParam filterParam = new FilterParam();
		filterParam.setKey("userid");
		filterParam.setRangeType(4);
		filterParam.setMinValue("10003");
		filterParam.setNested(false);
		filterParam.setBoolType((byte) 1);
		filterParam.setType(4);

		FilterParam filterParam1 = new FilterParam();
		filterParam1.setKey("sex");
		filterParam1.setValue("男");
		filterParam1.setNested(false);
		filterParam1.setBoolType((byte) 1);
		filterParam1.setType(1);

		filters.add(filterParam);
		filters.add(filterParam1);
		search.setFilters(filters);

		searchService.search(search);
	}

	// select * from student where t.username = 'test' or (t.userid > 10003 and t.sex = '女')
	@Test
	public void testMustWithShouldSearch() {
		SearchService searchService = (SearchService) applicationContext.getBean("searchService");
		SearchParam search = new SearchParam();
		search.setFlag(1);
		search.setPage(0);
		search.setSize(10);

		search.setIndex("exam_system");
		search.setType("student");

		List<FilterParam> filters = new ArrayList<FilterParam>();

		FilterParam filterParam = new FilterParam();
		filterParam.setKey("userid");
		filterParam.setRangeType(4);
		filterParam.setMinValue("10003");
		filterParam.setNested(false);
		filterParam.setBoolType((byte) 1);
		filterParam.setType(4);

		FilterParam filterParam1 = new FilterParam();
		filterParam1.setKey("sex");
		filterParam1.setValue("女");
		filterParam1.setNested(false);
		filterParam1.setBoolType((byte) 1);
		filterParam1.setType(1);

		FilterParam filterParam2 = new FilterParam();
		filterParam2.setKey("username");
		filterParam2.setValue("test");
		filterParam2.setNested(false);
		filterParam2.setBoolType((byte) 3);
		filterParam2.setType(1);
		filterParam2.setNestedBool(true);

		filters.add(filterParam);
		filters.add(filterParam1);
		filters.add(filterParam2);

		search.setFilters(filters);

		searchService.search(search);
	}

	//// select * from student where t.birthyear = '841593600000' and (t.collegeid = 1 or t.sex = '女')
	@Test
	public void testMustAndShouldSearch() {
		SearchService searchService = (SearchService) applicationContext.getBean("searchService");
		SearchParam search = new SearchParam();
		search.setFlag(1);
		search.setPage(0);
		search.setSize(10);

		search.setIndex("exam_system");
		search.setType("student");

		List<FilterParam> filters = new ArrayList<FilterParam>();

		FilterParam filterParam1 = new FilterParam();
		filterParam1.setKey("birthyear");
		filterParam1.setValue("841593600000");
		filterParam1.setNested(false);
		filterParam1.setBoolType((byte) 1);
		filterParam1.setType(1);

		FilterParam filterParam = new FilterParam();
		filterParam.setKey("collegeid");
		filterParam.setValue("1");
		filterParam.setNested(false);
		filterParam.setBoolType((byte) 3);
		filterParam.setType(2);

		FilterParam filterParam2 = new FilterParam();
		filterParam2.setKey("sex");
		filterParam2.setValue("女");
		filterParam2.setNested(false);
		filterParam2.setBoolType((byte) 3);
		filterParam2.setType(2);

		filters.add(filterParam);
		filters.add(filterParam1);
		filters.add(filterParam2);

		search.setFilters(filters);

		searchService.search(search);
	}

	@Test
	public void testSort() {
		SearchService searchService = (SearchService) applicationContext.getBean("searchService");
		SearchParam search = new SearchParam();
		search.setFlag(1);
		search.setPage(0);
		search.setSize(100);

		search.setIndex("exam_system");
		search.setType("student");

		List<FilterParam> filters = new ArrayList<FilterParam>();

		FilterParam filterParam1 = new FilterParam();
		filterParam1.setKey("grade");
		filterParam1.setValue("1441123200000");
		filterParam1.setNested(false);
		filterParam1.setBoolType((byte) 2);
		filterParam1.setType(1);

		Map<String, Map<String, String>> map = new HashMap<>();
		Map<String, String> order = new HashMap<>();
		order.put("order", "desc");
		map.put("collegeid", order);

		Map<String, String> order2 = new HashMap<>();
		order2.put("order", "asc");
		map.put("userid", order2);

		search.setOrderField(map);

		filters.add(filterParam1);

		search.setFilters(filters);

		searchService.search(search);
	}

	@Test
	public void testSearchIn() {
		SearchService searchService = (SearchService) applicationContext.getBean("searchService");
		SearchParam search = new SearchParam();
		search.setFlag(1);
		search.setPage(0);
		search.setSize(100);

		search.setIndex("exam_system");
		search.setType("student");

		List<FilterParam> filters = new ArrayList<FilterParam>();

		FilterParam filterParam1 = new FilterParam();
		filterParam1.setKey("userid");
		List<String> value = new ArrayList<>();
		value.add("10004");
		value.add("10005");
		filterParam1.setValue(value);
		filterParam1.setNested(false);
		filterParam1.setBoolType((byte) 2);
		filterParam1.setType(5);

		Map<String, Map<String, String>> map = new HashMap<>();

		Map<String, String> order2 = new HashMap<>();
		order2.put("order", "desc");
		map.put("userid", order2);

		search.setOrderField(map);

		filters.add(filterParam1);

		search.setFilters(filters);

		searchService.search(search);
	}

}