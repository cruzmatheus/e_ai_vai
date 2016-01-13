package matheus.com.br.eaivai.dao;

import com.orhanobut.logger.Logger;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by matheus on 05/01/16.
 */
public class AbstractDao<E extends ParseObject> {

    Class<E> classType;

    public AbstractDao(Class<E> classType) {
        this.classType = classType;
    }

    public boolean save(E e) {
        e.pinInBackground();
        return (e.saveEventually() != null);
    }

    public List<E> listAll() {
        try {
            ParseQuery<E> query = ParseQuery.getQuery(classType);
            query.fromLocalDatastore();
            return query.find();
        } catch (Exception ex) {
            Logger.e(ex.getMessage());
        }

        return null;
    }


}
