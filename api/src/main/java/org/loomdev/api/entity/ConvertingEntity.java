package org.loomdev.api.entity;

public interface ConvertingEntity {

    int getConversionTime();

    void setConversionTime(int ticks);

    boolean isConverting();

    void convert();

}
