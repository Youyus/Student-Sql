import java.sql.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import com.bjsxt.www.DB;

public class Student {

//void showInfoById(int id)
//输入id号，输出username，gender及该id用户的所有科目成绩；
	void showInfoById(int id) {
		Connection conn = DB.getConn();
		Statement stmt = DB.createStmt(conn);
		ResultSet rs_info = null;
		ResultSet rs_score = null;
		String sql_info = "select * from stu_info where id = " + id;
		String sql_score = "select * from stu_score where username = " 
				+ "(select username from stu_info where id = " + id + ")";
		try {
			rs_info = stmt.executeQuery(sql_info);
			while(rs_info.next()) {
				System.out.print(rs_info.getString("id") + "    " +rs_info.getString("username") + "    "
						+ rs_info.getString("gender"));				
			}
			
			rs_score = stmt.executeQuery(sql_score);
			while(rs_score.next()) {
				for(int i=2; i <= 5; i++) {
					System.out.print( "    " + rs_score.getString(i));				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs_info.close();
				rs_score.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static float getAvgById (int id) {
		float avg = 0;
		Connection conn = DB.getConn();
		Statement stmt = DB.createStmt(conn);
		ResultSet rs = null;
		String sql = "select * from stu_score where username = " 
				+ "(select username from stu_info where id = " + id + ")";
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				for(int i = 2; i <= 5; i++) {
					avg += rs.getInt(i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avg/4;
	}
	
//按平均分排序并打印出排序结果（仅适用于表元素为10情况下）
	void sortAvg() {
		Connection conn = DB.getConn();
		Statement stmt = DB.createStmt(conn);
		ResultSet rs = null;
		for (int id = 1; id <= 10; id ++) {
			String sql = 
					"insert into stu_score_avg values (" 
					+ id +", "
					+ "(select username from stu_info where id = " +id + ")"  +", "
					+ Student.getAvgById(id) + ")" ;
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String sql = "select * from stu_score_avg order by score_avg desc";
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				for( int i = 1; i <= 3; i++) {
					System.out.print(rs.getString(i) + "    " );
				} 
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Student st = new Student();
		System.out.println("学生1信息:");
		st.showInfoById(1);//打印出学号为1学生的信息成绩
		
		System.out.println();
		//System.out.println(Student.getAvgById(1));
		System.out.println("按平均成绩降序排名：");
		st.sortAvg();//按平均成绩排名并打印
	}
}
