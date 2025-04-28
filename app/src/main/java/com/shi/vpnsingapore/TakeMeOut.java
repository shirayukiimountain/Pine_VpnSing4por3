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

    // Constants for class and method names
    private static final String MAIN_ACTIVITY_CLASS = "com.privatevpn.internetaccess.MainActivity";
    private static final String HELPER_CLASS = "com.privatevpn.internetaccess.helpers.Helper";

    // Entry point to initialize all hooks
    public static void hook() {
        hookMainActivity();
        hookHelperMethods();
    }

    private static void hookMainActivity() {
        try {
            Class<?> mainActivity = Class.forName(MAIN_ACTIVITY_CLASS);
            Method onCreateMethod = mainActivity.getDeclaredMethod("onCreate", Bundle.class);

            Pine.hook(onCreateMethod, new MethodHook() {
                @Override
                public void beforeCall(Pine.CallFrame callFrame) {
                    try {
                        Context context = (Context) callFrame.thisObject;
                        Toast.makeText(context, "Modded by Shirayuki", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Log.e(TAG, "Error displaying Toast in MainActivity hook", e);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook MainActivity", e);
        }
    }

    private static void hookHelperMethods() {
        try {
            // Define hooks with replacement logic
            hookMethod(HELPER_CLASS, "isPremium", MethodReplacement.returnConstant(true));
            hookMethod(HELPER_CLASS, "isDeviceRooted", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "checkRootMethod1", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "checkRootMethod2", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "checkRootMethod3", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "isAppBlocked", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "isBanned", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "checkForUpdate", MethodReplacement.DO_NOTHING);
            hookMethod(HELPER_CLASS, "showUpdateDialog", MethodReplacement.DO_NOTHING);
            hookMethod(HELPER_CLASS, "getBannerAdStatus", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "getInterstitialAdStatus", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "getNativeAdStatus", MethodReplacement.returnConstant(false));
            hookMethod(HELPER_CLASS, "getUpdateForceUpdate", MethodReplacement.returnConstant(false));
            // Specific hooks
            hookPaymentMethod(HELPER_CLASS);
            hookPremiumBuyTime(HELPER_CLASS);
            hookPremiumEndTime(HELPER_CLASS);
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook Helper methods", e);
        }
    }

    private static void hookMethod(String className, String methodName, MethodReplacement replacement) {
        try {
            Method method = Class.forName(className).getDeclaredMethod(methodName);
            Pine.hook(method, replacement);
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook method: " + methodName + " in class: " + className, e);
        }
    }

    private static void hookPaymentMethod(String className) {
        try {
            Method getPaymentMethod = Class.forName(className).getDeclaredMethod("getPaymentMethod");
            Pine.hook(getPaymentMethod, new MethodReplacement() {
                @Override
                public Object replaceCall(Pine.CallFrame callFrame) {
                    return ""; // Return an empty string as the payment method
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook getPaymentMethod", e);
        }
    }

    private static void hookPremiumBuyTime(String className) {
        try {
            Method getPremiumBuyTime = Class.forName(className).getDeclaredMethod("getPremiumBuyTime");
            Pine.hook(getPremiumBuyTime, new MethodReplacement() {
                @Override
                public Object replaceCall(Pine.CallFrame callFrame) {
                    return "2090-01-01 23:59:59"; // Simulate lifetime premium
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook getPremiumBuyTime", e);
        }
    }

    private static void hookPremiumEndTime(String className) {
        try {
            Method getPremiumEndTime = Class.forName(className).getDeclaredMethod("getPremiumEndTime");
            Pine.hook(getPremiumEndTime, new MethodReplacement() {
                @Override
                public Object replaceCall(Pine.CallFrame callFrame) {
                    return "2090-01-01 23:59:59"; // Simulate lifetime premium
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook getPremiumEndTime", e);
        }
    }
}
