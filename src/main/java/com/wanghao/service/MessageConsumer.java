package com.wanghao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by wanghao on 2016/12/31.
 */
public class MessageConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    @Override
    public void onMessage(Message message) {
        logger.info("get Message"+message);
    }
}
