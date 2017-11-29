package ifpb.ads.mqtt;

import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/10/2017, 14:15:44
 */
public class PublishMQTT {

    public static void main(String[] args) {

        
        String topic = "sensor/temperatura";
        String content = "23";
        int qos = 2;
//        String broker = "ws://test.mosquitto.org:8080/mqtt";
//        String broker = "tcp://test.mosquitto.org:1883";
        String broker = "ws://localhost:9001";
        String clientId = "job1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Conectando ao broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Conectado");
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            System.out.println("Publicando a mensagem: " + content);
            sampleClient.publish(topic, message);
//            new Scanner(System.in).nextLine();
            System.out.println("Mensagem publicada");
            sampleClient.disconnect();
            System.out.println("Disconectado");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("motivo " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("causa " + me.getCause());
            System.out.println("ex " + me);
            me.printStackTrace();
        }
    }
}
