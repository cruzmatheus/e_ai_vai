package matheus.com.br.eaivai.dao;

import android.content.Context;

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
        realm.copyToRealm(e);
        realm.commitTransaction();
    }
}
