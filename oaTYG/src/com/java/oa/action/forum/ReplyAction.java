package com.java.oa.action.forum;
 
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.java.oa.action.ModelDrivenBaseAction;
import com.java.oa.domain.Reply;
import com.java.oa.domain.Topic;
import com.java.oa.service.ReplyService;
import com.java.oa.service.TopicService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {

	private Long topicId;
	
	@Resource(name="topicService")
	private TopicService topicService;
	
	@Resource(name="replyService")
	private ReplyService replyService;
	
	/** 发表回复页面 */
	public String addUI() throws Exception {
		// 准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/** 发表回复 */
	public String add() throws Exception {
		// 封装对象
		Reply reply = new Reply();
		// a, 表单中的参数
		reply.setContent(model.getContent());
		reply.setTopic(topicService.getById(topicId));
		// b, 在显示层才能获得的数据
		reply.setAuthor(getCurrentUser()); // 当前登录的用户
		reply.setIpAddr(getRequestIP()); // 客户端的IP地址

		// 调用业务方法
		replyService.save(reply);

		return "toTopicShow"; // 转到当前这个新回复所属的主题显示页面
	}

	// ---

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
