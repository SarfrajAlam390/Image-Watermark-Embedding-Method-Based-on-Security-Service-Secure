package visual.security.evaluation.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import visual.security.evaluation.bean.Bean;

@WebServlet("/UploadFileServlet_user")
public class UploadFileServlet_user extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		List li = fileUpload.parseRequest(request);
		
		FileItem plainImage=(FileItem) li.get(0);
		byte[] plainImageBytes = plainImage.get();
		String iname=  plainImage.getName();
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey secretkey = keyGen.generateKey();
		String skey = secretkey.toString();
		String stringKey = Base64.encode(secretkey.getEncoded());
		System.out.println("scretkey " + stringKey);
		
		Cipher aescipher = Cipher.getInstance("AES");
		aescipher.init(Cipher.ENCRYPT_MODE, secretkey);
		byte[] encryptImageBytes = aescipher.doFinal(plainImageBytes);
		System.out.println("encryptImageBytes--->"+encryptImageBytes);
		
		Bean b = new Bean();
		b.setPlainImage(plainImageBytes);
		b.setEncryptedImage(encryptImageBytes);
		
		ArrayList<Bean> al = new ArrayList<Bean>();
		al.add(b);
		ses.setAttribute("image", al);
		RequestDispatcher rd = request.getRequestDispatcher("EncryptedImage_user.jsp");
		rd.include(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("UploadFile_user.jsp?status=Some Internal Error");
			rd.include(request, response);
		}
	}
}
