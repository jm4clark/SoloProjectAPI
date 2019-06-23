package com.bae.persistence.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int albumID;
	private String youtubeLink;
	
	
	public Song() {}
	
	public Song(int id, String name, int albumID, String youtubeLink) {
		this.id = id;
		this.name = name;
		this.albumID = albumID;
		this.youtubeLink = youtubeLink;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public int getAlbumID() {
		return albumID;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

}
