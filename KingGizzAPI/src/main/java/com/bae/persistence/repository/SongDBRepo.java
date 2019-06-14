package com.bae.persistence.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bae.persistence.domain.Song;
import com.bae.util.JSONUtil;

public class SongDBRepo implements SongRepository{
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllSongs() {
		Query query = manager.createQuery("Selec a FROM Song a");
		return util.getJSONForObject(query.getResultList());
	}

	@Override
	public String getAnSong(int id) {
		return util.getJSONForObject(manager.find(Song.class, id));
	}

	@Override
	public String createSong(String song) {
		Song a = util.getObjectForJSON(song,  Song.class);
		manager.persist(a);
		return util.messageToJSON("Song successfully added");
	}

	@Override
	public String deleteSong(int id) {		
		if(manager.contains(manager.find(Song.class, id))) {
			manager.remove(manager.find(Song.class, id));
		}
		
		return util.messageToJSON("Song successfully deleted");
	}

	@Override
	public String updateSong(int id, String song) {
		Song newSong = util.getObjectForJSON(song, Song.class);
		Song oldSong = manager.find(Song.class, id);
		
		if(oldSong != null) {
			
			oldSong.setName(newSong.getName());
			//other attributes here
			
			manager.persist(oldSong);
		}
		
		return util.messageToJSON("Song successfully updated");
	}
}
