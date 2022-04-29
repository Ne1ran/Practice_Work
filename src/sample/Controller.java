package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Button addDataBtn;

    @FXML
    private Button addEducationBtn;

    @FXML
    private Button addParentsDataBtn;

    @FXML
    private Button addPrivateDataBtn;

    @FXML
    private Button changeDataBtn;

    @FXML
    private Button changeEducationBtn;

    @FXML
    private Button changeParentsDataBtn;

    @FXML
    private Button changePrivateDataBtn;

    @FXML
    private Button createReport;

    @FXML
    private TextField dataAddress;

    @FXML
    private TextField dataBirthday;

    @FXML
    private TextField dataFIO;

    @FXML
    private TextField dataPhone;

    @FXML
    private TextField eduAttestateNum;

    @FXML
    private TextField eduAttestateScore;

    @FXML
    private TextField eduAttestateSpeciality;

    @FXML
    private TextField eduLevel;

    @FXML
    private TextField familyChildren;

    @FXML
    private TextField familyFatherAddress;

    @FXML
    private TextField familyFatherFIO;

    @FXML
    private TextField familyFatherNumber;

    @FXML
    private TextField familyFatherWorkplace;

    @FXML
    private TextField familyMotherAddress;

    @FXML
    private TextField familyMotherFIO;

    @FXML
    private TextField familyMotherNumber;

    @FXML
    private TextField familyMotherWorkplace;

    @FXML
    private TextField parentsFamilyStatus;

    @FXML
    private TextField pdataBirthInfo;

    @FXML
    private TextField pdataBirthPlace;

    @FXML
    private TextField pdataBirthday;

    @FXML
    private TextField pdataFIO;

    @FXML
    private TextField pdataPassport;

    @FXML
    private TableView<Education> educationTable;

    @FXML
    private TableView<StudentData> normalDataTable;

    @FXML
    private TableView<Parents> parentsTable;

    @FXML
    private TableView<StudentPrivateData> privateDataTable;

    @FXML
    private Label labelInfo;

    DBHandler handler = new DBHandler();
    public static String IDchoosen = "";

    @FXML void initialize() throws SQLException, ClassNotFoundException {
        loadTables();

        setRowFactoriesOnTableViews();

        addDataBtn.setOnAction(Event ->{
            try {
                handler.addNewData(getDataFromTFields());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                normalDataTable.setItems(getData());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        addPrivateDataBtn.setOnAction(Event ->{
            try {
                handler.addNewPrivateData(getPrivateDataFromTFields());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                privateDataTable.setItems(getPrivateData());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        addEducationBtn.setOnAction(Event ->{
            try {
                handler.addNewEducationData(getEduDataFromTFields());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                educationTable.setItems(getEducationData());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        addParentsDataBtn.setOnAction(Event ->{
            try {
                handler.addNewParentsData(getParentsDataFromTFields());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                parentsTable.setItems(getParentsData());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        changeDataBtn.setOnAction(Event ->{
            if (!IDchoosen.equals("")){
                try {
                    handler.changeData(IDchoosen, getDataFromTFields());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    normalDataTable.setItems(getData());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            } else labelInfo.setText("Вы не выбрали строку для изменения!");
        });
    }

    public void loadTables() throws SQLException, ClassNotFoundException {
        loadDataTable();
        loadPrivateDataTable();
        loadEducationTable();
        loadParentsTable();

        normalDataTable.setItems(getData());
        privateDataTable.setItems(getPrivateData());
        educationTable.setItems(getEducationData());
        parentsTable.setItems(getParentsData());
    }

    public void loadDataTable(){
        TableColumn<StudentData, String> id = new TableColumn<>("ID");
        TableColumn<StudentData, String> fio = new TableColumn<>("ФИО");
        TableColumn<StudentData, String> birthday = new TableColumn<>("Дата рождения");
        TableColumn<StudentData, String> address = new TableColumn<>("Адрес проживания");
        TableColumn<StudentData, String> number = new TableColumn<>("Номер телефона");

        normalDataTable.getColumns().addAll(id, fio, birthday, address, number);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        number.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    public void loadPrivateDataTable(){
        TableColumn<StudentPrivateData, String> id = new TableColumn<>("ID");
        TableColumn<StudentPrivateData, String> fio = new TableColumn<>("ФИО");
        TableColumn<StudentPrivateData, String> birthday = new TableColumn<>("Дата рождения");
        TableColumn<StudentPrivateData, String> birthPlace = new TableColumn<>("Место рождения");
        TableColumn<StudentPrivateData, String> passportData = new TableColumn<>("Паспортные данные");
        TableColumn<StudentPrivateData, String> birthInfo = new TableColumn<>("Свидетельство о рождении");

        privateDataTable.getColumns().addAll(id, fio, birthday, birthPlace, passportData, birthInfo);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        passportData.setCellValueFactory(new PropertyValueFactory<>("passportData"));
        birthInfo.setCellValueFactory(new PropertyValueFactory<>("birthInfo"));
    }

    public void loadEducationTable(){
        TableColumn<Education, String> id = new TableColumn<>("ID");
        TableColumn<Education, String> level = new TableColumn<>("Прошлое образование");
        TableColumn<Education, String> attestateNumber = new TableColumn<>("Номер аттестата");
        TableColumn<Education, String> attestateScore = new TableColumn<>("Средний балл аттестата");
        TableColumn<Education, String> specialityInfo = new TableColumn<>("Информация о специальности");

        educationTable.getColumns().addAll(id, level, attestateNumber, attestateScore, specialityInfo);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        attestateNumber.setCellValueFactory(new PropertyValueFactory<>("attestateNumber"));
        attestateScore.setCellValueFactory(new PropertyValueFactory<>("attestateScore"));
        specialityInfo.setCellValueFactory(new PropertyValueFactory<>("specialityInfo"));
    }

    public void loadParentsTable(){
        TableColumn<Parents, String> id = new TableColumn<>("ID");
        TableColumn<Parents, String> familyStatus = new TableColumn<>("Статус семьи");
        TableColumn<Parents, String> children = new TableColumn<>("Количество детей");
        TableColumn<Parents, String> motherFio = new TableColumn<>("ФИО матери");
        TableColumn<Parents, String> motherAddress = new TableColumn<>("Адрес матери");
        TableColumn<Parents, String> motherNumber = new TableColumn<>("Номер матери");
        TableColumn<Parents, String> motherWorkplace = new TableColumn<>("Место работы матери");
        TableColumn<Parents, String> fatherFio = new TableColumn<>("ФИО отца");
        TableColumn<Parents, String> fatherAddress = new TableColumn<>("Адрес отца");
        TableColumn<Parents, String> fatherNumber = new TableColumn<>("Номер отца");
        TableColumn<Parents, String> fatherWorkplace = new TableColumn<>("Место работы отца");

        parentsTable.getColumns().addAll(id, familyStatus, children, motherFio, motherAddress, motherNumber,
                motherWorkplace, fatherFio, fatherAddress, fatherNumber, fatherWorkplace);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        familyStatus.setCellValueFactory(new PropertyValueFactory<>("familyStatus"));
        children.setCellValueFactory(new PropertyValueFactory<>("children"));
        motherFio.setCellValueFactory(new PropertyValueFactory<>("motherFio"));
        motherAddress.setCellValueFactory(new PropertyValueFactory<>("motherAddress"));
        motherNumber.setCellValueFactory(new PropertyValueFactory<>("motherNumber"));
        motherWorkplace.setCellValueFactory(new PropertyValueFactory<>("motherWorkplace"));
        fatherFio.setCellValueFactory(new PropertyValueFactory<>("fatherFio"));
        fatherAddress.setCellValueFactory(new PropertyValueFactory<>("fatherAddress"));
        fatherNumber.setCellValueFactory(new PropertyValueFactory<>("fatherNumber"));
        fatherWorkplace.setCellValueFactory(new PropertyValueFactory<>("fatherWorkplace"));
    }

    private ObservableList<StudentData> getData() throws SQLException, ClassNotFoundException {
        ObservableList<StudentData> list = FXCollections.observableArrayList();
        ResultSet studentDataSet = handler.getData();

        while (studentDataSet.next()){
            StudentData data = new StudentData();
            data.setId(studentDataSet.getString(AllConstants.DataConsts.ID));
            data.setFio(studentDataSet.getString(AllConstants.DataConsts.FIO));
            data.setAddress(studentDataSet.getString(AllConstants.DataConsts.ADDRESS));
            data.setBirthday(studentDataSet.getString(AllConstants.DataConsts.BIRTHDAY));
            data.setPhone(studentDataSet.getString(AllConstants.DataConsts.NUMBER));
            list.add(data);
        }

        return list;
    }

    private ObservableList<StudentPrivateData> getPrivateData() throws SQLException, ClassNotFoundException {
        ObservableList<StudentPrivateData> list = FXCollections.observableArrayList();
        ResultSet studentPrivateDataSet = handler.getPrivateData();

        while (studentPrivateDataSet.next()){
            StudentPrivateData data = new StudentPrivateData();
            data.setId(studentPrivateDataSet.getString(AllConstants.PrivateDataConsts.ID));
            data.setFio(studentPrivateDataSet.getString(AllConstants.PrivateDataConsts.FIO));
            data.setBirthPlace(studentPrivateDataSet.getString(AllConstants.PrivateDataConsts.BIRTH_PLACE));
            data.setBirthday(studentPrivateDataSet.getString(AllConstants.PrivateDataConsts.BIRTHDAY));
            data.setBirthInfo(studentPrivateDataSet.getString(AllConstants.PrivateDataConsts.BIRTH_INFO));
            data.setPassportData(studentPrivateDataSet.getString(AllConstants.PrivateDataConsts.PASSPORT));
            list.add(data);
        }

        return list;
    }

    private ObservableList<Education> getEducationData() throws SQLException, ClassNotFoundException {
        ObservableList<Education> list = FXCollections.observableArrayList();
        ResultSet educationDataSet = handler.getEducationData();

        while (educationDataSet.next()){
            Education data = new Education();
            data.setId(educationDataSet.getString(AllConstants.EducationConsts.ID));
            data.setLevel(educationDataSet.getString(AllConstants.EducationConsts.EDU_LEVEL));
            data.setAttestateNumber(educationDataSet.getString(AllConstants.EducationConsts.ATTESTATE_NUMBER));
            data.setAttestateScore(educationDataSet.getString(AllConstants.EducationConsts.ATTESTATE_SCORE));
            data.setSpecialityInfo(educationDataSet.getString(AllConstants.EducationConsts.SPECIALITY_INFO));
            list.add(data);
        }

        return list;
    }

    private ObservableList<Parents> getParentsData() throws SQLException, ClassNotFoundException {
        ObservableList<Parents> list = FXCollections.observableArrayList();
        ResultSet parentsDataSet = handler.getParentsData();

        while (parentsDataSet.next()){
            Parents data = new Parents();
            data.setId(parentsDataSet.getString(AllConstants.ParentsConsts.ID));
            data.setFamilyStatus(parentsDataSet.getString(AllConstants.ParentsConsts.FAMILY_STATUS));
            data.setChildren(parentsDataSet.getString(AllConstants.ParentsConsts.CHILDREN));
            data.setMotherFio(parentsDataSet.getString(AllConstants.ParentsConsts.MOTHER_FIO));
            data.setMotherAddress(parentsDataSet.getString(AllConstants.ParentsConsts.MOTHER_ADDRESS));
            data.setMotherNumber(parentsDataSet.getString(AllConstants.ParentsConsts.MOTHER_NUMBER));
            data.setMotherWorkplace(parentsDataSet.getString(AllConstants.ParentsConsts.MOTHER_WORKPLACE));
            data.setFatherFio(parentsDataSet.getString(AllConstants.ParentsConsts.FATHER_FIO));
            data.setFatherAddress(parentsDataSet.getString(AllConstants.ParentsConsts.FATHER_ADDRESS));
            data.setFatherNumber(parentsDataSet.getString(AllConstants.ParentsConsts.FATHER_NUMBER));
            data.setFatherWorkplace(parentsDataSet.getString(AllConstants.ParentsConsts.FATHER_WORKPLACE));
            list.add(data);
        }

        return list;
    }

    private void setRowFactoriesOnTableViews(){
        parentsTable.setRowFactory(tv ->{
            TableRow<Parents> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if (!row.isEmpty()){
                    IDchoosen = row.getItem().getId();
                }
            });
            return row;
        });

        educationTable.setRowFactory(tv ->{
            TableRow<Education> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if (!row.isEmpty()){
                    IDchoosen = row.getItem().getId();
                }
            });
            return row;
        });

        normalDataTable.setRowFactory(tv ->{
            TableRow<StudentData> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if (!row.isEmpty()){
                    IDchoosen = row.getItem().getId();
                }
            });
            return row;
        });

        privateDataTable.setRowFactory(tv ->{
            TableRow<StudentPrivateData> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if (!row.isEmpty()){
                    IDchoosen = row.getItem().getId();
                }
            });
            return row;
        });
    }

    private StudentData getDataFromTFields(){
        StudentData temp = new StudentData();
        temp.setFio(dataFIO.getText().trim());
        temp.setPhone(dataPhone.getText().trim());
        temp.setBirthday(dataBirthday.getText().trim());
        temp.setAddress(dataAddress.getText().trim());
        return temp;
    }

    private StudentPrivateData getPrivateDataFromTFields(){
        StudentPrivateData temp = new StudentPrivateData();
        temp.setFio(pdataFIO.getText().trim());
        temp.setBirthday(pdataBirthday.getText().trim());
        temp.setPassportData(pdataPassport.getText().trim());
        temp.setBirthInfo(pdataBirthInfo.getText().trim());
        temp.setBirthPlace(pdataBirthPlace.getText().trim());
        return temp;
    }

    private Education getEduDataFromTFields(){
        Education temp = new Education();
        temp.setLevel(eduLevel.getText().trim());
        temp.setSpecialityInfo(eduAttestateSpeciality.getText().trim());
        temp.setAttestateNumber(eduAttestateNum.getText().trim());
        temp.setAttestateScore(eduAttestateScore.getText().trim());
        return temp;
    }

    private Parents getParentsDataFromTFields(){
        Parents temp = new Parents();
        temp.setFamilyStatus(parentsFamilyStatus.getText().trim());
        temp.setChildren(familyChildren.getText().trim());
        temp.setMotherFio(familyMotherFIO.getText().trim());
        temp.setMotherAddress(familyMotherAddress.getText().trim());
        temp.setMotherNumber(familyMotherNumber.getText().trim());
        temp.setMotherWorkplace(familyMotherWorkplace.getText().trim());
        temp.setFatherFio(familyFatherFIO.getText().trim());
        temp.setFatherAddress(familyFatherAddress.getText().trim());
        temp.setFatherNumber(familyFatherNumber.getText().trim());
        temp.setFatherWorkplace(familyFatherWorkplace.getText().trim());
        return temp;
    }
}
