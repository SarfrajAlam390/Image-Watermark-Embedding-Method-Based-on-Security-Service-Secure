package visual.security.evaluation.servlet;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
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

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.apache.commons.io.output.ByteArrayOutputStream;

import visual.security.evaluation.bean.Bean;


@WebServlet("/ChangeContrastServlet_user")
public class ChangeContrastServlet_user extends HttpServlet {
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		BufferedImage buffer = null;
		BufferedImage buffer1 = null;
		float scaleFactor =1.1f;
		float scaleFactorencrypt =0.4f;
	    RescaleOp rescale;
	    RescaleOp rescale1;
		byte[] byteimage = null;
		try {
			Thread.sleep(6000);
		ArrayList<Bean> al =(ArrayList<Bean>) ses.getAttribute("image");
		for(Bean image:al) 
		{
			 byteimage = image.getPlainImage();
		}
		InputStream imageio = new ByteArrayInputStream(byteimage);
		
		buffer = ImageIO.read(imageio);
		
		
		rescale = new RescaleOp(scaleFactor,10.0f, null);
		buffer=rescale.filter(buffer,null);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
		
		ImageIO.write(buffer, "jpg", baos);
		
		byte[] bytecontrast = baos.toByteArray();
		Bean b = new Bean();
		b.setContrastImage(bytecontrast);
		
		rescale1 =  new RescaleOp(scaleFactorencrypt,10.0f, null);
		buffer1=rescale1.filter(buffer,null);
		
		ImageIO.write(buffer1, "jpg", baos1);
		byte[] bytecontrastEncrypt = baos1.toByteArray();
		b.setEncryptcontrastImage(bytecontrastEncrypt);
		
		HttpSession session = request.getSession();
		ArrayList<Bean> contrast = new ArrayList<Bean>();
		contrast.add(b);
		session.setAttribute("contrast", contrast);
		if(buffer!=null) {
		RequestDispatcher rd = request.getRequestDispatcher("ContrastImage_user.jsp");
		rd.include(request, response);
		}
		else 
		{
			RequestDispatcher rd = request.getRequestDispatcher("UploadFile_user.jsp?status=Faild to Contrast");
			rd.include(request, response);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		RequestDispatcher rd = request.getRequestDispatcher("UploadFile_user.jsp?status=Some Internal Error");
		rd.include(request, response);
	}
}
}