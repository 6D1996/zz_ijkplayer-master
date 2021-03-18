/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.tencent.iot.hub.device.android.service;
public interface ITXMqttService extends android.os.IInterface
{
  /** Default implementation for ITXMqttService. */
  public static class Default implements com.tencent.iot.hub.device.android.service.ITXMqttService
  {
    /**
         * 注册mqttAction监听器
         */
    @Override public void registerMqttActionListener(com.tencent.iot.hub.device.android.service.ITXMqttActionListener mqttActionListener) throws android.os.RemoteException
    {
    }
    /**
         * 注册shadowAction监听器
         */
    @Override public void registerShadowActionListener(com.tencent.iot.hub.device.android.service.ITXShadowActionListener shadowActionListener) throws android.os.RemoteException
    {
    }
    /**
         * 初始化设备信息
         * @param clientOptions  客户端选项
         */
    @Override public void initDeviceInfo(com.tencent.iot.hub.device.android.service.TXMqttClientOptions clientOptions) throws android.os.RemoteException
    {
    }
    /**
         * 设置断连状态buffer缓冲区
         */
    @Override public void setBufferOpts(com.tencent.iot.hub.device.android.service.TXDisconnectedBufferOptions bufferOptions) throws android.os.RemoteException
    {
    }
    /**
         * 连接MQTT
         * @param  options
         * @param  userContextId
         * @return status
         */
    @Override public java.lang.String connect(com.tencent.iot.hub.device.android.service.TXMqttConnectOptions options, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 重新连接
         */
    @Override public java.lang.String reconnect() throws android.os.RemoteException
    {
      return null;
    }
    /**
         * MQTT断连
         * @param timeout       等待时间（必须>0）。单位：毫秒
         * @param userContextId 用户上下文
         */
    @Override public java.lang.String disConnect(long timeout, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 订阅广播主题
         * @param qos
         * @param userContextId
         */
    @Override public java.lang.String subscribeBroadcastTopic(int qos, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 订阅主题
         * @param topic
         * @param qos
         * @param userContextId
         */
    @Override public java.lang.String subscribe(java.lang.String topic, int qos, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 取消订阅主题
         */
    @Override public java.lang.String unSubscribe(java.lang.String topic, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 发布主题
         * @param topic
         * @param message
         * @param userContextId
         */
    @Override public java.lang.String publish(java.lang.String topic, com.tencent.iot.hub.device.android.service.TXMqttMessage message, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 订阅RRPC主题
         * @param qos
         * @param userContextId
         */
    @Override public java.lang.String subscribeRRPCTopic(int qos, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 获取连接状态
         *
         * @return 连接状态
         */
    @Override public java.lang.String getConnectStatus() throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 获取设备影子文档
         */
    @Override public java.lang.String getShadow(long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 更新设备影子文档
         * @param devicePropertyList
         * @param userContextId
         */
    @Override public java.lang.String updateShadow(java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> devicePropertyList, long userContextId) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 注册设备属性
         * @param deviceProperty
         */
    @Override public void registerDeviceProperty(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty deviceProperty) throws android.os.RemoteException
    {
    }
    /**
         * 取消注册设备属性
         * @param deviceProperty
         */
    @Override public void unRegisterDeviceProperty(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty deviceProperty) throws android.os.RemoteException
    {
    }
    /**
         * 更新delta信息后，上报空的desired信息，通知服务器不再发送delta消息
         * @param reportJsonDoc 用户上报的JSON内容
         */
    @Override public java.lang.String reportNullDesiredInfo(java.lang.String reportJsonDoc) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 初始化OTA功能。
         *
         * @param storagePath OTA升级包存储路径(调用者必须确保路径已存在，并且具有写权限)
         * @param listener    OTA事件回调
         */
    @Override public void initOTA(java.lang.String storagePath, com.tencent.iot.hub.device.android.service.ITXOTAListener listener) throws android.os.RemoteException
    {
    }
    /**
         * 上报设备当前版本信息到后台服务器。
         *
         * @param currentFirmwareVersion 设备当前版本信息
         * @return 发送成功时返回字符串"OK"; 其它返回值表示发送失败；
         */
    @Override public java.lang.String reportCurrentFirmwareVersion(java.lang.String currentFirmwareVersion) throws android.os.RemoteException
    {
      return null;
    }
    /**
         * 上报设备升级状态到后台服务器。
         *
         * @param state 状态
         * @param resultCode 结果代码。0：表示成功；其它：表示失败；常见错误码：-1: 下载超时; -2:文件不存在；-3:签名过期；-4:校验错误；-5：更新固件失败
         * @param resultMsg 结果描述
         * @param version 版本号
         * @return 发送成功时返回字符串"OK"; 其它返回值表示发送失败；
         */
    @Override public java.lang.String reportOTAState(java.lang.String state, int resultCode, java.lang.String resultMsg, java.lang.String version) throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.tencent.iot.hub.device.android.service.ITXMqttService
  {
    private static final java.lang.String DESCRIPTOR = "com.tencent.iot.hub.device.android.service.ITXMqttService";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.tencent.iot.hub.device.android.service.ITXMqttService interface,
     * generating a proxy if needed.
     */
    public static com.tencent.iot.hub.device.android.service.ITXMqttService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.tencent.iot.hub.device.android.service.ITXMqttService))) {
        return ((com.tencent.iot.hub.device.android.service.ITXMqttService)iin);
      }
      return new com.tencent.iot.hub.device.android.service.ITXMqttService.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_registerMqttActionListener:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.service.ITXMqttActionListener _arg0;
          _arg0 = com.tencent.iot.hub.device.android.service.ITXMqttActionListener.Stub.asInterface(data.readStrongBinder());
          this.registerMqttActionListener(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_registerShadowActionListener:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.service.ITXShadowActionListener _arg0;
          _arg0 = com.tencent.iot.hub.device.android.service.ITXShadowActionListener.Stub.asInterface(data.readStrongBinder());
          this.registerShadowActionListener(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_initDeviceInfo:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.service.TXMqttClientOptions _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.tencent.iot.hub.device.android.service.TXMqttClientOptions.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.initDeviceInfo(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_setBufferOpts:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.service.TXDisconnectedBufferOptions _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.tencent.iot.hub.device.android.service.TXDisconnectedBufferOptions.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.setBufferOpts(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_connect:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.service.TXMqttConnectOptions _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.tencent.iot.hub.device.android.service.TXMqttConnectOptions.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _result = this.connect(_arg0, _arg1);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_reconnect:
        {
          data.enforceInterface(descriptor);
          java.lang.String _result = this.reconnect();
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_disConnect:
        {
          data.enforceInterface(descriptor);
          long _arg0;
          _arg0 = data.readLong();
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _result = this.disConnect(_arg0, _arg1);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_subscribeBroadcastTopic:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _result = this.subscribeBroadcastTopic(_arg0, _arg1);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_subscribe:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _arg1;
          _arg1 = data.readInt();
          long _arg2;
          _arg2 = data.readLong();
          java.lang.String _result = this.subscribe(_arg0, _arg1, _arg2);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_unSubscribe:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _result = this.unSubscribe(_arg0, _arg1);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_publish:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          com.tencent.iot.hub.device.android.service.TXMqttMessage _arg1;
          if ((0!=data.readInt())) {
            _arg1 = com.tencent.iot.hub.device.android.service.TXMqttMessage.CREATOR.createFromParcel(data);
          }
          else {
            _arg1 = null;
          }
          long _arg2;
          _arg2 = data.readLong();
          java.lang.String _result = this.publish(_arg0, _arg1, _arg2);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_subscribeRRPCTopic:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _result = this.subscribeRRPCTopic(_arg0, _arg1);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_getConnectStatus:
        {
          data.enforceInterface(descriptor);
          java.lang.String _result = this.getConnectStatus();
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_getShadow:
        {
          data.enforceInterface(descriptor);
          long _arg0;
          _arg0 = data.readLong();
          java.lang.String _result = this.getShadow(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_updateShadow:
        {
          data.enforceInterface(descriptor);
          java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> _arg0;
          _arg0 = data.createTypedArrayList(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty.CREATOR);
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _result = this.updateShadow(_arg0, _arg1);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_registerDeviceProperty:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.core.shadow.DeviceProperty _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.tencent.iot.hub.device.android.core.shadow.DeviceProperty.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.registerDeviceProperty(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_unRegisterDeviceProperty:
        {
          data.enforceInterface(descriptor);
          com.tencent.iot.hub.device.android.core.shadow.DeviceProperty _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.tencent.iot.hub.device.android.core.shadow.DeviceProperty.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.unRegisterDeviceProperty(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_reportNullDesiredInfo:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _result = this.reportNullDesiredInfo(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_initOTA:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          com.tencent.iot.hub.device.android.service.ITXOTAListener _arg1;
          _arg1 = com.tencent.iot.hub.device.android.service.ITXOTAListener.Stub.asInterface(data.readStrongBinder());
          this.initOTA(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_reportCurrentFirmwareVersion:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _result = this.reportCurrentFirmwareVersion(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_reportOTAState:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _arg1;
          _arg1 = data.readInt();
          java.lang.String _arg2;
          _arg2 = data.readString();
          java.lang.String _arg3;
          _arg3 = data.readString();
          java.lang.String _result = this.reportOTAState(_arg0, _arg1, _arg2, _arg3);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.tencent.iot.hub.device.android.service.ITXMqttService
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /**
           * 注册mqttAction监听器
           */
      @Override public void registerMqttActionListener(com.tencent.iot.hub.device.android.service.ITXMqttActionListener mqttActionListener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((mqttActionListener!=null))?(mqttActionListener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerMqttActionListener, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerMqttActionListener(mqttActionListener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 注册shadowAction监听器
           */
      @Override public void registerShadowActionListener(com.tencent.iot.hub.device.android.service.ITXShadowActionListener shadowActionListener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((shadowActionListener!=null))?(shadowActionListener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerShadowActionListener, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerShadowActionListener(shadowActionListener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 初始化设备信息
           * @param clientOptions  客户端选项
           */
      @Override public void initDeviceInfo(com.tencent.iot.hub.device.android.service.TXMqttClientOptions clientOptions) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((clientOptions!=null)) {
            _data.writeInt(1);
            clientOptions.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_initDeviceInfo, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().initDeviceInfo(clientOptions);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 设置断连状态buffer缓冲区
           */
      @Override public void setBufferOpts(com.tencent.iot.hub.device.android.service.TXDisconnectedBufferOptions bufferOptions) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((bufferOptions!=null)) {
            _data.writeInt(1);
            bufferOptions.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_setBufferOpts, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().setBufferOpts(bufferOptions);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 连接MQTT
           * @param  options
           * @param  userContextId
           * @return status
           */
      @Override public java.lang.String connect(com.tencent.iot.hub.device.android.service.TXMqttConnectOptions options, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((options!=null)) {
            _data.writeInt(1);
            options.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_connect, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().connect(options, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 重新连接
           */
      @Override public java.lang.String reconnect() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_reconnect, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().reconnect();
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * MQTT断连
           * @param timeout       等待时间（必须>0）。单位：毫秒
           * @param userContextId 用户上下文
           */
      @Override public java.lang.String disConnect(long timeout, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeLong(timeout);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_disConnect, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().disConnect(timeout, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 订阅广播主题
           * @param qos
           * @param userContextId
           */
      @Override public java.lang.String subscribeBroadcastTopic(int qos, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(qos);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_subscribeBroadcastTopic, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().subscribeBroadcastTopic(qos, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 订阅主题
           * @param topic
           * @param qos
           * @param userContextId
           */
      @Override public java.lang.String subscribe(java.lang.String topic, int qos, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(topic);
          _data.writeInt(qos);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_subscribe, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().subscribe(topic, qos, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 取消订阅主题
           */
      @Override public java.lang.String unSubscribe(java.lang.String topic, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(topic);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_unSubscribe, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().unSubscribe(topic, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 发布主题
           * @param topic
           * @param message
           * @param userContextId
           */
      @Override public java.lang.String publish(java.lang.String topic, com.tencent.iot.hub.device.android.service.TXMqttMessage message, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(topic);
          if ((message!=null)) {
            _data.writeInt(1);
            message.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_publish, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().publish(topic, message, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 订阅RRPC主题
           * @param qos
           * @param userContextId
           */
      @Override public java.lang.String subscribeRRPCTopic(int qos, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(qos);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_subscribeRRPCTopic, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().subscribeRRPCTopic(qos, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 获取连接状态
           *
           * @return 连接状态
           */
      @Override public java.lang.String getConnectStatus() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getConnectStatus, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getConnectStatus();
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 获取设备影子文档
           */
      @Override public java.lang.String getShadow(long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getShadow, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getShadow(userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 更新设备影子文档
           * @param devicePropertyList
           * @param userContextId
           */
      @Override public java.lang.String updateShadow(java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> devicePropertyList, long userContextId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeTypedList(devicePropertyList);
          _data.writeLong(userContextId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_updateShadow, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().updateShadow(devicePropertyList, userContextId);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 注册设备属性
           * @param deviceProperty
           */
      @Override public void registerDeviceProperty(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty deviceProperty) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((deviceProperty!=null)) {
            _data.writeInt(1);
            deviceProperty.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerDeviceProperty, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerDeviceProperty(deviceProperty);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 取消注册设备属性
           * @param deviceProperty
           */
      @Override public void unRegisterDeviceProperty(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty deviceProperty) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((deviceProperty!=null)) {
            _data.writeInt(1);
            deviceProperty.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_unRegisterDeviceProperty, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().unRegisterDeviceProperty(deviceProperty);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 更新delta信息后，上报空的desired信息，通知服务器不再发送delta消息
           * @param reportJsonDoc 用户上报的JSON内容
           */
      @Override public java.lang.String reportNullDesiredInfo(java.lang.String reportJsonDoc) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(reportJsonDoc);
          boolean _status = mRemote.transact(Stub.TRANSACTION_reportNullDesiredInfo, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().reportNullDesiredInfo(reportJsonDoc);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 初始化OTA功能。
           *
           * @param storagePath OTA升级包存储路径(调用者必须确保路径已存在，并且具有写权限)
           * @param listener    OTA事件回调
           */
      @Override public void initOTA(java.lang.String storagePath, com.tencent.iot.hub.device.android.service.ITXOTAListener listener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(storagePath);
          _data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_initOTA, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().initOTA(storagePath, listener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 上报设备当前版本信息到后台服务器。
           *
           * @param currentFirmwareVersion 设备当前版本信息
           * @return 发送成功时返回字符串"OK"; 其它返回值表示发送失败；
           */
      @Override public java.lang.String reportCurrentFirmwareVersion(java.lang.String currentFirmwareVersion) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(currentFirmwareVersion);
          boolean _status = mRemote.transact(Stub.TRANSACTION_reportCurrentFirmwareVersion, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().reportCurrentFirmwareVersion(currentFirmwareVersion);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
           * 上报设备升级状态到后台服务器。
           *
           * @param state 状态
           * @param resultCode 结果代码。0：表示成功；其它：表示失败；常见错误码：-1: 下载超时; -2:文件不存在；-3:签名过期；-4:校验错误；-5：更新固件失败
           * @param resultMsg 结果描述
           * @param version 版本号
           * @return 发送成功时返回字符串"OK"; 其它返回值表示发送失败；
           */
      @Override public java.lang.String reportOTAState(java.lang.String state, int resultCode, java.lang.String resultMsg, java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(state);
          _data.writeInt(resultCode);
          _data.writeString(resultMsg);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_reportOTAState, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().reportOTAState(state, resultCode, resultMsg, version);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static com.tencent.iot.hub.device.android.service.ITXMqttService sDefaultImpl;
    }
    static final int TRANSACTION_registerMqttActionListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_registerShadowActionListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_initDeviceInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_setBufferOpts = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_connect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_reconnect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    static final int TRANSACTION_disConnect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
    static final int TRANSACTION_subscribeBroadcastTopic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
    static final int TRANSACTION_subscribe = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
    static final int TRANSACTION_unSubscribe = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
    static final int TRANSACTION_publish = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
    static final int TRANSACTION_subscribeRRPCTopic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
    static final int TRANSACTION_getConnectStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
    static final int TRANSACTION_getShadow = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
    static final int TRANSACTION_updateShadow = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
    static final int TRANSACTION_registerDeviceProperty = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
    static final int TRANSACTION_unRegisterDeviceProperty = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
    static final int TRANSACTION_reportNullDesiredInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
    static final int TRANSACTION_initOTA = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
    static final int TRANSACTION_reportCurrentFirmwareVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
    static final int TRANSACTION_reportOTAState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
    public static boolean setDefaultImpl(com.tencent.iot.hub.device.android.service.ITXMqttService impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.tencent.iot.hub.device.android.service.ITXMqttService getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * 注册mqttAction监听器
       */
  public void registerMqttActionListener(com.tencent.iot.hub.device.android.service.ITXMqttActionListener mqttActionListener) throws android.os.RemoteException;
  /**
       * 注册shadowAction监听器
       */
  public void registerShadowActionListener(com.tencent.iot.hub.device.android.service.ITXShadowActionListener shadowActionListener) throws android.os.RemoteException;
  /**
       * 初始化设备信息
       * @param clientOptions  客户端选项
       */
  public void initDeviceInfo(com.tencent.iot.hub.device.android.service.TXMqttClientOptions clientOptions) throws android.os.RemoteException;
  /**
       * 设置断连状态buffer缓冲区
       */
  public void setBufferOpts(com.tencent.iot.hub.device.android.service.TXDisconnectedBufferOptions bufferOptions) throws android.os.RemoteException;
  /**
       * 连接MQTT
       * @param  options
       * @param  userContextId
       * @return status
       */
  public java.lang.String connect(com.tencent.iot.hub.device.android.service.TXMqttConnectOptions options, long userContextId) throws android.os.RemoteException;
  /**
       * 重新连接
       */
  public java.lang.String reconnect() throws android.os.RemoteException;
  /**
       * MQTT断连
       * @param timeout       等待时间（必须>0）。单位：毫秒
       * @param userContextId 用户上下文
       */
  public java.lang.String disConnect(long timeout, long userContextId) throws android.os.RemoteException;
  /**
       * 订阅广播主题
       * @param qos
       * @param userContextId
       */
  public java.lang.String subscribeBroadcastTopic(int qos, long userContextId) throws android.os.RemoteException;
  /**
       * 订阅主题
       * @param topic
       * @param qos
       * @param userContextId
       */
  public java.lang.String subscribe(java.lang.String topic, int qos, long userContextId) throws android.os.RemoteException;
  /**
       * 取消订阅主题
       */
  public java.lang.String unSubscribe(java.lang.String topic, long userContextId) throws android.os.RemoteException;
  /**
       * 发布主题
       * @param topic
       * @param message
       * @param userContextId
       */
  public java.lang.String publish(java.lang.String topic, com.tencent.iot.hub.device.android.service.TXMqttMessage message, long userContextId) throws android.os.RemoteException;
  /**
       * 订阅RRPC主题
       * @param qos
       * @param userContextId
       */
  public java.lang.String subscribeRRPCTopic(int qos, long userContextId) throws android.os.RemoteException;
  /**
       * 获取连接状态
       *
       * @return 连接状态
       */
  public java.lang.String getConnectStatus() throws android.os.RemoteException;
  /**
       * 获取设备影子文档
       */
  public java.lang.String getShadow(long userContextId) throws android.os.RemoteException;
  /**
       * 更新设备影子文档
       * @param devicePropertyList
       * @param userContextId
       */
  public java.lang.String updateShadow(java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> devicePropertyList, long userContextId) throws android.os.RemoteException;
  /**
       * 注册设备属性
       * @param deviceProperty
       */
  public void registerDeviceProperty(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty deviceProperty) throws android.os.RemoteException;
  /**
       * 取消注册设备属性
       * @param deviceProperty
       */
  public void unRegisterDeviceProperty(com.tencent.iot.hub.device.android.core.shadow.DeviceProperty deviceProperty) throws android.os.RemoteException;
  /**
       * 更新delta信息后，上报空的desired信息，通知服务器不再发送delta消息
       * @param reportJsonDoc 用户上报的JSON内容
       */
  public java.lang.String reportNullDesiredInfo(java.lang.String reportJsonDoc) throws android.os.RemoteException;
  /**
       * 初始化OTA功能。
       *
       * @param storagePath OTA升级包存储路径(调用者必须确保路径已存在，并且具有写权限)
       * @param listener    OTA事件回调
       */
  public void initOTA(java.lang.String storagePath, com.tencent.iot.hub.device.android.service.ITXOTAListener listener) throws android.os.RemoteException;
  /**
       * 上报设备当前版本信息到后台服务器。
       *
       * @param currentFirmwareVersion 设备当前版本信息
       * @return 发送成功时返回字符串"OK"; 其它返回值表示发送失败；
       */
  public java.lang.String reportCurrentFirmwareVersion(java.lang.String currentFirmwareVersion) throws android.os.RemoteException;
  /**
       * 上报设备升级状态到后台服务器。
       *
       * @param state 状态
       * @param resultCode 结果代码。0：表示成功；其它：表示失败；常见错误码：-1: 下载超时; -2:文件不存在；-3:签名过期；-4:校验错误；-5：更新固件失败
       * @param resultMsg 结果描述
       * @param version 版本号
       * @return 发送成功时返回字符串"OK"; 其它返回值表示发送失败；
       */
  public java.lang.String reportOTAState(java.lang.String state, int resultCode, java.lang.String resultMsg, java.lang.String version) throws android.os.RemoteException;
}
