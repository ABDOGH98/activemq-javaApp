import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) {
       try {
           //Connect to ActiveMQ broker
           ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
           //Create connection between consumer and and broker
           Connection connection = connectionFactory.createConnection();
           connection.start();
           //Create session for sending data
           Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
           //Sub to the queue
           //Destination destination = session.createQueue("enset.queue");
           Destination destination = session.createTopic("enset.topic");
           //connect consumer with the queue
           MessageConsumer consumer = session.createConsumer(destination);
           //Receive message with specific property
           //##### MessageConsumer consumer = session.createConsumer(destination,"code='c1'"); ####
           //event handling message consumed
           consumer.setMessageListener(new MessageListener() {
               @Override
               public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.print("recieve message : ");
                            System.out.println(textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
               }
           });
       }catch (JMSException e){
           e.printStackTrace();
       }


    }
}
