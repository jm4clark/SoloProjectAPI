package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Song;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class SongDBRepo implements SongRepository{
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllSongs() {
		Query query = manager.createQuery("Select a FROM Song a");
		return util.getJSONForObject(query.getResultList());
	}

	@Override
	public String getAnSong(int id) {
		return util.getJSONForObject(manager.find(Song.class, id));
	}

	@Override
	@Transactional(REQUIRED)
	public String createSong(String song) {
		Song a = util.getObjectForJSON(song,  Song.class);
		manager.merge(a);
		return util.messageToJSON("Song successfully added");
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteSong(int id) {		
		if(manager.contains(manager.find(Song.class, id))) {
			manager.remove(manager.find(Song.class, id));
			return util.messageToJSON("Song successfully deleted");
		}
		
		return util.messageToJSON("no song to be deleted");
	}

	@Override
	@Transactional(REQUIRED)
	public String updateSong(int id, String song) {
		Song newSong = util.getObjectForJSON(song, Song.class);
		Song oldSong = manager.find(Song.class, id);
		
		if(oldSong != null) {
			
			oldSong.setName(newSong.getName());
			oldSong.setAlbumID(newSong.getAlbumID());
			
			manager.persist(oldSong);
			return util.messageToJSON("Song successfully updated");
		}
		
		return util.messageToJSON("no song to update");
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setJSON(JSONUtil util) {
		this.util = util;
	}
	
}
