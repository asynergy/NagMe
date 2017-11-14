package asynergy.yin.ying.Nagme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * SetWeekly allows the user to set the days of the week for a weekly reminder to ring
 */
public class SetWeekly extends AppCompatActivity {

    public static String WEEKDAY_ARRAY= "weekday_array";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_days);

        final ToggleSlider sunday = (ToggleSlider) findViewById(R.id.Sunday);
        final ToggleSlider monday = (ToggleSlider) findViewById(R.id.Monday);
        final ToggleSlider tuesday = (ToggleSlider) findViewById(R.id.Tuesday);
        final ToggleSlider wednesday = (ToggleSlider) findViewById(R.id.Wednesday);
        final ToggleSlider thursday = (ToggleSlider) findViewById(R.id.Thursday);
        final ToggleSlider friday = (ToggleSlider) findViewById(R.id.Friday);
        final ToggleSlider saturday = (ToggleSlider) findViewById(R.id.Saturday);
        final ArrayList<ToggleSlider> toggleSliders = new ArrayList<>();
        toggleSliders.add(sunday);
        toggleSliders.add(monday);
        toggleSliders.add(tuesday);
        toggleSliders.add(wednesday);
        toggleSliders.add(thursday);
        toggleSliders.add(friday);
        toggleSliders.add(saturday);


        Button next = (Button) findViewById(R.id.btn_next);

        //get passed in data
        final Intent prevIntent = getIntent(); //gets the previously created intent
        final String alarmName = prevIntent.getStringExtra(SetAlarmInfo.ALARM_NAME);
        final String alarmTone = prevIntent.getStringExtra(ReminderListActivity.ALARM_TONE);
        final int reminderType = prevIntent.getIntExtra(SetFrequency.REMINDER_TYPE, -1);
        final boolean existingModel = prevIntent.getBooleanExtra(ReminderListActivity.EXISTING_MODEL, false);
        final long existingModelId = prevIntent.getLongExtra(ReminderListActivity.EXISTING_MODEL_ID, -1);

        final int minBetweenSnooze = prevIntent.getIntExtra(ReminderListActivity.MIN_BETWEEN_SNOOZE, ReminderTime.DEFAULT_MIN_BETWEEN_SNOOZE);

        //when the user clicks the next button, check which weekdays are checked
        //and store this data as an array of booleans
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   boolean[] weekdays = new boolean[7];//boolean string i.e "0011010"
                   int x = 0;
                for (ToggleSlider c : toggleSliders) {
                       weekdays[x] = c.isChecked();
                       x++;
                   }
                   //start the setTime class and pass in information set in previous and this
                   //activity
                   Intent i = new Intent(SetWeekly.this, SetTime.class);
                   i.putExtra(SetAlarmInfo.ALARM_NAME, alarmName);
                   i.putExtra(ReminderListActivity.ALARM_TONE, alarmTone);
                   i.putExtra(ReminderListActivity.EXISTING_MODEL, existingModel);
                   i.putExtra(ReminderListActivity.EXISTING_MODEL_ID, existingModelId);
                   i.putExtra(ReminderListActivity.MIN_BETWEEN_SNOOZE, minBetweenSnooze);
                   i.putExtra(SetFrequency.REMINDER_TYPE, reminderType);
                   i.putExtra(WEEKDAY_ARRAY, weekdays);
                   startActivity(i);
            }
        });

    }

    //populate the actionbar with a cancel icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cancel, menu);
        return true;
    }

    //on the click of the cancel button, go back to a list of alarms the user has set
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancel_button: {
                Intent intent = new Intent(this, AlarmListActivity.class);
                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
