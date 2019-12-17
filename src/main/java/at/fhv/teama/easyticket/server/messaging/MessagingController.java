package at.fhv.teama.easyticket.server.messaging;

import at.fhv.teama.easyticket.dto.MessageDto;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

import javax.jms.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class manages all messaging related tasks in the application.
 * There are various topics to which messages can be published.
 * Messages are shown until they are explicitly acknowledged.
 */
public class MessagingController {
    /**
     * Starts the embedded ActiveMQ Broker. The broker needs
     * to be running in order to publish and receive any message.
     */
    public static void start_broker() throws URISyntaxException {
        BrokerService broker = new BrokerService();
        TransportConnector connector = new TransportConnector();
        connector.setUri(new URI("tcp://localhost:61616"));
        try {
            broker.addConnector(connector);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Publishes a message to a specific topic.
     *
     * @param topicName   Name of the topic
     * @param messageText The message to be published
     */
    public static synchronized void publishMessageToTopic(String topicName, String messageText) throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(messageText);
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * Gets all messages for a user from a specified topic
     *
     * @param userName  Name of the user
     * @return All unacknowledged messages for a specific topic and user
     */
    public static synchronized Set<MessageDto> getMessages(String userName) throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID(userName);
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Topic topic = session.createTopic("topic");
        MessageConsumer consumer = session.createDurableSubscriber(topic, userName);
        Set<MessageDto> messageDtos = new HashSet<>();

        // Get the messages
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    long timestamp = message.getJMSTimestamp();
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;

                        MessageDto messageDto = new MessageDto();
                        messageDto.setTimestamp(timestamp);
                        messageDto.setContent(textMessage.getText());
                        messageDto.setTopic("topic");
                        messageDtos.add(messageDto);
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };
        consumer.setMessageListener(listener);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.close();
        session.close();
        connection.close();
        return messageDtos;
    }

    /**
     * Acknowledges a message so the message gets removed from the topic for
     * the specified user. The message is identified by the message content itself,
     * since messages are unique.
     *
     * @param userName    Name of the user that acknowledges the message
     * @param messageText The message content, used to identify the message to be acknowledged
     */
    public static synchronized void acknowledgeMessage(String userName, String messageText) throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID(userName);
        connection.start();

        Session session = connection.createSession(false,
                Session.CLIENT_ACKNOWLEDGE);

        Topic topic = session.createTopic("topic");
        MessageConsumer consumer = session.createDurableSubscriber(topic, userName);
        ArrayList<String> messages = new ArrayList<>();

        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        if (textMessage.getText().equals(messageText)) {
                            message.acknowledge();
                        }

                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };

        consumer.setMessageListener(listener);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.close();
        session.close();
        connection.close();

    }

    /**
     * Publishes all messages read from a RSS feed.
     *
     * @param url       URL to the feed
     * @param topicName Name of the topic to which the feed is published to
     */
    public static void publishFeed(String url, String topicName) {
        ArrayList<RssMessage> rssMessages = XMLParser.parseXML(url);
        for (RssMessage rssMessage : rssMessages) {
            try {
                MessagingController.publishMessageToTopic(topicName, rssMessage.getDescription());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
