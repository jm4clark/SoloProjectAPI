package com.bae.persistence.repository;

public interface SongRepository {
	String getAllSongs();
	String getAnSong(int id);
	String createSong(String song);
	String deleteSong(int id);
	String updateSong(int id, String song);
}
