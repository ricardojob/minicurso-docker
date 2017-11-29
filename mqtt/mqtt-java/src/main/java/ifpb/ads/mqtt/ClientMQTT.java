package ifpb.ads.mqtt;
 
import java.util.Arrays;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/10/2017, 14:15:44
 */
public class ClientMQTT {

    public static void main(String[] args) {
        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        String topic = "sensor/temperatura/#";
        int qos = 2;        
//        String broker = "ws://test.mosquitto.org:8080";
//        String broker = "ws://localhost:9001";
        String broker = "ws://iot.eclipse.org:80/ws";
        String clientId = "job";

        try {
            MqttClient client = new MqttClient(broker, clientId, dataStore);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Conectando ao broker: " + broker);
            client.setCallback(new ClienteCall());
            client.connect(connOpts);
            client.subscribe(topic, qos);
            System.out.println("Conectado");
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

    static class ClienteCall implements MqttCallback {

        @Override
        public void connectionLost(Throwable thrwbl) {
            System.out.println("ex = " + thrwbl);
        }

        @Override
        public void messageArrived(String topic, MqttMessage mm) throws Exception {
            byte[] bytes = mm.getPayload();
            System.out.println("topic: "+topic);
            System.out.println("array transmitido: "+Arrays.toString(bytes));
            System.out.println("valor: "+new String(bytes));
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken imdt) {
            //TODO
        }
    }
}
