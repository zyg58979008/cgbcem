package com.bootdo.common.config;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface JNATestDll extends Library {
    JNATestDll instanceDll  = (JNATestDll)Native.loadLibrary("JNATestDLL",JNATestDll.class);
    public int add(int a,int b);
    public int factorial(int n);
}