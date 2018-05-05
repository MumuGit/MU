package com.diabin.design_pattern.adapter;

/**
 * 将220v电压转换为5v
 */
public class VoltAdapter implements FiveVolt {
    Volt220 mVolt220;

    public VoltAdapter(Volt220 mVolt220) {
        this.mVolt220 = mVolt220;
    }

    @Override
    public int getVolt5() {
        return mVolt220.getVolt220() / 44;
    }
}
