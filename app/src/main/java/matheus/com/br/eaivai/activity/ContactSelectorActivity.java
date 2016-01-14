package matheus.com.br.eaivai.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.AutoCompleteTextView;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import matheus.com.br.eaivai.R;
import matheus.com.br.eaivai.utils.Constants;

/**
 * Created by matheus on 13/01/16.
 */
public class ContactSelectorActivity extends Activity {

    @Bind(R.id.at_Contacts)
    AutoCompleteTextView contact;

    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_selector);
        ButterKnife.bind(this);
        requestReadContactsPermission();

        mAdapter = new SimpleCursorAdapter(this, R.layout.single_contact, null,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER },
                new int[] {R.id.tv_ContactName, R.id.tv_ContactPhone },
                0);
        contact.setAdapter(mAdapter);

        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence str) {
                return getCursor(str);
            } });

        mAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            public CharSequence convertToString(Cursor cur) {
                int index = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                return cur.getString(index);
            }
        });

    }

    public Cursor getCursor(CharSequence str) {
        String select = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ? ";
        String[]  selectArgs = { "%" + str + "%"};
        String[] contactsProjection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.LOOKUP_KEY };

        return getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, select, selectArgs, null);
    }

    private void requestReadContactsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_CONTACTS }, Constants.REQUEST_READ_CONTACTS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_READ_CONTACTS_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
            default:
        }
    }
}
