package com.shi.vpnsingapore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.lang.reflect.Method;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;
import top.canyie.pine.callback.MethodReplacement;
import android.content.Context;


public class TakeMeOut {
    private static final String TAG = "HookMe";

    // Custom global exception handler
    public static void hook() {
        hookMainActivity();
        hookHelperMethods();
    }

    private static void hookMainActivity() {
        try {
            String mainActivityClass = "com.privatevpn.internetaccess.MainActivity";
            Method onCreateMethod = Class.forName(mainActivityClass).getDeclaredMethod("onCreate", Bundle.class);
            Pine.hook(onCreateMethod, new MethodHook() {
                @Override
                public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                    Toast.makeText((Context) callFrame.thisObject, "Modded by Shirayuki", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook MainActivity", e);
        }
    }

    private static void hookHelperMethods() {
        try {
            String helperClass = "com.privatevpn.internetaccess.helpers.Helper";
            hookMethod(helperClass, "isPremium", MethodReplacement.returnConstant(true));
            hookMethod(helperClass, "isDeviceRooted", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "checkRootMethod1", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "checkRootMethod2", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "checkRootMethod3", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "isAppBlocked", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "isBanned", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "checkForUpdate", MethodReplacement.DO_NOTHING);
            hookMethod(helperClass, "showUpdateDialog", MethodReplacement.DO_NOTHING);
            hookMethod(helperClass, "getBannerAdStatus", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "getInterstitialAdStatus", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "getNativeAdStatus", MethodReplacement.returnConstant(false));
            hookMethod(helperClass, "getUpdateForceUpdate", MethodReplacement.returnConstant(false));
            hookPaymentMethod(helperClass);
            getPremiumBuyTime(helperClass);
            hookPremiumEndTime(helperClass);
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook Helper methods", e);
        }
    }

    private static void hookMethod(String className, String methodName, MethodReplacement replacement) throws Exception {
        Method method = Class.forName(className).getDeclaredMethod(methodName);
        Pine.hook(method, replacement);
    }

    private static void hookPaymentMethod(String className) throws Exception {
        Method getPaymentMethod = Class.forName(className).getDeclaredMethod("getPaymentMethod");
        Pine.hook(getPaymentMethod, new MethodReplacement() {
            @Override
            public Object replaceCall(Pine.CallFrame callFrame) throws Throwable {
                return "";
            }
        });
    }
    
    private static void getPremiumBuyTime(String className) throws Exception {
        Method getPremiumBuyTime = Class.forName(className).getDeclaredMethod("getPremiumBuyTime");
        Pine.hook(getPremiumBuyTime, new MethodReplacement() {
            @Override
            public Object replaceCall(Pine.CallFrame callFrame) throws Throwable {
                return "2090-01-01 23:59:59";
            }
        });
    }

    private static void hookPremiumEndTime(String className) throws Exception {
        Method getPremiumEndTime = Class.forName(className).getDeclaredMethod("getPremiumEndTime");
        Pine.hook(getPremiumEndTime, new MethodReplacement() {
            @Override
            public Object replaceCall(Pine.CallFrame callFrame) throws Throwable {
                return "2090-01-01 23:59:59";
            }
        });
    }
}
