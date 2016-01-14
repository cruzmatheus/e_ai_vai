package matheus.com.br.eaivai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import matheus.com.br.eaivai.R;
import matheus.com.br.eaivai.adapter.DataObject;
import matheus.com.br.eaivai.adapter.MyRecyclerViewAdapter;
import matheus.com.br.eaivai.dao.EventDao;
import matheus.com.br.eaivai.entity.Event;
import matheus.com.br.eaivai.utils.Util;

/**
 * Created by matheus on 12/01/16.
 */
public class EventListActivity extends Activity {

    @Bind(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EventDao eventDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
        ButterKnife.bind(this);

        eventDao = new EventDao();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        rvAdapter = new MyRecyclerViewAdapter(getDataset());
        recyclerView.setAdapter(rvAdapter);
    }

    public void newEvent(View view) {
        Intent intent = new Intent(this, EventFormActivity.class);
        startActivity(intent);
    }

    private List<DataObject> getDataset() {
        List elements = new ArrayList<DataObject>();
        List<Event> events = eventDao.listAll();
        for (Event event : events) {
            DataObject obj = new DataObject(event.getName(), Util.parseDateToString(event.getDateTimeFrom(), "dd/MM/yyyy HH:mm"));
            elements.add(obj);
        }
        return elements;
    }
}
