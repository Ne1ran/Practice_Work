package sample;

import java.sql.*;

public class DBHandler extends Config {
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + Host + ":" + Port + "/" + Name;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connStr, User, Password);
        return connection;
    }

    public ResultSet getData() throws SQLException, ClassNotFoundException {
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.DataConsts.DATA_TABLE;
        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();
        return rset;
    }

    public ResultSet getPrivateData() throws SQLException, ClassNotFoundException {
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.PrivateDataConsts.PRIVATE_DATA_TABLE;
        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();
        return rset;
    }

    public ResultSet getEducationData() throws SQLException, ClassNotFoundException {
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.EducationConsts.EDUCATION_TABLE;
        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();
        return rset;
    }

    public ResultSet getParentsData() throws SQLException, ClassNotFoundException {
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.ParentsConsts.PARENTS_TABLE;
        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();
        return rset;
    }

    public void addNewData(StudentData data) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + AllConstants.DataConsts.DATA_TABLE + "(" + AllConstants.DataConsts.FIO + ',' +
                AllConstants.DataConsts.BIRTHDAY + ',' + AllConstants.DataConsts.ADDRESS + ',' +
                AllConstants.DataConsts.NUMBER + ')' + " VALUES(?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, data.getFio());
        prst.setString(2, data.getBirthday());
        prst.setString(3, data.getAddress());
        prst.setString(4, data.getPhone());
        prst.executeUpdate();
    }

    public void addNewPrivateData(StudentPrivateData data) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + AllConstants.PrivateDataConsts.PRIVATE_DATA_TABLE + "(" +
                AllConstants.PrivateDataConsts.FIO + ',' + AllConstants.PrivateDataConsts.BIRTHDAY + ',' +
                AllConstants.PrivateDataConsts.BIRTH_PLACE + ',' + AllConstants.PrivateDataConsts.PASSPORT + ',' +
                AllConstants.PrivateDataConsts.BIRTH_INFO + ')' + " VALUES(?,?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, data.getFio());
        prst.setString(2, data.getBirthday());
        prst.setString(3, data.getBirthPlace());
        prst.setString(4, data.getPassportData());
        prst.setString(5, data.getBirthInfo());
        prst.executeUpdate();
    }

    public void addNewEducationData(Education data) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + AllConstants.EducationConsts.EDUCATION_TABLE + "(" +
                AllConstants.EducationConsts.EDU_LEVEL + ',' + AllConstants.EducationConsts.ATTESTATE_NUMBER + ',' +
                AllConstants.EducationConsts.ATTESTATE_SCORE + ',' + AllConstants.EducationConsts.SPECIALITY_INFO + ')'
                + " VALUES(?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, data.getLevel());
        prst.setString(2, data.getAttestateNumber());
        prst.setString(3, data.getAttestateScore());
        prst.setString(4, data.getSpecialityInfo());
        prst.executeUpdate();
    }

    public void addNewParentsData(Parents data) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + AllConstants.ParentsConsts.PARENTS_TABLE + "(" +
                AllConstants.ParentsConsts.FAMILY_STATUS + ',' + AllConstants.ParentsConsts.CHILDREN + ',' +
                AllConstants.ParentsConsts.MOTHER_FIO + ',' + AllConstants.ParentsConsts.MOTHER_ADDRESS + ',' +
                AllConstants.ParentsConsts.MOTHER_NUMBER + ',' + AllConstants.ParentsConsts.MOTHER_WORKPLACE + ',' +
                AllConstants.ParentsConsts.FATHER_FIO + ',' + AllConstants.ParentsConsts.FATHER_ADDRESS + ',' +
                AllConstants.ParentsConsts.FATHER_NUMBER + ',' + AllConstants.ParentsConsts.FATHER_WORKPLACE
                + ')' + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, data.getFamilyStatus());
        prst.setString(2, data.getChildren());
        prst.setString(3, data.getMotherFio());
        prst.setString(4, data.getMotherAddress());
        prst.setString(5, data.getMotherNumber());
        prst.setString(6, data.getMotherWorkplace());
        prst.setString(7, data.getFatherFio());
        prst.setString(8, data.getFatherAddress());
        prst.setString(9, data.getFatherNumber());
        prst.setString(10, data.getFatherWorkplace());
        prst.executeUpdate();
    }

    public void changeData(String id, StudentData data) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + AllConstants.DataConsts.DATA_TABLE + " SET " + AllConstants.DataConsts.FIO + "='" +
                data.getFio() + "', " + AllConstants.DataConsts.BIRTHDAY + "='" + data.getBirthday() + "', " +
                AllConstants.DataConsts.ADDRESS + "='" + data.getAddress() + "', " +
                AllConstants.DataConsts.NUMBER + "='" + data.getPhone() + "' WHERE (" + AllConstants.DataConsts.ID
                + "=" + id + ")";
        System.out.println(update);
        PreparedStatement prst = getConnection().prepareStatement(update);
        prst.executeUpdate();
    }
}