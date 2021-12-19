import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    public static void main(String[] args) throws JMSException {
        // Create a ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // Create a Session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        // Create the destination (Topic or Queue)
        //Destination destination = session.createQueue("enset.queue");
        Destination destination = session.createTopic("enset.topic");
        //Destination destination = session.createQueue("DQueue");
        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // Create a messages
        String text = "Hello world!";
        TextMessage message = session.createTextMessage(text);
        //send message to consumer with specific property defined
        //#### message.setStringProperty("code","c1"); ######
        System.out.println("message send ...");
        // Tell the producer to send the message
        producer.send(message);
        // Clean up
        //session.close();
        //connection.close();
    }
}
