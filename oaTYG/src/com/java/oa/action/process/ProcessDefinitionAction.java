package com.java.oa.action.process;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.java.oa.service.ProcessDefinitionService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction{

	private File upload; // 上传的文件
	private InputStream inputStream; // 下载用的输入流
	private String id;
	private String key;
    
	@Resource(name="processDefinitionService")
	private ProcessDefinitionService processDefinitionService;

	
	/** 列表，显示的是所有最新版本的流程定义 */
	public String list() throws Exception {
		List<ProcessDefinition> pdList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("pdList", pdList);
		return "list";
	}

	/** 删除，删除的是指定key的所有版本的流程定义 */
	public String delete() throws Exception {
		key = new String(key.getBytes("iso8859-1"), "utf-8"); // 解决GET方式传递的中文乱码的问题
		processDefinitionService.deleteByKey(key);
		return "toList";
	}

	/** 添加页面（部署页面） */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加（部署） */
	public String add() throws Exception {
		processDefinitionService.deployByZip(upload);
		return "toList";
	}

	/** 查看流程图（xxx.png） */
	public String showProcessImage() throws Exception {
		id = new String(id.getBytes("iso8859-1"), "utf-8"); // 解决GET方式传递的中文乱码的问题
		inputStream = processDefinitionService.getImageResourceAsStreamByPdId(id);
		return "showProcessImage";
	}
	
	 
	// ---

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
