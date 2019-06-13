package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.AlbumRepository;

public class AlbumServiceImpl implements AlbumService {

	@Inject
	AlbumRepository repo;

	@Override
	public String addAlbum(String album) {
		return repo.createAlbum(album);
	}

	@Override
	public String getAllAlbums() {
		return repo.getAllAlbums();
	}

	@Override
	public String getAnAlbum(int id) {
		return repo.getAnAlbum(id);
	}

	@Override
	public String updateAlbum(int id, String album) {
		return repo.updateAlbum(id, album);
	}

	@Override
	public String deleteAlbum(int id) {
		return repo.deleteAlbum(id);
	}

}
