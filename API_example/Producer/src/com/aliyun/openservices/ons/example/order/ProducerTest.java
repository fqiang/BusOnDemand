package com.aliyun.openservices.ons.example.order;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

import java.util.Date;
import java.util.Properties;
public class ProducerTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //���ڿ���̨������ Producer ID
        properties.put(PropertyKeyConst.ProducerId, "PID-szbus-demand");
        // AccessKey �����������֤���ڰ����Ʒ������������̨����
        properties.put(PropertyKeyConst.AccessKey,"LTAIKqbB4udmJox5");
        // SecretKey �����������֤���ڰ����Ʒ������������̨����
        properties.put(PropertyKeyConst.SecretKey, "5eWvd583iBW8Ki7zIxrFCcGKK2JKhh");
        //���÷��ͳ�ʱʱ�䣬��λ����
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        // ���� TCP �����������˴��Թ�������������Ϊ����
        properties.put(PropertyKeyConst.ONSAddr,
        		"http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        Producer producer = ONSFactory.createProducer(properties);
        // �ڷ�����Ϣǰ��������� start ���������� Producer��ֻ�����һ�μ���
        producer.start();
        //ѭ��������Ϣ
        for (int i = 0; i < 100; i++){
            Message msg = new Message( //
                // Message ������ Topic
                "szbus-demand",
                // Message Tag �����Ϊ Gmail �еı�ǩ������Ϣ�����ٹ��࣬���� Consumer ָ������������ MQ ����������
                "TagA",
                // Message Body �������κζ�������ʽ�����ݣ� MQ �����κθ�Ԥ��
                // ��Ҫ Producer �� Consumer Э�̺�һ�µ����л��ͷ����л���ʽ
                "Hello MQ".getBytes());
            // ���ô�����Ϣ��ҵ��ؼ����ԣ��뾡����ȫ��Ψһ��
            // �Է��������޷������յ���Ϣ����£���ͨ�������Ʒ������������̨��ѯ��Ϣ������
            // ע�⣺������Ҳ����Ӱ����Ϣ�����շ�
            msg.setKey("ORDERID_" + i);
            try {
                SendResult sendResult = producer.send(msg);
                // ͬ��������Ϣ��ֻҪ�����쳣���ǳɹ�
                if (sendResult != null) {
                    System.out.println(new Date() + " Send mq message success. Topic is:" + msg.getTopic() + " msgId is: " + sendResult.getMessageId());
                }
            }
            catch (Exception e) {
                // ��Ϣ����ʧ�ܣ���Ҫ�������Դ��������·���������Ϣ��־û��������ݽ��в�������
                System.out.println(new Date() + " Send mq message failed. Topic is:" + msg.getTopic());
                e.printStackTrace();
            }
        }
        // ��Ӧ���˳�ǰ������ Producer ����
        // ע�⣺���������Ҳû������
        producer.shutdown();
    }
}