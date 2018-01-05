package com.expocalendar.project.persistence.abstraction.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataSourceManager {
    Connection createConnection() throws SQLException;
}
