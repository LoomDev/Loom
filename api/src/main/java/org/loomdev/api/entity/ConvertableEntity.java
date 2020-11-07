package org.loomdev.api.entity;

public interface ConvertableEntity {

    int getConversionTime();

    void setConversionTime(int ticks);

    boolean isConverting();

    void convert();

}
