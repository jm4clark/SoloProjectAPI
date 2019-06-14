package com.bae.business;

public interface SongService {
	String addSong(String song);

	String getAllSongs();

	String getAnSong(int id);

	String updateSong(int id, String song);

	String deleteSong(int id);
}
