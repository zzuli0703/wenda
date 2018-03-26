package com.nowcoder.service;

import com.nowcoder.dao.MessageDao;
import com.nowcoder.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    @Autowired
    SensitiveService sensitiveService;

    public int addMessage(Message message){
        message.setContent(sensitiveService.filter(message.getContent()));
        return messageDao.addMessage(message) > 0 ? message.getId() : 0;
    }

    public List<Message> getConversationDetail(String conversationId, int offset, int limit){
        return messageDao.getConversationDetail(conversationId,offset,limit);
    }

    public List<Message> getConversationList(int userId, int offset, int limit){
        return messageDao.getConversationList(userId,offset,limit);
    }

    public int getConversationUnreadCount(int userId, String conversationId){
        return messageDao.getConversationUnreadCount(userId,conversationId);
    }
}
