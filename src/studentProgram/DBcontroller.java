package studentProgram;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBcontroller {
	//���̺� �𵨰�ü ���� ��������Լ�(DB���Ա��) 
	public static int insertStudent(StudentModel studentModel) {
		int returnValue=0;  	//�����ͺ��̽� ��ɹ� ���ϰ�
		//�����ͺ��̽� ���ӿ�û
		Connection con=DButility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return 0;
		}
		//���Ը�ɹ� 
		String insertQuery="insert into studentdb.studenttbl values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=(PreparedStatement) con.prepareStatement(insertQuery);
			ps.setString(1, studentModel.getName());
			ps.setString(2, studentModel.getId());
			ps.setString(3, studentModel.getHp());
			ps.setInt(4, studentModel.getKor());
			ps.setInt(5, studentModel.getEng());
			ps.setInt(6, studentModel.getMath());
			ps.setInt(7, studentModel.getTotal());
			ps.setDouble(8, studentModel.getAvr());
			ps.setString(9, studentModel.getGrade());
			
			returnValue=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//����� ����
		return returnValue;		
	}
	//���̺� �𵨰�ü ��� ��������Լ�(DB���Ա��) 
	public static List<StudentModel> printstudent() {
		//���̺��ִ� ���ڵ� ���� �������� ���� ArrayList<PhoneBookModel>
		List<StudentModel> list=new ArrayList<StudentModel>();
		//�����ͺ��̽� ���ӿ�û
		Connection con=DButility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return null;
		}
		//DB��ɹ�
		String selectQuery="select*from studentdb.studenttbl";
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps=(PreparedStatement) con.prepareStatement(selectQuery);
			resultSet=ps.executeQuery(); 
			while(resultSet.next()) {
				String name=resultSet.getString(1);
				String id=resultSet.getString(2);
				String hp=resultSet.getString(3);
				int kor=resultSet.getInt(4);
				int eng=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int total=resultSet.getInt(7);
				Double avr=resultSet.getDouble(8);
				String grade=resultSet.getString(9);
				
				StudentModel studentModel=new StudentModel(name, id, hp, kor, eng, math,total,avr,grade);
				list.add(studentModel);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//���̺� �𵨰�ü �˻� ��������Լ�(�̸�) 
	public static List<StudentModel> searchstudent(String searchData, int number){
		final int S_NAME=1, S_EXIT=2;
		//���ڵ� ���� ������ List Collection
		List<StudentModel> list=new ArrayList<StudentModel>();
		//�����ͺ��̽� ���ӿ�û
		Connection con=DButility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return null;
		}
		//DB��ɹ�
		String searchQuary=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		switch(number) {
		case S_NAME:
			searchQuary="select*from studentdb.studenttbl where name like ?";
			break;
		}		
		try {
			ps=(PreparedStatement) con.prepareStatement(searchQuary);
			searchData="%"+searchData+"%";
			ps.setString(1, searchData);
			resultSet=ps.executeQuery();
			while(resultSet.next()) {
				String name=resultSet.getString(1);
				String id=resultSet.getString(2);
				String hp=resultSet.getString(3);
				int kor=resultSet.getInt(4);
				int eng=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int total=resultSet.getInt(7);
				Double avr=resultSet.getDouble(8);
				String grade=resultSet.getString(9);
				
				StudentModel studentModel=new StudentModel(name, id, hp, kor, math, eng, total, avr, grade);
				list.add(studentModel);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	//���̺� �𵨰�ü ���� ��������Լ�(DB���Ա��) 
	public static int deletestudent(String deleteData, int number) {
		final int S_NAME=1;
		//DB���ӿ�û
		Connection con=DButility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return 0;
		}
		String deleteQuery=null;
		PreparedStatement ps=null;
		int resultNumber=0;
		//DB��ɹ�
		switch(number) {
		case S_NAME:
			deleteQuery="delete from studentdb.studenttbl where name like ?";
			break;
		}	
		try {
			ps=(PreparedStatement) con.prepareStatement(deleteQuery);
			deleteData="%"+deleteData+"%";
			ps.setString(1, deleteData);
			resultNumber=ps.executeUpdate();		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultNumber;
	}

	//���̺� �𵨰�ü ���� ��������Լ�(DB���Ա��) 
	public static int updatestudent(StudentModel studentModel) {
		Connection con=DButility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return 0;
		}
		String updateQuery=null;
		PreparedStatement ps=null;
		int resultNumber=0;
		
		updateQuery="update studentdb.studenttbl set kor=?,eng=?,math=?,total=?,avr=?,grade=? where hp=?";
			
		try {
			ps=(PreparedStatement) con.prepareStatement(updateQuery);	
			ps.setInt(1, studentModel.getKor());
			ps.setInt(2, studentModel.getEng());
			ps.setInt(3, studentModel.getMath());
			ps.setInt(4, studentModel.getTotal());
			ps.setDouble(5, studentModel.getAvr());
			ps.setString(6, studentModel.getGrade());
			ps.setString(7, studentModel.getHp());		
			resultNumber=ps.executeUpdate();		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultNumber;
	}

	public static List<StudentModel> sortstudent(int no) {
		final int ASC=1, DESC=2;
		//���̺��ִ� ���ڵ� ���� �������� ���� ArrayList<PhoneBookModel>
		List<StudentModel> list=new ArrayList<StudentModel>();
		String sortQuery=null;
		//�����ͺ��̽� ���ӿ�û
		Connection con=DButility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return null;
		}	
		//DB��ɹ�
		switch(no) {
		case ASC:
			sortQuery="select*from studentdb.studenttbl order by avr asc";
			break;
		case DESC:
			sortQuery="select*from studentdb.studenttbl order by avr desc";
			break;
		}
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps=(PreparedStatement) con.prepareStatement(sortQuery);
			resultSet=ps.executeQuery(); 
			
			while(resultSet.next()) {
				String name=resultSet.getString(1);
				String id=resultSet.getString(2);
				String hp=resultSet.getString(3);
				int kor=resultSet.getInt(4);
				int eng=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int total=resultSet.getInt(7);
				Double avr=resultSet.getDouble(8);
				String grade=resultSet.getString(9);
				
				StudentModel studentModel=new StudentModel(name, id, hp, kor, eng, math,total,avr,grade);
				list.add(studentModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
}

