package visual.security.evaluation.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;

import visual.security.evaluation.bean.Bean;
import visual.security.evaluation.dao.SecurityDAO;
import visual.security.evaluation.util.ImageImportance;

@WebServlet("/ImageImportanceServlet_user")
public class ImageImportanceServlet_user extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		HttpSession ses = request.getSession();
		byte[] plainImege = null;
		byte[] encImage = null;
		byte[] contrastImage = null;
		byte[] encContrastImage = null;
		byte[] ampImage = null;
		byte[] amencImage = null;
		double percent=0;
		BufferedImage bufferimg1 = null;
		BufferedImage bufferimg2 = null;
		int uid =(Integer)ses.getAttribute("uid");
		ArrayList<Bean> image = (ArrayList<Bean>)ses.getAttribute("image");
		ArrayList<Bean> contrast = (ArrayList<Bean>)ses.getAttribute("contrast");
		ArrayList<Bean> ampletudeImage = (ArrayList<Bean>)ses.getAttribute("ampletudeImage");
		for(Bean img:image) 
		{
			plainImege = img.getPlainImage();
			encImage = img.getEncryptedImage();
		}
		
		for(Bean con:contrast) 
		{
			contrastImage = con.getContrastImage();
			encContrastImage = con.getEncryptcontrastImage();
		}
		for(Bean amp : ampletudeImage) 
		{
			ampImage = amp.getAmplitudeImage();
			amencImage = amp.getEncryptamplitudeImage();
		}
		System.out.println("plain Image-Ser-->"+plainImege);
		System.out.println("Enc Image-Ser-->"+encImage);
		InputStream imageio = new ByteArrayInputStream(plainImege);
		InputStream imageio1 = new ByteArrayInputStream(amencImage);
		
		bufferimg1 = ImageIO.read(imageio);
		bufferimg2 = ImageIO.read(imageio1);
		
		new ImageImportance();
		BufferedImage pollingImg = ImageImportance.getDifferenceImage(bufferimg1, bufferimg2);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(pollingImg, "jpg", baos);
		byte[] imagePooling = baos.toByteArray();
		
		if(imagePooling!=null) {
		percent =  new ImageImportance().percentageCal(bufferimg1, bufferimg2);
		if(percent!=0) 
		{
			double percentage = 0;
			b.setUid(uid);
			b.setPlainImage(plainImege);
			b.setEncryptedImage(encImage);
			b.setContrastImage(contrastImage);
			b.setEncryptcontrastImage(encContrastImage);
			b.setAmplitudeImage(ampImage);
			b.setEncryptamplitudeImage(amencImage);
			b.setPoolImage(imagePooling);
			percentage = percent;
			b.setPercentage(percentage);
			
			try {
				Thread.sleep(6000);
				int i = new SecurityDAO().iibvsiImage(b);
				if(i!=0) 
				{
					RequestDispatcher rd= request.getRequestDispatcher("UploadFile_user.jsp?status=Successfully Created");
					rd.include(request, response);
				}
				else 
				{
					RequestDispatcher rd= request.getRequestDispatcher("UploadFile_user.jsp?status=Not Created");
					rd.include(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd= request.getRequestDispatcher("UploadFile_user.jsp?status=Some Internal Error");
				rd.include(request, response);
			}
		}
		}
		else 
		{
			RequestDispatcher rd= request.getRequestDispatcher("UploadFile_user.jsp?status=Buffer Image Error");
			rd.include(request, response);
		}
		System.out.println("percentage--->"+percent);
	}
}