package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class WorkingTimesDAO implements  WorkingTimesDAOInterface{
    private static Map<String, Map<Date, List<Time>>> reservedDoctors = new HashMap<>();
    private static final int NEXT_DAYS = 8;
    private static final int WORK_START_TIME = 10;

    @Override
    public void addDoctor(Doctor doctor) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        List<Time> dayGraphic = makeDayGraphic();
        Map<Date, List<Time> > dateAndTime = makeDateAndTime(now, dayGraphic);
        reservedDoctors.put(doctor.getID(), dateAndTime);
    }
    /**
     *
     * @param date current date.
     * @param dayGraphic method makeDayGraphic();
     * @return map, where Keys are next 7 days and values each days working times introduce in arraylist.
     */
    private Map<Date, List<Time>> makeDateAndTime(Date date, List<Time> dayGraphic) {
        Map<Date, List<Time> > dateAndTime = new HashMap<>();
        for(int days=0; days < NEXT_DAYS ;days ++) {
            Date nextDate = new Date(date.getYear(), date.getMonth()-1, date.getDate()+days);
            dateAndTime.put(nextDate,dayGraphic);
        }
        return dateAndTime;
    }

    /**
     *
     * @return each day working graphic times.
     * */
    private List<Time> makeDayGraphic() {
        List<Time> dayGraphic = new ArrayList<>();
        int minute = 0;
        int second = 0;
        int hour = WORK_START_TIME;
        for(int i =0; i < NEXT_DAYS; i ++) {
            Time times = new Time(hour + i, minute, second);
            dayGraphic.add(times);
        }
        return dayGraphic;
    }

    @Override
    public void updateBase() {
        //TODO
    }

    @Override
    public void reserveDoctor(Doctor doctor, Date date, Time time) {
        //TODO
    }
}
