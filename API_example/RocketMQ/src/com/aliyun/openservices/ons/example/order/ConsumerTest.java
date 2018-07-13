package com.aliyun.openservices.ons.example.order;
import py4j.GatewayServer;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

import java.util.Properties;
public class ConsumerTest {
    
 
	public static Message mes = new Message();
	
    public static void main(String[] args) {	
    	
        Properties properties = new Properties();
        // ���ڿ���̨������ Consumer ID
        properties.put(PropertyKeyConst.ConsumerId, "CID-szbus-demand");
        // AccessKey �����������֤���ڰ����Ʒ������������̨����
        properties.put(PropertyKeyConst.AccessKey, "LTAIKqbB4udmJox5");
        // SecretKey �����������֤���ڰ����Ʒ������������̨����
        properties.put(PropertyKeyConst.SecretKey, "5eWvd583iBW8Ki7zIxrFCcGKK2JKhh");
        // ���� TCP �����������˴��Թ�������������Ϊ����
        properties.put(PropertyKeyConst.ONSAddr,
        		"http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
          // ��Ⱥ���ķ�ʽ (Ĭ��)
          // properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
          // �㲥���ķ�ʽ
          // properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("szbus-demand", "TagA||TagB", new MessageListener() { //���Ķ�� Tag
            public Action consume(Message message, ConsumeContext context) {
                System.out.println("Receive: " + message);
//                String topic = message.getTopic();
                
                mes = message;
                return Action.CommitMessage;
            }
        });
        
        //��������һ�� Topic
        consumer.subscribe("Topic_szbus_demand", "*", new MessageListener() { //����ȫ�� Tag
            public Action consume(Message message, ConsumeContext context) {
                System.out.println("Receive: " + message);
                return Action.CommitMessage;
                
            }
        });
        consumer.start();
        System.out.println("Consumer Started"); 
        
        
        ConsumerTest con= new ConsumerTest();
        GatewayServer server = new GatewayServer(con);
        server.start();
    }
    
    public static Message getMessage() {
    	
    	return mes;
      }
    

}