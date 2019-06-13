package com.bae.business;

public interface AlbumService {
	String addAlbum(String album);

	String getAllAlbums();

	String getAnAlbum(int id);

	String updateAlbum(int id, String album);

	String deleteAlbum(int id);
}
