package com.dou361.jjdxm_ijkplayer.mqtt;
//2020-12-02

import android.util.Log;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencent.iot.hub.device.java.core.gateway.TXGatewayConnection;
import com.tencent.iot.hub.device.java.core.mqtt.TXMqttActionCallBack;
import com.tencent.iot.hub.device.java.core.mqtt.TXMqttConnection;
import com.tencent.iot.hub.device.java.core.mqtt.TXMqttConstants;
import com.tencent.iot.hub.device.java.core.util.AsymcSslUtils;
import com.tencent.iot.hub.device.java.main.mqtt.MQTTSample;

public class MQTTLocalSample {

    private static final String TAG = "TXMQTT";
    private static final Logger LOG = LoggerFactory.getLogger(MQTTSample.class);

    // Default Value, should be changed in testing
    private String mBrokerURL = "ssl://fawtsp-mqtt-public-dev.faw.cn:8883";
    private String mProductID = "PRODUCT-ID";
    private String mDevName = "DEVICE-NAME";
    private String mDevPSK = "DEVICE-SECRET";
    private String mSubProductID = "SUBDEV_PRODUCT-ID";
    private String mSubDevName = "SUBDEV_DEV-NAME";
    private String mTestTopic = "XLQ2S443OT/firefly";

    private TXMqttActionCallBack mMqttActionCallBack;
    private MqttClientPersistence clientPersistence;

    /**
     * MQTT连接实例
     */
    private TXMqttConnection mMqttConnection;

    /**
     * 请求ID
     */
    private static AtomicInteger requestID = new AtomicInteger(0);

    public MQTTLocalSample(TXMqttActionCallBack callBack) {
        mMqttActionCallBack = callBack;
        mMqttConnection = new TXMqttConnection(mProductID, mDevName, callBack);
    }

    public MQTTLocalSample( TXMqttActionCallBack callBack, String brokerURL, String productId,
                       String devName, String devPSK, String subProductID, String subDevName, String testTopic) {
        mBrokerURL = brokerURL;
        mProductID = productId;
        mDevName = devName;
        mDevPSK = devPSK;
        mSubProductID = subProductID;
        mSubDevName = subDevName;
        mTestTopic = testTopic;

        mMqttActionCallBack = callBack;

        //记得修改这个参数
        mMqttConnection = new TXMqttConnection(mBrokerURL,mProductID,mDevName,mDevPSK,new DisconnectedBufferOptions(),clientPersistence,callBack);
    }

    /**
     * 获取主题
     *
     * @param topicName
     * @return
     */
    private String getTopic(String topicName) {
        return mTestTopic;
    }

    /**
     * 建立MQTT连接
     */
    public void connect() {
        clientPersistence=new MqttClientPersistence() {
            @Override
            public void open(String clientId, String serverURI) throws MqttPersistenceException {

            }

            @Override
            public void close() throws MqttPersistenceException {

            }

            @Override
            public void put(String key, MqttPersistable persistable) throws MqttPersistenceException {

            }

            @Override
            public MqttPersistable get(String key) throws MqttPersistenceException {
                return null;
            }

            @Override
            public void remove(String key) throws MqttPersistenceException {

            }

            @Override
            public Enumeration keys() throws MqttPersistenceException {
                return null;
            }

            @Override
            public void clear() throws MqttPersistenceException {

            }

            @Override
            public boolean containsKey(String key) throws MqttPersistenceException {
                return false;
            }
        };
        mMqttConnection = new TXGatewayConnection(mBrokerURL,mProductID,mDevName,mDevPSK,mMqttActionCallBack);
        MqttConnectOptions options = new MqttConnectOptions();
        Log.d(TAG, "connect: 正在连接");
        options.setConnectionTimeout(8);
        options.setKeepAliveInterval(240);
        options.setAutomaticReconnect(true);

        //       options.setSocketFactory(AsymcSslUtils.getSocketFactory());


        MQTTRequest mqttRequest = new MQTTRequest("connect", requestID.getAndIncrement());
        mMqttConnection.connect(options, mqttRequest);

        DisconnectedBufferOptions bufferOptions = new DisconnectedBufferOptions();
        bufferOptions.setBufferEnabled(true);
        bufferOptions.setBufferSize(1024);
        bufferOptions.setDeleteOldestMessages(true);
        mMqttConnection.setBufferOpts(bufferOptions);
    }

    /**
     * 断开MQTT连接
     */
    public void disconnect() {
        MQTTRequest mqttRequest = new MQTTRequest("disconnect", requestID.getAndIncrement());
        mMqttConnection.disConnect(mqttRequest);
    }

    public void setSubdevOnline() {
        // set subdev online

    }

    public void setSubDevOffline() {

    }

    /**
     * 订阅主题
     *
     * @param topicName 主题名
     */
    public void subscribeTopic(String topicName) {
        // 主题
        String topic = getTopic(topicName);
        // QOS等级
        int qos = TXMqttConstants.QOS1;
        // 用户上下文（请求实例）
        MQTTRequest mqttRequest = new MQTTRequest("subscribeTopic", requestID.getAndIncrement());

        LOG.debug(TAG, "sub topic is " + topic);

        // 订阅主题
        mMqttConnection.subscribe(topic, qos, mqttRequest);

    }

    /**
     * 取消订阅主题
     *
     * @param topicName 主题名
     */
    public void unSubscribeTopic(String topicName) {
        // 主题
        String topic = getTopic(topicName);
        // 用户上下文（请求实例）
        MQTTRequest mqttRequest = new MQTTRequest("unSubscribeTopic", requestID.getAndIncrement());
        LOG.debug(TAG, "Start to unSubscribe" + topic);
        // 取消订阅主题
        mMqttConnection.unSubscribe(topic, mqttRequest);
    }

    /**
     * 发布主题
     */
    public void publishTopic(String topicName,  JSONObject jsonObject) {

        // 主题
        String topic = getTopic(topicName);
        System.out.print("aaaaaaaaaaaaa " +topic);
        // MQTT消息
        MqttMessage message = new MqttMessage();

        message.setQos(TXMqttConstants.QOS1);
        message.setPayload(jsonObject.toString().getBytes());

        // 用户上下文（请求实例）
        MQTTRequest mqttRequest = new MQTTRequest("publishTopic", requestID.getAndIncrement());

        LOG.debug(TAG, "pub topic " + topic + message);
        // 发布主题
        mMqttConnection.publish(topic, message, mqttRequest);

    }
}
