package visual.security.evaluation.bean;

public class Bean {
	private int uid;
	private int fid;
	private String uname;
	private String email;
	private String password;
	private String gender;
	private String dob;
	private String address;
	
	private byte[] plainImage;
	private byte[] encryptedImage;
	private byte[] contrastImage;
	private byte[] encryptcontrastImage;
	
	private byte[] amplitudeImage;
	private byte[] encryptamplitudeImage;
	private byte[] poolImage;
	private double percentage;
	
	
	
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public byte[] getPoolImage() {
		return poolImage;
	}
	public void setPoolImage(byte[] poolImage) {
		this.poolImage = poolImage;
	}
	public byte[] getAmplitudeImage() {
		return amplitudeImage;
	}
	public void setAmplitudeImage(byte[] amplitudeImage) {
		this.amplitudeImage = amplitudeImage;
	}
	public byte[] getEncryptamplitudeImage() {
		return encryptamplitudeImage;
	}
	public void setEncryptamplitudeImage(byte[] encryptamplitudeImage) {
		this.encryptamplitudeImage = encryptamplitudeImage;
	}
	public byte[] getEncryptcontrastImage() {
		return encryptcontrastImage;
	}
	public void setEncryptcontrastImage(byte[] encryptcontrastImage) {
		this.encryptcontrastImage = encryptcontrastImage;
	}
	public byte[] getContrastImage() {
		return contrastImage;
	}
	public void setContrastImage(byte[] contrastImage) {
		this.contrastImage = contrastImage;
	}
	public byte[] getPlainImage() {
		return plainImage;
	}
	public void setPlainImage(byte[] plainImage) {
		this.plainImage = plainImage;
	}
	public byte[] getEncryptedImage() {
		return encryptedImage;
	}
	public void setEncryptedImage(byte[] encryptedImage) {
		this.encryptedImage = encryptedImage;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
