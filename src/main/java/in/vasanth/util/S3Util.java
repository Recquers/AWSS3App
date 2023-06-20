package in.vasanth.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;

import in.vasanth.entity.MapKeyWithData;
import in.vasanth.repo.ObjectS3Repo;

@Service
public class S3Util {
	@Value("${bucketName}")
	private String bucketName;
	
	@Autowired
	private ObjectS3Repo repo;

	private final AmazonS3 s3;

	public S3Util(AmazonS3 s3) {
		this.s3 = s3;
	}
	
	public void saveUri(String key) {
		
		URL url = s3.getUrl(bucketName, key);
		String value = url.toString();
		MapKeyWithData data = new MapKeyWithData();
		data.setFileKey(key);
		data.setObjectUri(value);
		repo.save(data);
		
	}
	

	
	public String saveFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		try {
			File file1 = convertMultiPartToFile(file);
			PutObjectResult putObjectResult = s3.putObject(bucketName, originalFilename, file1);
			saveUri(originalFilename);
			return putObjectResult.getContentMd5();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}


}
