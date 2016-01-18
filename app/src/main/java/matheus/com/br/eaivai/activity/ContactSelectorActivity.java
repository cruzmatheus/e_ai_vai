package matheus.com.br.eaivai.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import matheus.com.br.eaivai.R;
import matheus.com.br.eaivai.adapter.ContactArrayAdapter;
import matheus.com.br.eaivai.utils.Constants;

/**
 * Created by matheus on 13/01/16.
 */
public class ContactSelectorActivity extends Activity {

    @Bind(R.id.at_Contacts)
    AutoCompleteTextView contact;
    @Bind(R.id.contactList)
    ListView contacListView;

    SimpleCursorAdapter mAdapter;
    ArrayList<String> contactList;
    ContactArrayAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_selector);
        ButterKnife.bind(this);
        requestReadContactsPermission();
        setupView();
    }

    public Cursor getCursor(CharSequence str) {
        String select = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ? AND " + ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ?";
        String[]  selectArgs = { "%" + str + "%", "1"};
        String[] contactsProjection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.LOOKUP_KEY };

        return getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, select, selectArgs, null);
    }

    public void setupView() {
        contactList = new ArrayList<>();
        contactAdapter = new ContactArrayAdapter(this, contactList);
        contacListView.setAdapter(contactAdapter);
        mAdapter = new SimpleCursorAdapter(this, R.layout.single_contact, null,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER },
                new int[] {R.id.tv_ContactName, R.id.tv_ContactPhone },
                0);
        contact.setAdapter(mAdapter);

        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence str) {
                return getCursor(str);
            }
        });

        mAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            public CharSequence convertToString(Cursor cur) {
                int index = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                return cur.getString(index);
            }
        });

        contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addContactNameToList(parent);
            }
        });
    }

    public void addContactNameToList(AdapterView<?> parent) {
        contactList.add(((TextView)((LinearLayout) parent.getChildAt(0)).getChildAt(0)).getText().toString());
        contactAdapter.notifyDataSetChanged();
        contact.setText("");
    }

    private void requestReadContactsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_CONTACTS }, Constants.REQUEST_READ_CONTACTS_PERMISSION_CODE);
        }
    }

}
