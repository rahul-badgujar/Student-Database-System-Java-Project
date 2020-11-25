package data_models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Model {
    public PreparedStatement toSqlInsertStatement(Connection connection) throws SQLException, ClassNotFoundException;

}
