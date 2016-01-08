package matheus.com.br.eaivai.dao;

import android.content.Context;

import java.lang.reflect.Method;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by matheus on 05/01/16.
 */
public class AbstractDao<E extends RealmObject> {

    protected Realm realm;

    public AbstractDao(Context context) {
        realm = Realm.getInstance(context);
    }

    public void save(E e) {
        realm.beginTransaction();
        e = generateIdForClass(e);
        realm.copyToRealm(e);
        realm.commitTransaction();
    }

    public long getNextId(E e) {
        return realm.where(e.getClass()).max("id").longValue() + 1;
    }

    public E generateIdForClass(E e) {
        Method[] methods = e.getClass().getDeclaredMethods();
        try {
            for (Method method : methods) {
                if (method.getName().contains("setId")) {
                    method.invoke(e, getNextId(e));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return e;
    }
}
