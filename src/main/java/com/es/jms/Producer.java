package com.es.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.*;

@Singleton
public class Producer {
    @Resource(mappedName = "jms/topic_dest")
    private Topic topic;
    @Resource(mappedName = "jms/topic_connection_factory")
    private ConnectionFactory connectionFactory;

    public void sendJmsMessage(String message) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(topic);
            producer.send(createTextMessage(session, message));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private TextMessage createTextMessage(Session session, String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        return textMessage;
    }
}
