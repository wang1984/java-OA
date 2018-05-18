package com.java.oa.action.forum;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.java.oa.action.ModelDrivenBaseAction;
import com.java.oa.domain.Forum;
import com.java.oa.domain.Topic;
import com.java.oa.service.ForumService;
import com.java.oa.service.TopicService;
import com.java.oa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum> {
    
	@Resource(name="forumService")
	private ForumService forumService;
	
	@Resource(name="topicService")
	private TopicService topicService;
	
	
	/**
	 * 0 表示全部主题
	 * 1 表示全部精华贴
	 */
	private int viewType;

	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
	 * 1 表示只按最后更新时间排序
	 * 2 表示只按主题发表时间排序
	 * 3 表示只按回复数量排序
	 */
	private int orderBy;

	/**
	 * true表示升序
	 * false表示降序
	 */
	private boolean asc; 

	//==================================
	
	/** 版块列表 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** 显示单个版块 主题列表有分页*/
	public String show() throws Exception {
		// 准备数据：Forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		// 准备数据：topicList 不考虑分页的情况下   topicService
//		List<Topic> topicList = topicService.findByForum(forum);
//		ActionContext.getContext().put("topicList", topicList);
		
		// 准备分页的数据 v1
		// PageBean pageBean = topicService.getPageBeanByForum(pageNum, forum);
		// ActionContext.getContext().getValueStack().push(pageBean); // 放到栈顶
		
		// 准备分页的数据 v2 使用公共的分页方法
//	    String hql = "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
//	    Object[] args = { forum };
//	    PageBean pageBean = topicService.getPageBean(pageNum, hql, args);
//	    ActionContext.getContext().getValueStack().push(pageBean); // 放到栈顶 
		 
	    
	    // 准备分页的数据 v3 带过滤条件与排序条件的
//		 List<Object> argsList = new ArrayList<Object>();
//		 String hql = "FROM Topic t WHERE t.forum=? ";
//		 argsList.add(forum);
//		
//		 if (viewType == 1) { // 1 表示只看精华帖
//		 hql += "AND t.type=? ";
//		 argsList.add(Topic.TYPE_BEST);
//		 }
//		
//		 if (orderBy == 1) {
//		 // 1 表示只按最后更新时间排序
//		 hql += " ORDER BY t.lastUpdateTime " + (asc ? "ASC" : "DESC");
//		 } else if (orderBy == 2) {
//		 // 表示只按主题发表时间排序
//		 hql += " ORDER BY t.postTime " + (asc ? "ASC" : "DESC");
//		 } else if (orderBy == 3) {
//		 // 表示只按回复数量排序
//		 hql += " ORDER BY t.replyCount " + (asc ? "ASC" : "DESC");
//		 } else {
//		 // 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
//		 hql += " ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
//		 }
//		
//		 Object[] args = argsList.toArray();
//		 PageBean pageBean = topicService.getPageBean(pageNum, hql, args);
//		 ActionContext.getContext().getValueStack().push(pageBean); // 放到栈顶
		
		 
		// 准备分页的数据 v4 （最终版）-- 使用QueryHelper
			new QueryHelper(Topic.class, "t")//
					.addWhereCondition("t.forum=?", forum)//
					.addWhereCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST) // 1 表示只看精华帖
					.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1 表示只按最后更新时间排序
					.addOrderByProperty((orderBy == 2), "t.postTime", asc) // 表示只按主题发表时间排序
					.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // 表示只按回复数量排序
					.addOrderByProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
					.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false) // 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
					.preparePageBean(topicService, pageNum);

		 
		 return "show";
	}
	 

	
	
	//==================================

	/**
	 * @return the viewType
	 */
	public int getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	/**
	 * @return the orderBy
	 */
	public int getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the asc
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * @param asc the asc to set
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	
}
