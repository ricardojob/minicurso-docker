# -*- coding: cp1252 -*-
import paho.mqtt.client as mqtt
from struct import pack
from random import randint
from time import sleep

AREA_ID = 10
SENSOR_ID = 5000

# topicos providos por este sensor
#tt = "area/%d/sensor/%s/temperatura" % (AREA_ID,SENSOR_ID)
tt = "sensor/temperatura"
#ut = "area/%d/sensor/%s/umidade" % (AREA_ID,SENSOR_ID)

# cria um identificador baseado no id do sensor
client = mqtt.Client(client_id = 'NODE:%d-%d' % (AREA_ID,SENSOR_ID),protocol = mqtt.MQTTv31)
# conecta no broker
#client.connect("192.168.99.100", 1883)
#client.connect("iot.eclipse.org", 1883)
client.connect("iot.eclipse.org", 1883)
# ws://test.mosquitto.org:8080/mqtt

# var wsbroker = "iot.eclipse.org";  //mqtt websocket enabled broker
#       var wsport = 80 // port for above
#       var client = new Paho.MQTT.Client(wsbroker, wsport, '/ws',
#           "myclientid_" + parseInt(Math.random() * 100, 10));
while True:
    # gera um valor de temperartura aleatório
    t = randint(0,50)
    # codificando o payload como big endian, 2 bytes
    #payload = pack(">H",t)
    # envia a publicação
    client.publish(tt,t,qos=0)
    #client.publish(tt,payload,qos=0)
    print tt + "/" + str(t)

    # gera um valor de umidade aleatório
#    u = randint(0,100)
    # codificando o payload como big endian, 2 bytes
#    payload = pack(">H",u)
    # envia a publicação
    #client.publish(ut,u,qos=0)
#    client.publish(ut,payload,qos=0)
#    print ut + "/" + str(u)

    sleep(5)
