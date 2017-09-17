package com.system.es.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.system.es.enu.TableEnum;
import com.system.es.model.BulkDocument;
import com.system.es.model.Document;
import com.system.es.model.Index;
import com.system.po.Student;
import com.system.service.StudentService;

@Component
public class DocumentFactory {
	@Autowired
	private StudentService studentService;

	@Value("${es.index}")
	private String index;

	public Document createDocument(TableEnum tableEnum, Integer id) {
		Document document = null;
		switch (tableEnum) {
		case student:

			try {
				Student student = studentService.findById(id);
				if (student != null) {
					document = getDocument(student, "student", String.valueOf(id));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		default:
			break;
		}

		return document;

	}

	public List<BulkDocument> createBulkDocuments(TableEnum tableEnum) {
		List<BulkDocument> list = new ArrayList<>();
		switch (tableEnum) {
		case student:
			Map<String, Object> params = new HashMap<>();
			List<Student> students = studentService.findAllByParams(params);
			if (!CollectionUtils.isEmpty(students)) {
				for (Student student : students) {
					BulkDocument bulkDocument = getBulkDocument(student, "student",
							String.valueOf(student.getUserid()));
					list.add(bulkDocument);
				}
			}
			break;

		default:
			break;
		}

		return list;

	}

	public List<BulkDocument> createBulkDocuments(TableEnum tableEnum, Map<String, Object> params) {
		List<BulkDocument> list = new ArrayList<>();
		switch (tableEnum) {
		case student:
			List<Student> students = studentService.findAllByParams(params);
			if (!CollectionUtils.isEmpty(students)) {
				for (Student student : students) {
					BulkDocument bulkDocument = getBulkDocument(student, "student",
							String.valueOf(student.getUserid()));
					list.add(bulkDocument);
				}
			}
			break;

		default:
			break;
		}

		return list;

	}

	private Document getDocument(Object object, String type, String id) {
		Document document = new Document();
		document.setContent(object);
		document.setType(type);
		document.setId(id);
		document.setIndex(index);
		return document;
	}

	private BulkDocument getBulkDocument(Object object, String type, String id) {
		BulkDocument bulkDocument = new BulkDocument();
		bulkDocument.setContent(object);

		Index bulkDocumentIndex = new Index();
		bulkDocumentIndex.setIndex(index);
		bulkDocumentIndex.setType(type);
		bulkDocumentIndex.setId(id);

		bulkDocument.setIndex(bulkDocumentIndex);

		return bulkDocument;
	}

}
