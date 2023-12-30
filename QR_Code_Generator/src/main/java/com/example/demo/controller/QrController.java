package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
@RequestMapping("/qr")
public class QrController {
	
	@ModelAttribute("qr")
	public User user() {
		return new User();
	}
	
	@GetMapping
	public String homePage() {
		return "index";
	}
	
	@PostMapping
	public String generateQRCode(@ModelAttribute("qr") User user, Model model) {
		
		try {
			
		
			BufferedImage buffImage = generateQRCOde(user);
			File output = new File("C:\\STS Workspace\\QR_Code_Generator\\src\\main\\resources\\static\\"+user.getFirstName()+".jpg");
			ImageIO.write(buffImage, "jpg", output);
			model.addAttribute(user);
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/qr?success";
	}

	private BufferedImage generateQRCOde(User user) throws WriterException {
		
		StringBuilder str = new StringBuilder();
		str = str.append("First Name").append(user.getFirstName()).append("| |")
				 .append("Last Name").append(user.getLastName()).append("| |")
				 .append("City").append(user.getCity()).append("| |")
				 .append("State").append(user.getState()).append("| |")
				 .append("Zipcode").append(user.getZipCode());
		
		QRCodeWriter codeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = codeWriter.encode(str.toString(), BarcodeFormat.QR_CODE, 200, 200);
				 
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

}
