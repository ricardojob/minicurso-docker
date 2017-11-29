//http://mitsuruog.github.io/what-mqtt/
var mqtt    = require('mqtt');
var readline = require('readline');
var client  = mqtt.connect('ws://iot.eclipse.org:80/ws');
//var client  = mqtt.connect('ws://localhost:9001');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

client.on('connect', function () {
  client.subscribe('sensor/temperatura');
  console.log('conectado')
});

client.on('message', function (topic, message) {
  console.log("recebendo:",message.toString());
});

var iv = setInterval( function() {
  var randInt = Math.floor(Math.random()*100);
  client.publish('sensor/temperatura', ''+randInt);
  console.log('msg:',randInt);
}, 200 );