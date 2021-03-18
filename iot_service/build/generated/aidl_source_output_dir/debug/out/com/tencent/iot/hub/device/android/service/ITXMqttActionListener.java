/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.tencent.iot.hub.device.android.service;
public interface ITXMqttActionListener extends android.os.IInterface
{
  /** Default implementation for ITXMqttActionListener. */
  public static class Default implements com.tencent.iot.hub.device.android.service.ITXMqttActionListener
  {
    /**
         * MQTT Connect完成回调
         *
         * @param status    OK: 连接成功        ERROR: 连接失败
         * @param reconnect true: 重新连接      false: 首次连接
         * @param msg       连接信息
         */
    @Override public void onConnectCompleted(java.lang.String status, boolean reconnect, long userContextId, java.lang.String msg) throws android.os.RemoteException
    {
    }
    /**
         * MQTT连接断开回调
         *
         * @param cause 连接断开原因
         */
    @Override public void onConnectionLost(java.lang.String cause) throws android.os.RemoteException
    {
    }
    /**
         * MQTT Disconnect完成回调
         *
         * @param status OK: 断连成功，ERROR: 断连失败
         * @param msg    相信信息
         */
    @Override public void onDisconnectCompleted(java.lang.String status, long userContextId, java.lang.String msg) throws android.os.RemoteException
    {
    }
    /**
         * 发布消息完成回调
         *
         * @param status OK: 发布消息成功，ERROR: 发布消息失败
         * @param token  消息token
         * @param errMsg 详细信息
         */
    @Override public void onPublishCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
    {
    }
    /**
         * 订阅主题完成回调
         *
         * @param status  OK: 订阅成功，ERROR: 订阅失败
         * @param token   消息token
         * @param errMsg  详细信息
         */
    @Override public void onSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
    {
    }
    /**
         * 取消订阅主题完成回调
         *
         * @param status    OK: 取消订阅成功，ERROR: 取消订阅失败
         * @param token     消息token
         * @param errMsg    详细信息
         */
    @Override public void onUnSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
    {
    }
    /**
         * 收到订阅主题的消息Push
         *
         * @param topic   主题名称
         * @param message 消息内容
         */
    @Override public void onMessageReceived(java.lang.String topic, com.tencent.iot.hub.device.android.service.TXMqttMessage message) throws android.os.RemoteException
    {
    }
    /**
         * 远程服务已启动回调接口
         */
    @Override public void onServiceStartedCallback() throws android.os.RemoteException
    {
    }
    /**
         * 远程服务销毁回调接口
         */
    @Override public void onServiceDestroyCallback() throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.tencent.iot.hub.device.android.service.ITXMqttActionListener
  {
    private static final java.lang.String DESCRIPTOR = "com.tencent.iot.hub.device.android.service.ITXMqttActionListener";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.tencent.iot.hub.device.android.service.ITXMqttActionListener interface,
     * generating a proxy if needed.
     */
    public static com.tencent.iot.hub.device.android.service.ITXMqttActionListener asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.tencent.iot.hub.device.android.service.ITXMqttActionListener))) {
        return ((com.tencent.iot.hub.device.android.service.ITXMqttActionListener)iin);
      }
      return new com.tencent.iot.hub.device.android.service.ITXMqttActionListener.Stub.Proxy(obj);
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
        case TRANSACTION_onConnectCompleted:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          boolean _arg1;
          _arg1 = (0!=data.readInt());
          long _arg2;
          _arg2 = data.readLong();
          java.lang.String _arg3;
          _arg3 = data.readString();
          this.onConnectCompleted(_arg0, _arg1, _arg2, _arg3);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onConnectionLost:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          this.onConnectionLost(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onDisconnectCompleted:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          long _arg1;
          _arg1 = data.readLong();
          java.lang.String _arg2;
          _arg2 = data.readString();
          this.onDisconnectCompleted(_arg0, _arg1, _arg2);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onPublishCompleted:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          com.tencent.iot.hub.device.android.service.TXMqttToken _arg1;
          if ((0!=data.readInt())) {
            _arg1 = com.tencent.iot.hub.device.android.service.TXMqttToken.CREATOR.createFromParcel(data);
          }
          else {
            _arg1 = null;
          }
          long _arg2;
          _arg2 = data.readLong();
          java.lang.String _arg3;
          _arg3 = data.readString();
          this.onPublishCompleted(_arg0, _arg1, _arg2, _arg3);
          reply.writeNoException();
          if ((_arg1!=null)) {
            reply.writeInt(1);
            _arg1.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_onSubscribeCompleted:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          com.tencent.iot.hub.device.android.service.TXMqttToken _arg1;
          if ((0!=data.readInt())) {
            _arg1 = com.tencent.iot.hub.device.android.service.TXMqttToken.CREATOR.createFromParcel(data);
          }
          else {
            _arg1 = null;
          }
          long _arg2;
          _arg2 = data.readLong();
          java.lang.String _arg3;
          _arg3 = data.readString();
          this.onSubscribeCompleted(_arg0, _arg1, _arg2, _arg3);
          reply.writeNoException();
          if ((_arg1!=null)) {
            reply.writeInt(1);
            _arg1.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_onUnSubscribeCompleted:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          com.tencent.iot.hub.device.android.service.TXMqttToken _arg1;
          if ((0!=data.readInt())) {
            _arg1 = com.tencent.iot.hub.device.android.service.TXMqttToken.CREATOR.createFromParcel(data);
          }
          else {
            _arg1 = null;
          }
          long _arg2;
          _arg2 = data.readLong();
          java.lang.String _arg3;
          _arg3 = data.readString();
          this.onUnSubscribeCompleted(_arg0, _arg1, _arg2, _arg3);
          reply.writeNoException();
          if ((_arg1!=null)) {
            reply.writeInt(1);
            _arg1.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_onMessageReceived:
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
          this.onMessageReceived(_arg0, _arg1);
          reply.writeNoException();
          if ((_arg1!=null)) {
            reply.writeInt(1);
            _arg1.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_onServiceStartedCallback:
        {
          data.enforceInterface(descriptor);
          this.onServiceStartedCallback();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onServiceDestroyCallback:
        {
          data.enforceInterface(descriptor);
          this.onServiceDestroyCallback();
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.tencent.iot.hub.device.android.service.ITXMqttActionListener
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
           * MQTT Connect完成回调
           *
           * @param status    OK: 连接成功        ERROR: 连接失败
           * @param reconnect true: 重新连接      false: 首次连接
           * @param msg       连接信息
           */
      @Override public void onConnectCompleted(java.lang.String status, boolean reconnect, long userContextId, java.lang.String msg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(status);
          _data.writeInt(((reconnect)?(1):(0)));
          _data.writeLong(userContextId);
          _data.writeString(msg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onConnectCompleted, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onConnectCompleted(status, reconnect, userContextId, msg);
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
           * MQTT连接断开回调
           *
           * @param cause 连接断开原因
           */
      @Override public void onConnectionLost(java.lang.String cause) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(cause);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onConnectionLost, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onConnectionLost(cause);
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
           * MQTT Disconnect完成回调
           *
           * @param status OK: 断连成功，ERROR: 断连失败
           * @param msg    相信信息
           */
      @Override public void onDisconnectCompleted(java.lang.String status, long userContextId, java.lang.String msg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(status);
          _data.writeLong(userContextId);
          _data.writeString(msg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onDisconnectCompleted, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onDisconnectCompleted(status, userContextId, msg);
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
           * 发布消息完成回调
           *
           * @param status OK: 发布消息成功，ERROR: 发布消息失败
           * @param token  消息token
           * @param errMsg 详细信息
           */
      @Override public void onPublishCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(status);
          if ((token!=null)) {
            _data.writeInt(1);
            token.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          _data.writeLong(userContextId);
          _data.writeString(errMsg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onPublishCompleted, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onPublishCompleted(status, token, userContextId, errMsg);
            return;
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            token.readFromParcel(_reply);
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 订阅主题完成回调
           *
           * @param status  OK: 订阅成功，ERROR: 订阅失败
           * @param token   消息token
           * @param errMsg  详细信息
           */
      @Override public void onSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(status);
          if ((token!=null)) {
            _data.writeInt(1);
            token.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          _data.writeLong(userContextId);
          _data.writeString(errMsg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onSubscribeCompleted, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onSubscribeCompleted(status, token, userContextId, errMsg);
            return;
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            token.readFromParcel(_reply);
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 取消订阅主题完成回调
           *
           * @param status    OK: 取消订阅成功，ERROR: 取消订阅失败
           * @param token     消息token
           * @param errMsg    详细信息
           */
      @Override public void onUnSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(status);
          if ((token!=null)) {
            _data.writeInt(1);
            token.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          _data.writeLong(userContextId);
          _data.writeString(errMsg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onUnSubscribeCompleted, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onUnSubscribeCompleted(status, token, userContextId, errMsg);
            return;
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            token.readFromParcel(_reply);
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 收到订阅主题的消息Push
           *
           * @param topic   主题名称
           * @param message 消息内容
           */
      @Override public void onMessageReceived(java.lang.String topic, com.tencent.iot.hub.device.android.service.TXMqttMessage message) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
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
          boolean _status = mRemote.transact(Stub.TRANSACTION_onMessageReceived, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onMessageReceived(topic, message);
            return;
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            message.readFromParcel(_reply);
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * 远程服务已启动回调接口
           */
      @Override public void onServiceStartedCallback() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onServiceStartedCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onServiceStartedCallback();
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
           * 远程服务销毁回调接口
           */
      @Override public void onServiceDestroyCallback() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onServiceDestroyCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onServiceDestroyCallback();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static com.tencent.iot.hub.device.android.service.ITXMqttActionListener sDefaultImpl;
    }
    static final int TRANSACTION_onConnectCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_onConnectionLost = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_onDisconnectCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_onPublishCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_onSubscribeCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_onUnSubscribeCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    static final int TRANSACTION_onMessageReceived = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
    static final int TRANSACTION_onServiceStartedCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
    static final int TRANSACTION_onServiceDestroyCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
    public static boolean setDefaultImpl(com.tencent.iot.hub.device.android.service.ITXMqttActionListener impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.tencent.iot.hub.device.android.service.ITXMqttActionListener getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * MQTT Connect完成回调
       *
       * @param status    OK: 连接成功        ERROR: 连接失败
       * @param reconnect true: 重新连接      false: 首次连接
       * @param msg       连接信息
       */
  public void onConnectCompleted(java.lang.String status, boolean reconnect, long userContextId, java.lang.String msg) throws android.os.RemoteException;
  /**
       * MQTT连接断开回调
       *
       * @param cause 连接断开原因
       */
  public void onConnectionLost(java.lang.String cause) throws android.os.RemoteException;
  /**
       * MQTT Disconnect完成回调
       *
       * @param status OK: 断连成功，ERROR: 断连失败
       * @param msg    相信信息
       */
  public void onDisconnectCompleted(java.lang.String status, long userContextId, java.lang.String msg) throws android.os.RemoteException;
  /**
       * 发布消息完成回调
       *
       * @param status OK: 发布消息成功，ERROR: 发布消息失败
       * @param token  消息token
       * @param errMsg 详细信息
       */
  public void onPublishCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException;
  /**
       * 订阅主题完成回调
       *
       * @param status  OK: 订阅成功，ERROR: 订阅失败
       * @param token   消息token
       * @param errMsg  详细信息
       */
  public void onSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException;
  /**
       * 取消订阅主题完成回调
       *
       * @param status    OK: 取消订阅成功，ERROR: 取消订阅失败
       * @param token     消息token
       * @param errMsg    详细信息
       */
  public void onUnSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException;
  /**
       * 收到订阅主题的消息Push
       *
       * @param topic   主题名称
       * @param message 消息内容
       */
  public void onMessageReceived(java.lang.String topic, com.tencent.iot.hub.device.android.service.TXMqttMessage message) throws android.os.RemoteException;
  /**
       * 远程服务已启动回调接口
       */
  public void onServiceStartedCallback() throws android.os.RemoteException;
  /**
       * 远程服务销毁回调接口
       */
  public void onServiceDestroyCallback() throws android.os.RemoteException;
}
