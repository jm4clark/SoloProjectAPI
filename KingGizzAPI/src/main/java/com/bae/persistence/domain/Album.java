package com.bae.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String releaseDate;
	private String albumArtLink;
	//private Genre genre;
	//private Song[] songs;
	//private Review[] reviews;
	
	public Album() {}
	
	public Album(int id, String name, String releaseDate, String albumArtLink) {
		this.id = id;
		this.name = name;
		this.releaseDate = releaseDate;
		this.albumArtLink = albumArtLink;
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
	
	public void settId(int id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAlbumArtLink() {
		return albumArtLink;
	}

	public void setAlbumArtLink(String albumArtLink) {
		this.albumArtLink = albumArtLink;
	}

}
