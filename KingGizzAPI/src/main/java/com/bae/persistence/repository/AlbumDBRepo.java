package com.bae.persistence.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bae.persistence.domain.Album;
import com.bae.util.JSONUtil;

public class AlbumDBRepo implements AlbumRepository {
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllAlbums() {
		Query query = manager.createQuery("Selec a FROM Album a");
		return util.getJSONForObject(query.getResultList());
	}

	@Override
	public String getAnAlbum(int id) {
		return util.getJSONForObject(manager.find(Album.class, id));
	}

	@Override
	public String createAlbum(String album) {
		Album a = util.getObjectForJSON(album,  Album.class);
		manager.persist(a);
		return util.messageToJSON("album successfully added");
	}

	@Override
	public String deleteAlbum(int id) {
		Album a = util.getObjectForJSON(getAnAlbum(id), Album.class);
		
		if(manager.contains(manager.find(Album.class, id))) {
			manager.remove(manager.find(Album.class, id));
		}
		
		return util.messageToJSON("album successfully deleted");
	}

	@Override
	public String updateAlbum(int id, String album) {
		Album newAlbum = util.getObjectForJSON(album, Album.class);
		Album oldAlbum = manager.find(Album.class, id);
		
		if(oldAlbum != null) {
			
			oldAlbum.setName(newAlbum.getName());
			//other attributes here
			
			manager.persist(oldAlbum);
		}
		
		return util.messageToJSON("album successfully updated");
	}

}
