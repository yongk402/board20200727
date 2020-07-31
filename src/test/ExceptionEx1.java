package test;

import java.sql.SQLException;
import java.sql.SQLTransientException;

public class ExceptionEx1 {
	public void main() {
		A a = new B();
		try {
			a.method();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		B b = new B();
//		b.method();
	}
}

class B implements A {
	public void method() throws SQLTransientException{ 
//implement의 익셉션보다 하위 클래스의 익셉션만 발생 시킬 수 있다.
	}
}
interface A {
	void method() throws SQLException;
}
