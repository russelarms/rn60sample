package com.rn60sample;

import android.util.Log;

import com.facebook.react.ReactActivity;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;
import com.microsoft.appcenter.utils.async.AppCenterFuture;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "rn60sample";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Crashes.hasCrashedInLastSession().thenAccept(new AppCenterConsumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean){
                    AppCenterFuture<ErrorReport> lastSessionCrashReport = Crashes.getLastSessionCrashReport();
                    lastSessionCrashReport.thenAccept(new AppCenterConsumer<ErrorReport>() {
                        @Override
                        public void accept(ErrorReport errorReport) {
                            Log.d("rn60 sample", "errorReport:" + errorReport);
                        }
                    });
                }
            }
        });
    }
}
