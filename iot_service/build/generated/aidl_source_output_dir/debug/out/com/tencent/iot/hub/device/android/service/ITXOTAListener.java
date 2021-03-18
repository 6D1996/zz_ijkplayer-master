/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.tencent.iot.hub.device.android.service;
public interface ITXOTAListener extends android.os.IInterface
{
  /** Default implementation for ITXOTAListener. */
  public static class Default implements com.tencent.iot.hub.device.android.service.ITXOTAListener
  {
    /**
         * 上报固件版本回调
         *
         * @param resultCode  上报结果码；0：成功；其它：失败
         * @param version  版本；
         * @param resultMsg  上报结果码描述
         */
    @Override public void onReportFirmwareVersion(int resultCode, java.lang.String version, java.lang.String resultMsg) throws android.os.RemoteException
    {
    }
    /**
        * OTA升级包下载进度回调
        *
        * @param percent  下载进度（0 ~ 100）;
        * @param version  版本；
        */
    @Override public void onDownloadProgress(int percent, java.lang.String version) throws android.os.RemoteException
    {
    }
    /**
        * OTA升级包下载完成回调
        * @param outputFile  已下载完成的升级包文件名（包含全路径）；
        * @param version  版本；
        */
    @Override public void onDownloadCompleted(java.lang.String outputFile, java.lang.String version) throws android.os.RemoteException
    {
    }
    /**
        * OTA升级包下载失败回调
        *
        * @param errCode  失败错误码; -1: 下载超时; -2:文件不存在；-3:签名过期；-4:校验错误；
        * @param version  版本；
        */
    @Override public void onDownloadFailure(int errCode, java.lang.String version) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.tencent.iot.hub.device.android.service.ITXOTAListener
  {
    private static final java.lang.String DESCRIPTOR = "com.tencent.iot.hub.device.android.service.ITXOTAListener";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.tencent.iot.hub.device.android.service.ITXOTAListener interface,
     * generating a proxy if needed.
     */
    public static com.tencent.iot.hub.device.android.service.ITXOTAListener asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.tencent.iot.hub.device.android.service.ITXOTAListener))) {
        return ((com.tencent.iot.hub.device.android.service.ITXOTAListener)iin);
      }
      return new com.tencent.iot.hub.device.android.service.ITXOTAListener.Stub.Proxy(obj);
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
        case TRANSACTION_onReportFirmwareVersion:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String _arg1;
          _arg1 = data.readString();
          java.lang.String _arg2;
          _arg2 = data.readString();
          this.onReportFirmwareVersion(_arg0, _arg1, _arg2);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onDownloadProgress:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.onDownloadProgress(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onDownloadCompleted:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.onDownloadCompleted(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_onDownloadFailure:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.onDownloadFailure(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.tencent.iot.hub.device.android.service.ITXOTAListener
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
           * 上报固件版本回调
           *
           * @param resultCode  上报结果码；0：成功；其它：失败
           * @param version  版本；
           * @param resultMsg  上报结果码描述
           */
      @Override public void onReportFirmwareVersion(int resultCode, java.lang.String version, java.lang.String resultMsg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(resultCode);
          _data.writeString(version);
          _data.writeString(resultMsg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onReportFirmwareVersion, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onReportFirmwareVersion(resultCode, version, resultMsg);
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
          * OTA升级包下载进度回调
          *
          * @param percent  下载进度（0 ~ 100）;
          * @param version  版本；
          */
      @Override public void onDownloadProgress(int percent, java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(percent);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onDownloadProgress, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onDownloadProgress(percent, version);
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
          * OTA升级包下载完成回调
          * @param outputFile  已下载完成的升级包文件名（包含全路径）；
          * @param version  版本；
          */
      @Override public void onDownloadCompleted(java.lang.String outputFile, java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(outputFile);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onDownloadCompleted, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onDownloadCompleted(outputFile, version);
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
          * OTA升级包下载失败回调
          *
          * @param errCode  失败错误码; -1: 下载超时; -2:文件不存在；-3:签名过期；-4:校验错误；
          * @param version  版本；
          */
      @Override public void onDownloadFailure(int errCode, java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(errCode);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onDownloadFailure, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onDownloadFailure(errCode, version);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static com.tencent.iot.hub.device.android.service.ITXOTAListener sDefaultImpl;
    }
    static final int TRANSACTION_onReportFirmwareVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_onDownloadProgress = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_onDownloadCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_onDownloadFailure = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    public static boolean setDefaultImpl(com.tencent.iot.hub.device.android.service.ITXOTAListener impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.tencent.iot.hub.device.android.service.ITXOTAListener getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * 上报固件版本回调
       *
       * @param resultCode  上报结果码；0：成功；其它：失败
       * @param version  版本；
       * @param resultMsg  上报结果码描述
       */
  public void onReportFirmwareVersion(int resultCode, java.lang.String version, java.lang.String resultMsg) throws android.os.RemoteException;
  /**
      * OTA升级包下载进度回调
      *
      * @param percent  下载进度（0 ~ 100）;
      * @param version  版本；
      */
  public void onDownloadProgress(int percent, java.lang.String version) throws android.os.RemoteException;
  /**
      * OTA升级包下载完成回调
      * @param outputFile  已下载完成的升级包文件名（包含全路径）；
      * @param version  版本；
      */
  public void onDownloadCompleted(java.lang.String outputFile, java.lang.String version) throws android.os.RemoteException;
  /**
      * OTA升级包下载失败回调
      *
      * @param errCode  失败错误码; -1: 下载超时; -2:文件不存在；-3:签名过期；-4:校验错误；
      * @param version  版本；
      */
  public void onDownloadFailure(int errCode, java.lang.String version) throws android.os.RemoteException;
}
