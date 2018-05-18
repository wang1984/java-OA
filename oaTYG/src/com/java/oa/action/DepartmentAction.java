package com.java.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.java.oa.domain.Department;
import com.java.oa.service.DepartmentService;
import com.java.oa.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department>  {
    
	private Long parentId;
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;

	private Department model = new Department();

	public Department getModel() {
		return model;
	}

	/**
	 * 列表： 列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表
	 * 当点击本部门的时候根据id查出本部门的所有子级部门进行显示
	 */ 
	public String list() throws Exception {
		
		List<Department> departmentList = null;
		if (parentId == null) {//判断是否是顶级部门
			//默认显示顶级部门列表
			departmentList = departmentService.findTopList();
		} else {
			//显示指定部门的子部门列表
			departmentList = departmentService.findChildren(parentId);
			
			//添加的时候将父级部门传入到页面
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		 
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	 
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/**  添加页面 */ 
	public String addUI() throws Exception {
		// 准备数据(非树状结构)
		/*
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "addUI";
		*/
		/*
		  @param topList           从顶层开始遍历重组数据 
	      @param department 用于过滤本部门及其子部门     这个部门和这个部门的子孙部门都不要，如果为null，表示没有要移除的部门分支
	    */
		// 准备数据（树状结构）
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);
		return "addUI";
	}

	 
	public String add() throws Exception {
		// 封装对象
		// Department department = new Department();
		// department.setName(name);
		// department.setDescription(description);

		// 保存到数据库
		// 处理上级部门
		 
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);

		return "toList";
	}

	 
	public String editUI() throws Exception {
	 
		// 准备回显的数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		
		if (department.getParent() != null) {
			this.parentId = department.getParent().getId();
		}
		 
		// 准备数据上级部门（非树状结构）
		/*
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "editUI";
		*/
		/*
		    @param topList           从顶层开始遍历重组数据 
	        @param department 用于过滤本部门及其子部门     这个部门和这个部门的子孙部门都不要，如果为null，表示没有要移除的部门分支
		*/
		//  准备数据上级部门（树状结构）
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, department);
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "editUI";
	}

	 
	public String edit() throws Exception {
		// 1，从数据库中取出要修改的原始数据
		Department department = departmentService.getById(model.getId());

		// 2，设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		// >> 处理上级部门
		Department parent = departmentService.getById(parentId);
		department.setParent(parent);
		
		// 3，更新到数据库
		departmentService.update(department);

		return "toList";
	}

}
