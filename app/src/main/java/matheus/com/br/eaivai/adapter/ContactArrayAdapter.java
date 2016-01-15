package matheus.com.br.eaivai.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import matheus.com.br.eaivai.R;

/**
 * Created by matheus on 14/01/16.
 */
public class ContactArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<>();


    public ContactArrayAdapter(Context context, List<String> objects) {
        super(context, R.layout.contact_list_item, R.id.titleTextView, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
