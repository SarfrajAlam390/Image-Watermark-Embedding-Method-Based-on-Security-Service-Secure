package visual.security.evaluation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import visual.security.evaluation.bean.Bean;
import visual.security.evaluation.dbconnection.DbConnection;

public class ViewDAO extends DbConnection {
	Connection con = null;
	PreparedStatement ps = null;
	ArrayList<Bean> al = new ArrayList<Bean>();
	public ViewDAO() {
		con=getConnection();
	}
	public ArrayList<Bean> adminAcceptNewUser()throws Exception {
		ps = con.prepareStatement("select uid,uname,email,dob,address from userdetails where status='in active'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			b.setUid(rs.getInt(1));
			b.setUname(rs.getString(2));
			b.setEmail(rs.getString(3));
			b.setDob(rs.getString(4));
			b.setAddress(rs.getString(5));
			al.add(b);
		}
		return al;
	}
	public ArrayList<Bean> adminViewUsers()throws Exception {
		ps = con.prepareStatement("select uid,uname,email,dob,address,gender from userdetails where status='active'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			b.setUid(rs.getInt(1));
			b.setUname(rs.getString(2));
			b.setEmail(rs.getString(3));
			b.setDob(rs.getString(4));
			b.setAddress(rs.getString(5));
			b.setGender(rs.getString(6));
			al.add(b);
		}
		return al;
	}
	public ArrayList<Bean> userViewUploadedFiles(int uid)throws Exception {
		ps = con.prepareStatement("select fid,percentage from file where uid=?");
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			b.setUid(rs.getInt(1));
			b.setPercentage(rs.getDouble(2));
			al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> userViewImages(int fid,int uid)throws Exception {
		ps = con.prepareStatement("select plainimage,contrastimage,enccontrastimage,ampimage,encampimage from file where uid=? and fid=?");
		ps.setInt(1, uid);
		ps.setInt(2, fid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			b.setPlainImage(rs.getBytes(1));
			b.setEncryptedImage(rs.getBytes(1));
			b.setContrastImage(rs.getBytes(2));
			b.setEncryptcontrastImage(rs.getBytes(3));
			b.setAmplitudeImage(rs.getBytes(4));
			b.setEncryptamplitudeImage(rs.getBytes(5));
			al.add(b);
		}
		return al;
	}
	public ArrayList<Bean> adminViewUploadedFiles()throws Exception {
		ps = con.prepareStatement("select uid,fid,percentage from file");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			b.setUid(rs.getInt(1));
			b.setFid(rs.getInt(2));
			b.setPercentage(rs.getDouble(3));
			al.add(b);
		}
		return al;
	}
	public ArrayList<Bean> adminViewFeedback()throws Exception {
		ps = con.prepareStatement("select fid,uname,email,message from feedback");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			b.setFid(rs.getInt(1));
			b.setUname(rs.getString(2));
			b.setEmail(rs.getString(3));
			b.setAddress(rs.getString(4));
			al.add(b);
		}
		return al;
	}
}