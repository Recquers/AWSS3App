package in.vasanth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.vasanth.util.S3Util;

@Controller
public class MsgController {
	@Autowired
	private S3Util s3Util;

	@GetMapping("/")
	public String loadForm() {
		return "upload";
	}

	@PostMapping("/upload")
	public String handleUploadForm(Model model, @RequestParam("file") MultipartFile multipart) {
		String message = "";
		try {
			String saveFile = s3Util.saveFile(multipart);
			System.out.println(saveFile);
			message = "Your file has been uploaded successfully!";
		} catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
		}
		model.addAttribute("message", message);
		return "message";
	}

}
