package in.vasanth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MapKeyWithData {
	
	@Id
	@GeneratedValue
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String fileKey;
	private String objectUri;
	
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public String getObjectUri() {
		return objectUri;
	}
	public void setObjectUri(String objectUri) {
		this.objectUri = objectUri;
	}
	

}
