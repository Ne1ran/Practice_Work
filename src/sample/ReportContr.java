package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Controller.handler;

public class ReportContr {

    @FXML
    private Button closeBtn;

    @FXML
    private TableView<ReportData> reportTable;

    @FXML void initialize() throws SQLException, ClassNotFoundException {
        loadReportTable();
        reportTable.setItems(getReportData());
        closeBtn.setOnAction(Event ->{
            closeBtn.getScene().getWindow().hide();
        });
    }

    public void loadReportTable(){
        TableColumn<ReportData, String> id = new TableColumn<>("ID");
        TableColumn<ReportData, String> fio = new TableColumn<>("ФИО");
        TableColumn<ReportData, String> birthday = new TableColumn<>("Дата рождения");
        TableColumn<ReportData, String> phone = new TableColumn<>("Номер телефона");
        TableColumn<ReportData, String> score = new TableColumn<>("Средний балл аттестата");
        TableColumn<ReportData, String> speciality = new TableColumn<>("Выбранная специальность");

        reportTable.getColumns().addAll(id, fio, birthday, phone, score, speciality);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        speciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
    }

    private ObservableList<ReportData> getReportData() throws SQLException, ClassNotFoundException {
        ObservableList<ReportData> list = FXCollections.observableArrayList();
        ResultSet studentDataSet = handler.getData();

        while (studentDataSet.next()){
            ReportData data = new ReportData();
            data.setId(studentDataSet.getString(AllConstants.DataConsts.ID));
            ResultSet studentEduSet = handler.getEduDataWhere(data.getId());
            data.setFio(studentDataSet.getString(AllConstants.DataConsts.FIO));
            data.setBirthday(studentDataSet.getString(AllConstants.DataConsts.BIRTHDAY));
            data.setPhone(studentDataSet.getString(AllConstants.DataConsts.NUMBER));
            if (studentEduSet.next()){
                data.setScore(studentEduSet.getString(AllConstants.EducationConsts.ATTESTATE_SCORE));
                data.setSpeciality(studentEduSet.getString(AllConstants.EducationConsts.SPECIALITY_INFO));
            } else {
                data.setScore("No data");
                data.setSpeciality("No data");
            }
            list.add(data);
        }
        return list;
    }
}
