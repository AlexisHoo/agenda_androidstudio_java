package fr.utt.if26.agenda_copy;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUtils {

    public static LocalDate selectedDate;

    public static ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");

            }
            else{
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }

        return daysInMonthArray;
    }

    public static String monthYearFromDate(LocalDate date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public static ArrayList<String> daysInWeekArray(LocalDate selectedDate) {


    }
}
