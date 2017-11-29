//http://mitsuruog.github.io/what-mqtt/
var mqtt    = require('mqtt');
var client  = mqtt.connect('ws://iot.eclipse.org:80/ws');
//var client  = mqtt.connect('ws://localhost:9001');
 
var enviar = true; 

client.on('connect', function () {
  client.subscribe('atuador/temperatura');
  console.log('conectado')
});

client.on('message', function (topic, message) {
  console.log("recebendo:",message.toString());
  enviar = message.toString() == "0";
 
});

var iv = setInterval( function() {
  if(enviar){
    var randInt = Math.floor(Math.random()*100);
    client.publish('sensor/temperatura', ''+randInt);
    console.log('msg:',randInt);
  }
}, 2000 );