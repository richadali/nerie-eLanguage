package nerie.e_resources.non_schedule.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nerie/e-resources/")

public class CaptchaController {

	private final DefaultKaptcha defaultKaptcha;

	@Autowired
	public CaptchaController(DefaultKaptcha defaultKaptcha) {
		this.defaultKaptcha = defaultKaptcha;
	}

	@GetMapping("/get-captcha")
	public @ResponseBody ResponseEntity<String> getCaptcha(HttpSession httpSession) throws IOException {

		String text = defaultKaptcha.createText();
		BufferedImage image = defaultKaptcha.createImage(text);
		httpSession.setAttribute("captchaText", text);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();
		String result = Base64.encodeBase64String(bytes);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
