package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import android.view.WindowManager;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

public class ReactNativeDisableScreenshotModule extends ReactContextBaseJavaModule {

    private static final String DISABLE_SCREENSHOT_ERROR_CODE = "DISABLE_SCREENSHOT_ERROR_CODE";
    private final ReactApplicationContext reactContext;

    public ReactNativeDisableScreenshotModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeDisableScreenshot";
    }

    @ReactMethod
    public void start(final Promise promise) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    getCurrentActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
                    promise.resolve("Disable screenshot success.");
                } catch (Exception e) {
                    promise.reject(DISABLE_SCREENSHOT_ERROR_CODE, "Failed disable screenshot.");
                }
            }
        });
    }

    @ReactMethod
    public void stop(final Promise promise) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    getCurrentActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                    promise.resolve("Enable screenshot success.");
                } catch (Exception e) {
                    promise.reject(DISABLE_SCREENSHOT_ERROR_CODE, "Failed enable screenshot.");
                }
            }
        });
    }
  }
