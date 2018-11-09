package ca.com.diogo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2214401227561634055L;

	@Id
	@Column(name="id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String type;
	
	@Column(nullable=false, length=100)
	private String name;
	
	@Column(name="description", nullable=true, length=500)
	private String desc;
	
	@Column(nullable=true, length=50)
	private String urlPhoto;
	
	@Column(nullable=true, length=50)
	private String urlVideo;
	
	@Column(nullable=true, length=15)
	private String latitude;
	
	@Column(nullable=true, length=15)
	private String longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", type=" + type + ", name=" + name + ", desc=" + desc + ", urlPhoto=" + urlPhoto
				+ ", urlVideo=" + urlVideo + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
