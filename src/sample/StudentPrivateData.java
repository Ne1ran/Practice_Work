package sample;

public class StudentPrivateData {
    private String id;
    private String fio;
    private String birthday;
    private String birthPlace;
    private String passportData;
    private String birthInfo;

    public StudentPrivateData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getBirthInfo() {
        return birthInfo;
    }

    public void setBirthInfo(String birthInfo) {
        this.birthInfo = birthInfo;
    }
}
