package com.blog.ln.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.ln.bean.MessageInfo;

@Mapper
public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);
    
    /**
          * 查询留言信息
     * @param record
     * @return
     */
    public List<MessageInfo> getMessageList(MessageInfo record);
    
    /**
          * 查询留言总数
     * @return
     */
    public Long messamount(MessageInfo record);
    
}