package com.soa.jesteban.mqttcallback;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MainActivity extends AppCompatActivity implements MqttCallback {
    Button button1 = null;
    MqttConnectOptions connOpt;
    static final String M2MIO_USERNAME = "wymecqjv";
    static final String M2MIO_PASSWORD_MD5 = "PBnK_BZnwDZ3";
    static final String topic = "soa/expo3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.button1 = (Button) findViewById(R.id.button1);

        try {
            connOpt.setCleanSession(true);
            connOpt.setKeepAliveInterval(30);
            connOpt.setUserName(M2MIO_USERNAME);
            connOpt.setPassword(M2MIO_PASSWORD_MD5.toCharArray());
            MqttClient client = new MqttClient("tcp://m10.cloudmqtt.com:12182", "Userdroid", new MemoryPersistence());
            client.setCallback(this);
            client.connect(connOpt);
            client.subscribe(topic);

        } catch (MqttException e) {
            e.printStackTrace();
            Context context = getApplicationContext();
            String text = e.getMessage();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Context context = getApplicationContext();
        String text = new String(message.getPayload());
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
