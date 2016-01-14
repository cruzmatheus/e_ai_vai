package matheus.com.br.eaivai.activity;

import android.app.Application;

import com.orhanobut.logger.Logger;

import matheus.com.br.eaivai.utils.ParseUtils;

/**
 * Created by matheus on 12/01/16.
 */
public class InitializerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("EAIVAI");
        ParseUtils.initializeParse(getApplicationContext());
    }
}
