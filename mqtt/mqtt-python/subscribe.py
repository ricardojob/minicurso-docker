# -*- coding: cp1252 -*-
import paho.mqtt.client as mqtt
from struct import unpack
from time import sleep

# assinando todas as publicações dentro da area 10
#TOPIC = "area/10/sensor/#"
TOPIC = "sensor/temperatura/#"

# função chamada quando a conexão for realizada, sendo
# então realizada a subscrição
def on_connect(client, data, rc):
    print data
    print rc
    client.subscribe([(TOPIC,0)])

# função chamada quando uma nova mensagem do tópico é gerada
def on_message(client, userdata, msg):
    print msg.payload
    # decodificando o valor recebido
    #v = unpack(">H",msg.payload)[0]
    #print msg.topic + "/" + str(v)

# cria um cliente para supervisão
client = mqtt.Client(client_id = 'SCADA',protocol = mqtt.MQTTv31)
# estabelece as funçõe de conexão e mensagens
client.on_connect = on_connect
client.on_message = on_message

# conecta no broker
#client.connect("192.168.99.100", 1883)
#client.connect("test.mosquitto.org", 8080)
client.connect("iot.eclipse.org", 1883)

# permace em loop, recebendo mensagens
client.loop_forever()
