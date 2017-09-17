package com.system.task.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.system.es.enu.TableEnum;
import com.system.es.model.BulkDocument;
import com.system.es.service.DocumentService;
import com.system.es.util.DocumentFactory;

@Component
public class SyncDbToEsTask {

	@Autowired
	private DocumentFactory documentFactory;
	@Autowired
	private DocumentService documentService;

	@Scheduled(cron = "${sync.db.to.es.task.time}")
	public void run() {
		System.out.println(this.getClass().getSimpleName() + "-->同步数据库到ES开始执行。。。");
		List<BulkDocument> list = documentFactory.createBulkDocuments(TableEnum.student);
		// Document document = documentFactory.createDocument(TableEnum.STUDENT, 1111);
		// documentService.insertDocument(document);
		documentService.insertBatchDocument(list);
		System.out.println(this.getClass().getSimpleName() + "-->同步数据库到ES结束执行。。。");
	}

}
