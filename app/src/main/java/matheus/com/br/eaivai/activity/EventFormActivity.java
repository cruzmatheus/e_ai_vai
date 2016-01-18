package matheus.com.br.eaivai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.orhanobut.logger.Logger;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import matheus.com.br.eaivai.R;
import matheus.com.br.eaivai.dao.EventDao;
import matheus.com.br.eaivai.entity.Event;
import matheus.com.br.eaivai.utils.Util;

public class EventFormActivity extends AppCompatActivity implements OnDateSetListener, com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener {

    private GoogleApiClient googleApiClient;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    @Bind(R.id.event_name)
    TextView name;
    @Bind(R.id.event_location)
    EditText location;
    @Bind(R.id.event_datetime_from)
    EditText dateTimeFrom;
    @Bind(R.id.event_datetime_to)
    EditText dateTimeTo;
    @Bind(R.id.event_recurring)
    CheckBox recurring;
    String dateHelper;
    int dateTimeId;
    EventDao eventDao;
    Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_form_activity);
        ButterKnife.bind(this);

        event = new Event();
        eventDao = new EventDao();

    }

    public void searchEstablishments(View view) {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(typeFilter)
                    .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @OnClick({R.id.event_datetime_from,R.id.event_datetime_to})
    public void changeDate(EditText field) {
        dateTimeId = field.getId();
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                EventFormActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show(getFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        dateHelper = String.format("%d/%d/%d", dayOfMonth, (monthOfYear + 1), year);
        Calendar now = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.time.TimePickerDialog timePicker = com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(
                EventFormActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        timePicker.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String helper = dateHelper + "  " + String.format("%d:%d", hourOfDay, minute);
        if (dateTimeId == dateTimeFrom.getId()) {
            dateTimeFrom.setText(helper);
            event.setDateTimeFrom(Util.parseStringToDate(helper, "dd/MM/yyyy HH:mm"));
        } else {
            dateTimeTo.setText(helper);
            event.setDatetimeTo(Util.parseStringToDate(helper, "dd/MM/yyyy HH:mm"));
        }
    }

    public void inviteContacts(View view) {
        event.setName(name.getText().toString());
        event.setRecurring(recurring.isChecked());

        Intent intent = new Intent(this, ContactSelectorActivity.class);
//        intent.putExtra("event", event);
        startActivity(intent);
    }

    public void saveEvent(View view) {
        event.setName(name.getText().toString());
        event.setRecurring(recurring.isChecked());

        boolean saved = false;

        if (validateForm()) {
            saved = eventDao.save(event);
        }

        if (saved) {
            Intent intent = new Intent(this, EventListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                LatLng latLng = place.getLatLng();
                location.setText(place.getName());
                event.setLatitude(latLng.latitude);
                event.setLongetude(latLng.longitude);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Logger.e(status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        TextView[] inputs = {name, dateTimeFrom, dateTimeTo, location};
        for (TextView tv : inputs) {
            if (Util.isBlank(tv.getText().toString())) {
                tv.setError("Não pode ficar em branco.");
                valid = false;
            }
        }

        return valid;
    }
}
