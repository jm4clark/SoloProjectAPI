package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Album;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AlbumDBRepo implements AlbumRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setJSON(JSONUtil util) {
		this.util = util;
	}

	@Override
	public String getAllAlbums() {
		Query query = manager.createQuery("Select a FROM Album a");
		return util.getJSONForObject(query.getResultList());
	}

	@Override
	public String getAnAlbum(int id) {
		return util.getJSONForObject(manager.find(Album.class, id));
	}

	@Override
	@Transactional(REQUIRED)
	public String createAlbum(String album) {
		Album a = util.getObjectForJSON(album, Album.class);
		manager.merge(a);
		return util.messageToJSON("album successfully added");
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAlbum(int id) {
		if (manager.contains(manager.find(Album.class, id))) {
			manager.remove(manager.find(Album.class, id));
			return util.messageToJSON("album successfully deleted");
		}
		return util.messageToJSON("no album to delete");

	}

	@Override
	@Transactional(REQUIRED)
	public String updateAlbum(int id, String album) {
		Album newAlbum = util.getObjectForJSON(album, Album.class);
		Album oldAlbum = manager.find(Album.class, id);

		if (oldAlbum != null) {

			oldAlbum.setName(newAlbum.getName());
			// other attributes here

			manager.persist(oldAlbum);
			return util.messageToJSON("album successfully updated");
		}

		return util.messageToJSON("no album to update");
	}

}
