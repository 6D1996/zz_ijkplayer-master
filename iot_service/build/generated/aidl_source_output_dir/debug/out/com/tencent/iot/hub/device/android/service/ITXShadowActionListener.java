/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.tencent.iot.hub.device.android.service;
public interface ITXShadowActionListener extends android.os.IInterface
{
  /** Default implementation for ITXShadowActionListener. */
  public static class Default implements com.tencent.iot.hub.device.android.service.ITXShadowActionListener
  {
    /**
         * 文档请求响应的回调接口
         *
         * @param type     文档操作方式, get/update/delete
         * @param result   请求响应结果, 0: 成功；非0：失败
         * @param document 云端返回的JSON文档
         */
    @Override public void onRequestCallback(java.lang.String type, int result, java.lang.String document) throws android.os.RemoteException
    {
    }
    /**
         * 设备属性处理回调接口
         *
         * @param propertyJSONDocument 设备属性json文档
         * @param devicePropertyList   更新后的设备属性集
         */
    @Override public void onDevicePropertyCallback(java.lang.String propertyJSONDocument, java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> devicePropertyList) throws android.os.RemoteException
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
         * @param status     OK: 订阅成功，ERROR: 订阅失败
         * @param token      消息token
         * @param errMsg     详细信息
         */
    @Override public void onSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException
    {
    }
    /**
         * 取消订阅主题完成回调
         *
         * @param status    OK: 取消订阅成功，ERROR: 取消订阅失败
         * @param token     消息token，包含消息内容结构体
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
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.tencent.iot.hub.device.android.service.ITXShadowActionListener
  {
    private static final java.lang.String DESCRIPTOR = "com.tencent.iot.hub.device.android.service.ITXShadowActionListener";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.tencent.iot.hub.device.android.service.ITXShadowActionListener interface,
     * generating a proxy if needed.
     */
    public static com.tencent.iot.hub.device.android.service.ITXShadowActionListener asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.tencent.iot.hub.device.android.service.ITXShadowActionListener))) {
        return ((com.tencent.iot.hub.device.android.service.ITXShadowActionListener)iin);
      }
      return new com.tencent.iot.hub.device.android.service.ITXShadowActionListener.Stub.Proxy(obj);
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
        case TRANSACTION_onRequestCallback:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _arg1;
          _arg1 = data.readInt();
          java.lang.String _arg2;
          _arg2 = data.readString();
          this.onRequestCallback(_arg0, _arg1, _arg2);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onDevicePropertyCallback:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> _arg1;
          _arg1 = new java.util.ArrayList<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty>();
          this.onDevicePropertyCallback(_arg0, _arg1);
          reply.writeNoException();
          reply.writeTypedList(_arg1);
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
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.tencent.iot.hub.device.android.service.ITXShadowActionListener
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
           * 文档请求响应的回调接口
           *
           * @param type     文档操作方式, get/update/delete
           * @param result   请求响应结果, 0: 成功；非0：失败
           * @param document 云端返回的JSON文档
           */
      @Override public void onRequestCallback(java.lang.String type, int result, java.lang.String document) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(type);
          _data.writeInt(result);
          _data.writeString(document);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onRequestCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onRequestCallback(type, result, document);
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
           * 设备属性处理回调接口
           *
           * @param propertyJSONDocument 设备属性json文档
           * @param devicePropertyList   更新后的设备属性集
           */
      @Override public void onDevicePropertyCallback(java.lang.String propertyJSONDocument, java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> devicePropertyList) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(propertyJSONDocument);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onDevicePropertyCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onDevicePropertyCallback(propertyJSONDocument, devicePropertyList);
            return;
          }
          _reply.readException();
          _reply.readTypedList(devicePropertyList, com.tencent.iot.hub.device.android.core.shadow.DeviceProperty.CREATOR);
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
           * @param status     OK: 订阅成功，ERROR: 订阅失败
           * @param token      消息token
           * @param errMsg     详细信息
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
           * @param token     消息token，包含消息内容结构体
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
      public static com.tencent.iot.hub.device.android.service.ITXShadowActionListener sDefaultImpl;
    }
    static final int TRANSACTION_onRequestCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_onDevicePropertyCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_onPublishCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_onSubscribeCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_onUnSubscribeCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_onMessageReceived = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    public static boolean setDefaultImpl(com.tencent.iot.hub.device.android.service.ITXShadowActionListener impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.tencent.iot.hub.device.android.service.ITXShadowActionListener getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * 文档请求响应的回调接口
       *
       * @param type     文档操作方式, get/update/delete
       * @param result   请求响应结果, 0: 成功；非0：失败
       * @param document 云端返回的JSON文档
       */
  public void onRequestCallback(java.lang.String type, int result, java.lang.String document) throws android.os.RemoteException;
  /**
       * 设备属性处理回调接口
       *
       * @param propertyJSONDocument 设备属性json文档
       * @param devicePropertyList   更新后的设备属性集
       */
  public void onDevicePropertyCallback(java.lang.String propertyJSONDocument, java.util.List<com.tencent.iot.hub.device.android.core.shadow.DeviceProperty> devicePropertyList) throws android.os.RemoteException;
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
       * @param status     OK: 订阅成功，ERROR: 订阅失败
       * @param token      消息token
       * @param errMsg     详细信息
       */
  public void onSubscribeCompleted(java.lang.String status, com.tencent.iot.hub.device.android.service.TXMqttToken token, long userContextId, java.lang.String errMsg) throws android.os.RemoteException;
  /**
       * 取消订阅主题完成回调
       *
       * @param status    OK: 取消订阅成功，ERROR: 取消订阅失败
       * @param token     消息token，包含消息内容结构体
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
}
