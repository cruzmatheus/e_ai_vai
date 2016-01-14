package matheus.com.br.eaivai.utils;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

import matheus.com.br.eaivai.entity.Event;

/**
 * Created by matheus on 11/01/16.
 */
public class ParseUtils {

    public static void initializeParse(Context context) {
        ParseObject.registerSubclass(Event.class);
        Parse.enableLocalDatastore(context);
        Parse.initialize(context);
    }
}
