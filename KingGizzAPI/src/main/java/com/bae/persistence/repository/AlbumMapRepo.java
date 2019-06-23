package com.bae.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import com.bae.persistence.domain.Album;
import com.bae.util.JSONUtil;

@Alternative
public class AlbumMapRepo implements AlbumRepository {

	private Map<Integer, Album> albumMap = new HashMap<>();

	private JSONUtil util = new JSONUtil();

	@Override
	public String getAllAlbums() {
		return util.getJSONForObject(albumMap);
	}

	@Override
	public String getAnAlbum(int id) {
		return util.getJSONForObject(albumMap.get(id));
	}

	@Override
	public String createAlbum(String album) {
		Album newAlbum = util.getObjectForJSON(album, Album.class);
		albumMap.put(newAlbum.getId(), newAlbum);
		return util.messageToJSON("Album successfully created!");
	}

	@Override
	public String deleteAlbum(int id) {
		if (albumMap.containsKey(id)) {
			albumMap.remove(id);
			return util.messageToJSON("Album successfully deleted!");
		}
		return util.messageToJSON("No album to delete...");
	}

	@Override
	public String updateAlbum(int id, String album) {
		if (albumMap.containsKey(id)) {
			Album newAlbum = util.getObjectForJSON(album, Album.class);
			albumMap.put(id, newAlbum);
			return util.messageToJSON("Album successfully updated!");
		}
		return util.messageToJSON("No album to update...");
	}

	public Map<Integer, Album> getAlbumMap() {
		return albumMap;
	}

}
