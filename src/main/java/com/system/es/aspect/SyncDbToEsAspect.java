package com.system.es.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.es.annotation.SyncDbToEs;
import com.system.es.common.EsCommon;
import com.system.es.enu.TableEnum;
import com.system.es.model.Document;
import com.system.es.service.DocumentService;
import com.system.es.util.DocumentFactory;

@Component
@Aspect
public class SyncDbToEsAspect {
	@Autowired
	private DocumentService documentService;

	@Autowired
	private DocumentFactory documentFactory;

	@After(value = "@annotation(syncDbToEs)")
	public void after(JoinPoint joinPoint, SyncDbToEs syncDbToEs) {
		System.out.println(syncDbToEs.index() + "/" + syncDbToEs.type() + "/" + syncDbToEs.operationType());
		String targetClz = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("targetClz:" + targetClz + ";methodName:" + methodName + ";args:" + args);

		if (EsCommon.OPERATE_TYPE_ADD.equals(syncDbToEs.operationType())) {
			// List<BulkDocument> list = documentFactory
			// .createBulkDocuments(TableEnum.getTableEnumByName(syncDbToEs.type()));
			// documentService.insertBatchDocument(list);
			Document document = documentFactory.createDocument(TableEnum.getTableEnumByName(syncDbToEs.type()), 10007);
			documentService.insertDocument(document);

		} else if (EsCommon.OPERATE_TYPE_DELETE.equals(syncDbToEs.operationType())) {
			// List<BulkDocument> bulkDocuments = new ArrayList<>();
			// BulkDocument bulkDocument = new BulkDocument();
			// Delete delete = new Delete();
			// delete.setId(args[0].toString());
			// delete.setIndex(syncDbToEs.index());
			// delete.setType(syncDbToEs.type());
			// bulkDocument.setDelete(delete);
			// bulkDocuments.add(bulkDocument);
			//
			// documentService.delBatchDocument(bulkDocuments);

			Document document = new Document();
			document.setIndex(syncDbToEs.index());
			document.setId(args[0].toString());
			document.setType(syncDbToEs.type());

			documentService.delDocument(document);
		}

	}

	@Around(value = "@annotation(syncDbToEs)")
	public Object around(ProceedingJoinPoint join, SyncDbToEs syncDbToEs) throws Throwable {

		Object[] args = join.getArgs();
		Object ret = join.proceed();
		return ret;
	}

}
