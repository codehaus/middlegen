package org.codehaus.rdbms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection Factory that uses plain JDBC to obtain a {@link Connection}.
 *
 * @author Aslak Hellesøy
 */
public class JDBCConnectionFactory implements ConnectionFactory {
	private String driver;
	private String url;
	private String username;
	private String password;

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Connection getConnection() throws org.codehaus.rdbms.jdbc.DatabaseException {
		try {
			getClass().getClassLoader().loadClass(driver).newInstance();
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (ClassNotFoundException e) {
			throw new DatabaseException("Couldn't load JDBC driver " + driver + ". Make sure it's on your classpath.");
		} catch (InstantiationException e) {
			throw new DatabaseException("Couldn't instantiate JDBC driver " + driver + ". That's pretty bad news for your driver.");
		} catch (IllegalAccessException e) {
			throw new DatabaseException("Couldn't instantiate JDBC driver " + driver + ". That's pretty bad news for your driver.");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't connect to database: " + e.getMessage());
		}
	}
}
