package metrology.functionality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date 04.10.2013
 * @author Talanov S.V.
 */
public class MeasurementDevice {

    public static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private String name;            //***Название
    private String mark;            //Марка
    private String type;            //Тип
    private String number;          //***Номер
    private Date dateWasMade;       //Дата изготовления
    private Date dateStartUsing;    //Дата ввода в эксплуатацию
    private String manufacturer;    //Фирма-изготовитель
    private String department;      //***Подразделение
    private String respPerson;      //Ответственный
    private String measRange;       //Диапазон измерений
    private String measDeviation;   //Погрешность прибора
    private String measStep;        //Цена деления
    private int checkRate;          //***Периодичность поверки (калибровки, аттестации)
    private String companyLastCheck;//Компания, производишая последнюю поверку
    private Date dateLastCheck;     //***Дата последней поверки (калибровки, аттестации)
    private Date dateNextCheck;     //Дата следующей поверки (калибровки, аттестации)
    public int quartNextCheck;      ////Квартал следующей поверки
    public int dayNextCheck;        ////День следующей поверки
    public int monthNextCheck;      ////Месяц следующей поверки
    public int yearNextCheck;       ////Год следующей поверки
    private Date dateGetFromDept;   //Дата передачи в поверку (калибровку, аттестацию) из подразделения
    private Date dateReturnToDept;  //Дата передачи прибора из поверки (калибровки, атестации)в позразделение
    private Date dateSentToOut;     //Дата передачи прибора в аутсорсинговую компанию
    private Date dateReturnFromOut; //Дата получения прибора от аутсорсинговой компании
    private String checkDocNumber;  //Номер документа о поверке (калибровке, аттестации)
    private String status;          //Статус прибора
    private String place;           //Место нахождения прибора (в кладовой СИ, в подразделении)
    private int placeNumb;          //Место хранения в кладовой КИП
    private String addStat;         //--Невидимый системный статус (просрочен, скоро в поверку, поверен, не поверен)

    public MeasurementDevice(String name, String number, String department, Date dateLastCheck, int checkRate) {
        this.name = name;
        this.number = number;
        this.department = department;
        this.dateLastCheck = dateLastCheck;
        this.checkRate = checkRate;
        calcDateNextCheck();
    }

    public void calcDateNextCheck() {
//Расчет даты следующей поверки:
        Calendar calendar = Calendar.getInstance();
        if (dateLastCheck != null) {
            calendar.setTime(dateLastCheck);
            calendar.add(Calendar.MONTH, checkRate);
            dateNextCheck = calendar.getTime();
//Расчет квартала следующей поверки:
            int month = calendar.get(Calendar.MONTH);
            if (month <= 2) {
                quartNextCheck = 1;
            } else if (month > 2 && month <= 5) {
                quartNextCheck = 2;
            } else if (month > 5 && month <= 8) {
                quartNextCheck = 3;
            } else {
                quartNextCheck = 4;
            }
//Определение дня следующей поверки:
            dayNextCheck = calendar.get(Calendar.DAY_OF_MONTH);
//Определение месяца следующей поверки:
            monthNextCheck = calendar.get(Calendar.MONTH) + 1;
//Определение года следующей поверки:
            yearNextCheck = calendar.get(Calendar.YEAR);
        }
    }
//Перевод строки в дату:
    public static Date stringToDate(String inputString) {
        Date resultDate = null;
        try {
            if (!"".equals(inputString) &&!"null".equals(inputString) &&!"-".equals(inputString) && inputString != null) {
                resultDate = format.parse(inputString);
            } else {
                //resultDate = format.parse("01.01.1900");
                resultDate = null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(MeasurementDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultDate;
    }
//Перевод даты в строку:
    public static String dateToString(Date inputDate) {
        String resultString = null;
        if (inputDate != null) {
            resultString = format.format(inputDate);
        } else {
            resultString = "-";
        }
        return resultString;
    }

    public void printInfo() {
        System.out.println(name + " / "
                + number + " / " + department + " / " + dateToString(dateLastCheck));
        System.out.println(dateToString(dateNextCheck)
                + " / Day: " + dayNextCheck
                + " / Month: " + monthNextCheck
                + " / Quarter: " + quartNextCheck
                + " / Year: " + yearNextCheck);
    }

    //Getters & Setters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateWasMade() {
        return dateWasMade;
    }

    public void setDateWasMade(Date dateWasMade) {
        this.dateWasMade = dateWasMade;
    }

    public Date getDateStartUsing() {
        return dateStartUsing;
    }

    public void setDateStartUsing(Date dateStartUsing) {
        this.dateStartUsing = dateStartUsing;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRespPerson() {
        return respPerson;
    }

    public void setRespPerson(String respPerson) {
        this.respPerson = respPerson;
    }

    public String getMeasRange() {
        return measRange;
    }

    public void setMeasRange(String measRange) {
        this.measRange = measRange;
    }

    public String getMeasDeviation() {
        return measDeviation;
    }

    public void setMeasDeviation(String measDeviation) {
        this.measDeviation = measDeviation;
    }

    public String getMeasStep() {
        return measStep;
    }

    public void setMeasStep(String measStep) {
        this.measStep = measStep;
    }

    public int getCheckRate() {
        return checkRate;
    }

    public void setCheckRate(int checkRate) {
        this.checkRate = checkRate;
    }

    public String getCompanyLastCheck() {
        return companyLastCheck;
    }

    public void setCompanyLastCheck(String companyLastCheck) {
        this.companyLastCheck = companyLastCheck;
    }

    public Date getDateLastCheck() {
        return dateLastCheck;
    }

    public void setDateLastCheck(Date dateLastCheck) {
        this.dateLastCheck = dateLastCheck;
    }

    public Date getDateNextCheck() {
        return dateNextCheck;
    }

    public void setDateNextCheck(Date dateNextCheck) {
        this.dateNextCheck = dateNextCheck;
    }

    public Date getDateGetFromDept() {
        return dateGetFromDept;
    }

    public void setDateGetFromDept(Date dateGetFromDept) {
        this.dateGetFromDept = dateGetFromDept;
    }

    public Date getDateReturnToDept() {
        return dateReturnToDept;
    }

    public void setDateReturnToDept(Date dateReturnToDept) {
        this.dateReturnToDept = dateReturnToDept;
    }

    public Date getDateSentToOut() {
        return dateSentToOut;
    }

    public void setDateSentToOut(Date dateSentToOut) {
        this.dateSentToOut = dateSentToOut;
    }

    public Date getDateReturnFromOut() {
        return dateReturnFromOut;
    }

    public void setDateReturnFromOut(Date dateReturnFromOut) {
        this.dateReturnFromOut = dateReturnFromOut;
    }

    public String getCheckDocNumber() {
        return checkDocNumber;
    }

    public void setCheckDocNumber(String checkDocNumber) {
        this.checkDocNumber = checkDocNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPlaceNumb() {
        return placeNumb;
    }

    public void setPlaceNumb(int placeNumb) {
        this.placeNumb = placeNumb;
    }

    public String getAddStat() {
        return addStat;
    }

    public void setAddStat(String addStat) {
        this.addStat = addStat;
    }

    public int getQuartNextCheck() {
        return quartNextCheck;
    }

    public int getDayNextCheck() {
        return dayNextCheck;
    }

    public int getMonthNextCheck() {
        return monthNextCheck;
    }

    public int getYearNextCheck() {
        return yearNextCheck;
    }
    
    
    
}
