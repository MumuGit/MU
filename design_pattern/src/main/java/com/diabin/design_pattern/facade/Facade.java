package com.diabin.design_pattern.facade;

public class Facade {
    private SubSystemA mPhone = new SubSystemA();
    private SubSystemB mCamera = new SubSystemB();

    public void videoChat() {
        System.out.println("视频聊天");
        mCamera.openCamera();
        mPhone.dail();
    }
}
