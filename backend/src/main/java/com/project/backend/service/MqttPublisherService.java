package com.project.backend.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {
    @Value("${mqtt.broker}")
    private String brokerUrl;

    @Value("${mqtt.client-id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    private MqttClient mqttClient;

    public void connectAndPublish(String message){
        try{
            if(mqttClient == null || !mqttClient.isConnected()){
                mqttClient = new MqttClient(brokerUrl, clientId, null);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setAutomaticReconnect(true);
                options.setCleanSession(true);
                mqttClient.connect(options);

            }

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            mqttClient.publish(topic, mqttMessage);
            System.out.println("✅ Message envoyé au robot : " + message);

        } catch (MqttException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur MQTT : " + e.getMessage());
        }
    }

}
