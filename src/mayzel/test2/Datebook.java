package mayzel.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */
public class Datebook {
	Map<Integer, Event> map = new HashMap<Integer, Event>();
	List<Event> listOfEvents = new ArrayList<Event>();
	Map<Event, Date> map2 = new HashMap<Event, Date>();

	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */
	public void addSingleEvent(Event event, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOf = calendar.get(Calendar.DAY_OF_YEAR);
		map.put(dayOf, event);
		listOfEvents.add(map.get(dayOf));
		map2.put(map.put(dayOf, event), date);

	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {
		listOfEvents.add(event);

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(dayOfWeek);
		while (true) {
			Event e = map.put(weekDay, event);
			listOfEvents.add(e);
			map2.put(e, date);
			weekDay = weekDay + 7;
		}

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int monthOf = calendar.get(Calendar.MONTH);
		while (true) {
			Event e = map.put(monthOf, event);
			listOfEvents.add(e);
			map2.put(e, date);
			monthOf++;

		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int yearsDay = calendar.get(dayOfYear);
		while (true) {
			Event e = map.put(yearsDay, event);
			map2.put(e, date);
			listOfEvents.add(e);
		}
	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < listOfEvents.size(); i++) {
			if (date.equals(map2.get(listOfEvents.get(i)))) {
				events.add(listOfEvents.get(i));
			}
		}
		Collections.sort(events);
		return events;

	}

}
