package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.SongRepository;;

public class SongServiceImpl implements SongService{
	@Inject
	SongRepository repo;

	@Override
	public String addSong(String song) {
		return repo.createSong(song);
	}

	@Override
	public String getAllSongs() {
		return repo.getAllSongs();
	}

	@Override
	public String getAnSong(int id) {
		return repo.getAnSong(id);
	}

	@Override
	public String updateSong(int id, String song) {
		return repo.updateSong(id, song);
	}

	@Override
	public String deleteSong(int id) {
		return repo.deleteSong(id);
	}
}
