package com.java.oa.action.forum;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.java.oa.action.ModelDrivenBaseAction;
import com.java.oa.domain.Forum;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;
import com.java.oa.service.ForumService;
import com.java.oa.service.ReplyService;
import com.java.oa.service.TopicService;
import com.java.oa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {
	
	@Resource(name="topicService")
	private TopicService topicService;
	
	@Resource(name="replyService")
	private ReplyService replyService;
	
	@Resource(name="forumService")
	private ForumService forumService;
	
	private Long forumId;//板块的id
 
	
	/** 发新帖页面 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}
	
	
	/** 发新帖 */
	public String add() throws Exception {
		// 封装对象
		Topic topic = new Topic();
		// >> a, 表单中的参数
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topic.setForum(forumService.getById(forumId));
		// >> b, 在显示层才能获得的数据
		topic.setAuthor(getCurrentUser()); // 当前登录的用户
		topic.setIpAddr(getRequestIP()); // 客户端的IP地址

		// 调用业务方法(其他的关于业务的操作都放在业务层service中)
		topicService.save(topic);

		ActionContext.getContext().put("topicId", topic.getId());
		return "toShow"; // 转到当前这个新主题的显示页面
	}

	
	/** 显示单个主题 */
	public String show() throws Exception {
		// 准备数据: topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备数据: replyList
//		List<Reply> replyList = replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
		 // 准备分页的数据 v1
		//PageBean pageBean = replyService.getPageBeanByTopic(pageNum, topic);
		//ActionContext.getContext().getValueStack().push(pageBean);
   
		// 准备分页的数据 v2
//		String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
//		Object[] args = { topic };
//		
//		PageBean pageBean = replyService.getPageBean(pageNum, hql, args);
//		ActionContext.getContext().getValueStack().push(pageBean);
//         
		// 准备分页的数据 （最终版）
		new QueryHelper(Reply.class, "r")//
				.addWhereCondition("r.topic=?", topic)//
				.addOrderByProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum);
		
		return "show";
	}
 
	 
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
	
}
