package com.es.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.*;
import java.util.concurrent.TimeUnit;

@Singleton
public class Consumer {
    @Resource(mappedName = "jms/topic_dest")
    private Topic topic;
    @Resource(mappedName = "jms/topic_connection_factory")
    private ConnectionFactory connectionFactory;

    public static final long TIMEOUT = TimeUnit.SECONDS.toMillis(15);

    public String receiveMessage() {
        String message = null;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(topic);
            connection.start();
            TextMessage receivedMsg = (TextMessage) consumer.receive(TIMEOUT);
            if (receivedMsg != null) {
                message = receivedMsg.getText();
            }
            connection.stop();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return message;
    }

}
