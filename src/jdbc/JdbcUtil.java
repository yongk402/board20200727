package jdbc;

import java.sql.Connection;
import java.sql.Statement;

public class JdbcUtil { // p435
	public static void close(AutoCloseable... resource) {
		try {
			for (AutoCloseable res : resource) {
				res.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
