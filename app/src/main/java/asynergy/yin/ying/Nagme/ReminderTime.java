package asynergy.yin.ying.Nagme;

/**
 * Interface for ReminderTime
 * Defines behavior all Reminder(*) objects should follow
 * represents an individual reminder timing scheme (based on its frequency)
 * for a specific alarm (ex: "take medicine")
 */
public interface ReminderTime {

    //reminder types
    int ONE_TIME = 1;
    int DAILY = 2;
    int WEEKLY = 3;
    int MONTHLY = 4;

    int minToMillis = 60000;
    int DEFAULT_MIN_BETWEEN_SNOOZE = 1;

    //return what type of reminder it is
     int getReminderType();

    //return hour of particular alarm (0-24)
     int getHour();

    //return string boolean rep of weekdays sunday-saturday (will be entirely false boolean array for non-relevants)
     String getWeekdays();

    //return int for the week of each month the alarm goes off (-1 if not applicable)
     int getWeekOfMonth();

    //return string rep of one time event (will be empty string for non-relevants)
     String getDateString();

    //return minute of alarm
     int getMin();

    //pre: will always be the same as it's db id
     long getId();
     void setId(long id);

    //getter and setter for snoozeCounter
     int getSnoozeCounter();
     void setSnoozeCounter(int snoozeCounter);

    //setter and getter for snooze time
     int getSnoozeTime();
     void setSnoozeTime(int minBetweenSnooze);

    //setter and getter for next awake time
     long getNextAwakeTime();
     void setNextAwakeTime(long nextAwakeTime);

    //returns the soonest upcoming alarm time (in milliseconds)
     long getNextTime();

    //returns if this scheme will produce an upcoming alarm
     boolean hasNextTime();
}
