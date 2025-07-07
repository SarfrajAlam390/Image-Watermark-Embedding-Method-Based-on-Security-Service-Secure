package visual.security.evaluation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import visual.security.evaluation.bean.Bean;
import visual.security.evaluation.dbconnection.DbConnection;

public class SecurityDAO extends DbConnection {
	Connection con = null;
	ArrayList<Bean> al = new ArrayList<Bean>();
	PreparedStatement ps = null;
	int i = 0;
	public SecurityDAO() {
		con=getConnection();
	}
	
	public ArrayList<Bean> login(Bean b)throws Exception {
		ps = con.prepareStatement("select uid,uname,email from userdetails where email=? and password=? and status='active'");
		ps.setString(1, b.getEmail());
		ps.setString(2, b.getPassword());
		ResultSet rs= ps.executeQuery();
		while(rs.next()) 
		{
			Bean login = new Bean();
			login.setUid(rs.getInt(1));
			login.setUname(rs.getString(2));
			login.setEmail(rs.getString(3));
			al.add(login);
		}
		return al;
	}
	
	public int reg(Bean b)throws Exception {
		ps = con.prepareStatement("insert into userdetails(uname,password,email,dob,gender,address,status)values(?,?,?,?,?,?,'in active')");
		ps.setString(1, b.getUname());
		ps.setString(2, b.getPassword());
		ps.setString(3, b.getEmail());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(sd.parse(b.getDob()).getTime());
		ps.setDate(4, date);
		ps.setString(5, b.getGender());
		ps.setString(6, b.getAddress());
		i=ps.executeUpdate();
		return i;
	}
	
	public int adminAcceptNewUser(int uid)throws Exception {
		ps = con.prepareStatement("update userdetails set status='active' where uid=? and status='in active'");
		ps.setInt(1, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int iibvsiImage(Bean b)throws Exception {
		ps = con.prepareStatement("insert into file(uid,plainimage,encimage,contrastimage,enccontrastimage,ampimage,encampimage,pollimage,percentage)values(?,?,?,?,?,?,?,?,?)");
		ps.setInt(1, b.getUid());
		ps.setBytes(2, b.getPlainImage());
		ps.setBytes(3, b.getEncryptedImage());
		ps.setBytes(4, b.getContrastImage());
		ps.setBytes(5, b.getEncryptcontrastImage());
		ps.setBytes(6, b.getAmplitudeImage());
		ps.setBytes(7, b.getEncryptamplitudeImage());
		ps.setBytes(8, b.getPoolImage());
		ps.setDouble(9, b.getPercentage());
		i=ps.executeUpdate();
		return i;
	}
	
	public int feedBack(Bean b)throws Exception {
		ps = con.prepareStatement("insert into feedback(uname,email,message)values(?,?,?)");
		ps.setString(1, b.getUname());
		ps.setString(2, b.getEmail());
		ps.setString(3, b.getAddress());
		i=ps.executeUpdate();
		return i;
	}
}