package com.bae.persistence.repository;

public interface AlbumRepository {

	String getAllAlbums();
	String getAnAlbum(int id);
	String createAlbum(String album);
	String deleteAlbum(int id);
	String updateAlbum(int id, String album);
}
