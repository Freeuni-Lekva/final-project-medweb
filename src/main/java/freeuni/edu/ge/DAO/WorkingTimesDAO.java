package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class WorkingTimesDAO {
        //implements  WorkingTimesDAOInterface{
    private static Map<String, Map<Date, List<Time>>> reservedDoctors = new HashMap<>();
    private static Map<String, List<Date>> eachDoctorWorkingDates = new HashMap<>();

    private static final int NEXT_DAYS = 8;
    private static final int WORK_START_TIME = 10;

//    @Override
    public void addDoctor(Doctor doctor) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth());
        Map<Date, List<Time> > dateAndTime = makeDateAndTime(now, doctor.getID());
        reservedDoctors.put(doctor.getID(), dateAndTime);
    }
    /**
     *
     * @param date current date.
     * @return map, where Keys are next 7 days and values each days working times introduce in arraylist.
     */
    private Map<Date, List<Time>> makeDateAndTime(Date date, String doctor) {
        Map<Date, List<Time> > dateAndTime = new HashMap<>();
        List<Date> doctorDates = new ArrayList<>();
        for(int days=0; days < NEXT_DAYS ;days ++) {
            List<Time> dayGraphic = makeDayGraphic();
            Date nextDate = new Date(date.getYear(), date.getMonth(), date.getDate()+days);
            doctorDates.add(nextDate);
            dateAndTime.put(nextDate,dayGraphic);
        }
        eachDoctorWorkingDates.put(doctor, doctorDates);
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

//    @Override
    public void updateBase() {
        updateWorkingTimesMap();
        updateReservedDoctors();
    }

    private void updateReservedDoctors() {
        for(String doctorID : eachDoctorWorkingDates.keySet()) {
            List<Date> doctorDates = eachDoctorWorkingDates.get(doctorID);
            Map<Date, List<Time>> doctorWork = reservedDoctors.get(doctorID);
            Map<Date, List<Time>> tmpDoctorWork = new HashMap<>();
            for(int i=0; i <doctorDates.size();i++) {
                if(!doctorWork.containsKey(doctorDates.get(i))) {
                    List<Time> dayGraphic = makeDayGraphic();
                    tmpDoctorWork.put(doctorDates.get(i), dayGraphic);
                } else {
                    tmpDoctorWork.put(doctorDates.get(i), doctorWork.get(doctorDates.get(i)));
                }
                reservedDoctors.put(doctorID, tmpDoctorWork);
            }
        }
    }

    private void updateWorkingTimesMap() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth());
        for(String doctorID : eachDoctorWorkingDates.keySet()) {
            List<Date> dates = eachDoctorWorkingDates.get(doctorID);
            for(int i = 0; i < dates.size(); i++){
                if(dates.get(i).compareTo(now) < 0) {
                    Date date;
                    if(dates.get(dates.size()-1).compareTo(now) < 0) {
                        date = new Date(now.getYear(), now.getMonth(),now.getDate()+1);
                    } else {
                        date = new Date(now.getYear(), now.getMonth(),dates.get(dates.size()-1).getDate()+1);
                    }
                    dates.remove(i);
                    dates.add(date);
                    i--;
                }
            }
        }
    }

//    private static Map<String, List<Date>> makeCopyOfWorkingDates() {
//        Map<String, List<Date>> returnValue = new HashMap<>();
//        for(String doctorID : eachDoctorWorkingDates.keySet()) {
//            List<Date> tmpDates = eachDoctorWorkingDates.get(doctorID);
//            List<Date> tmpDatesCopy = new ArrayList<>();
//            for(int i = 0; i <tmpDates.size();i++) {
//                Date copyDate = new Date(tmpDates.get(i).getYear(),tmpDates.get(i).getMonth(),tmpDates.get(i).getDate());
//                tmpDatesCopy.add(copyDate);
//            }
//            String copyID = new String();
//            copyID = doctorID;
//            returnValue.put(copyID, tmpDatesCopy);
//        }
//        return returnValue;
//    }
//
//
//    private static Map<String, Map<Date, List<Time>>> makeCopyOfReservedDoctors() {
//        Map<String, Map<Date, List<Time>>> returnValue = new HashMap<>();
//        for(String doctorsId : reservedDoctors.keySet()) {
//            Map<Date, List<Time>> eachDoctorTimes = reservedDoctors.get(doctorsId);
//            Map<Date, List<Time>> eachDoctorTimesCopy = new HashMap<>();
//            for(Date date : eachDoctorTimes.keySet()) {
//                List<Time> times = eachDoctorTimes.get(date);
//                List<Time> timesForCopy = new ArrayList<>();
//                for(int index = 0; index < times.size(); index ++) {
//                    Time time = new Time(times.get(index).getHours(), times.get(index).getMinutes(), times.get(index).getSeconds());
//                    timesForCopy.add(time);
//                }
//                Date dateForCopy = new Date(date.getYear(), date.getMonth(), date.getDate());
//                eachDoctorTimesCopy.put(dateForCopy, timesForCopy);
//            }
//            String doctorsIdCopy = new String();
//            doctorsIdCopy = doctorsId;
//            returnValue.put(doctorsIdCopy, eachDoctorTimesCopy);
//        }
//        return returnValue;
//    }

    public void getToString(Map<String, Map<Date, List<Time>>> returnValue) {
        for(String ID : returnValue.keySet()) {
            Map<Date, List<Time>> tmp = returnValue.get(ID);
            for (Date d : tmp.keySet()) {
                List<Time> tmp2 = tmp.get(d);
                System.out.print(d.toString() + " ");
                for (int i = 0; i < tmp2.size(); i++) {
                    System.out.print(tmp2.get(i) + " ");
                }
                System.out.println("");
            }
        }
    }

//    @Override
    public void reserveDoctor(Doctor doctor, Date date, Time time) {
        Map<Date, List<Time>> doctorWorkingTimes = reservedDoctors.get(doctor.getID());
        List<Time> doctorEachDayTimes = doctorWorkingTimes.get(date);
        doctorEachDayTimes.remove(doctorEachDayTimes.indexOf(time));
    }
}
