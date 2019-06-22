package com.bae.MapTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Album;
import com.bae.persistence.repository.AlbumMapRepo;
import com.bae.util.JSONUtil;

public class AlbumServiceTest {
	private AlbumMapRepo repo;
	private Album alb1 = new Album(1, "Nonagon Infinity", "2017-01-01", "img");
	private Album alb2 = new Album(2, "12 Bar Bruise", "2015-01-01", "img2");
	private Album alb3 = new Album(3, "Fishing for Fishies", "2019-01-01", "img3");
	private JSONUtil util = new JSONUtil();
	
	@Before
	public void setup() {
		repo = new AlbumMapRepo();
	}
	
	@Test
	public void getAllAlbumsTestEmpty() {
		assertEquals("{}", repo.getAllAlbums());
	}
	
	@Test
	public void getAllAlbumsTestOne() {
		repo.getAlbumMap().put(1, alb1);
		assertEquals("{\"1\":" + util.getJSONForObject(alb1) + "}", repo.getAllAlbums());
	}
	
	@Test
	public void addAlbumTest() {
		String newAlbum = util.getJSONForObject(alb1);
		assertEquals(repo.createAlbum(newAlbum), util.messageToJSON("Album successfully created!"));
		assertEquals(repo.getAlbumMap().size(), 1);
	}
	
	@Test
	public void addTwoAlbumTest() {
		assertEquals(repo.createAlbum(util.getJSONForObject(alb1)), util.messageToJSON("Album successfully created!"));
		assertEquals(repo.createAlbum(util.getJSONForObject(alb2)), util.messageToJSON("Album successfully created!"));
		assertEquals(repo.getAlbumMap().size(), 2);
	}
	
	@Test
	public void deleteAlbumTest() {
		repo.getAlbumMap().put(1, alb1);
		repo.deleteAlbum(1);
		assertEquals(null, repo.getAlbumMap().get(1));
	}
	
	@Test 
	public void deleteOneOfTwoAlbumTest() {
		repo.getAlbumMap().put(1, alb1);
		repo.getAlbumMap().put(2, alb2);
		repo.deleteAlbum(1);
		
		assertEquals(null, repo.getAlbumMap().get(1));
		assertEquals(alb2, repo.getAlbumMap().get(2));
		
		repo.deleteAlbum(2);
		assertEquals(null, repo.getAlbumMap().get(1));
		assertEquals(null, repo.getAlbumMap().get(2));		
	}
	
	@Test
	public void deleteAlbumAndNonExistAlbumTest() {
		repo.getAlbumMap().put(1, alb1);
		
		assertEquals(repo.deleteAlbum(1), util.messageToJSON("Album successfully deleted!"));
		assertEquals(repo.deleteAlbum(2), util.messageToJSON("No album to delete..."));
	}
	
	@Test
	public void updateAlbumTest() {
		repo.getAlbumMap().put(1, alb1);
		Album updatedAlbum = alb1;
		updatedAlbum.setName("Octagon Infinity");
		repo.updateAlbum(1, util.getJSONForObject(updatedAlbum));
		assertEquals("Octagon Infinity", repo.getAlbumMap().get(1).getName());
	}
	
	@Test 
	public void updateNonExistAlbumTest() {
		
		assertEquals(util.messageToJSON("No album to update..."), repo.updateAlbum(1, util.getJSONForObject(alb1)));
	}
	
	@Test
	public void jsonUtilGetJSON() {
		assertEquals(util.getJSONForObject(alb1),"{\"id\":1,\"name\":\"Nonagon Infinity\",\"releaseDate\":\"2017-01-01\",\"albumArtLink\":\"img\"}");
	}
	
	
	@Test
	public void jsonUtilGetObject() {
		String stringTest = "{\"id\":1,\"name\":\"Nonagon Infinity\",\"releaseDate\":\"2017-01-01\",\"albumArtLink\":\"img\"}";
		Album testAlbum = util.getObjectForJSON(stringTest, Album.class);
		assertEquals(alb1.getId(), testAlbum.getId());
	}
}
