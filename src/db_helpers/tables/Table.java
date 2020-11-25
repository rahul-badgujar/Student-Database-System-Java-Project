package db_helpers.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import data_models.Model;

public interface Table {

    public Statement createTable() throws ClassNotFoundException, SQLException;

    public PreparedStatement insertRecord(Model model) throws ClassNotFoundException, SQLException;

    public List<Model> queryAllRecords() throws ClassNotFoundException, SQLException;

    public boolean checkRecordExistance(Model model) throws SQLException, ClassNotFoundException;
}
