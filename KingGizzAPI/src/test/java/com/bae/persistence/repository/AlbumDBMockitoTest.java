package com.bae.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Album;
import com.bae.persistence.repository.AlbumDBRepo;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AlbumDBMockitoTest {

	@InjectMocks
	private AlbumDBRepo repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	@Mock
	private JSONUtil util;

	public static final Album ALBUM_ONE = new Album(1, "Nonagon Infinity", "whenever", "https://images-na.ssl-images-amazon.com/images/I/81OzrxuWtGL._SY355_.jpg");
	public static final Album ALBUM_TWO = new Album(2, "Quarters!", "awhileago", "https://f4.bcbits.com/img/a3796441552_10.jpg");

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setJSON(util);
	}

	@Test
	public void testGetAll() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Album> albums = new ArrayList<>();
		albums.add(ALBUM_ONE);
		albums.add(ALBUM_TWO);
		Mockito.when(query.getResultList()).thenReturn(albums);

		Assert.assertEquals("[" + util.getJSONForObject(ALBUM_ONE) + "," + util.getJSONForObject(ALBUM_TWO) + "]",
				repo.getAllAlbums());
	}

	@Test
	public void testGetOneAlbum() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Album> albums = new ArrayList<>();
		albums.add(ALBUM_ONE);
		albums.add(ALBUM_TWO);
		Mockito.when(query.getSingleResult()).thenReturn(ALBUM_ONE);

		//Assert.assertEquals(util.getJSONForObject(ALBUM_ONE), repo.getAnAlbum(1));
	}
	
	@Test
	public void testCreateAlbum() {
		//Mockito.when(manager.find(Album.class, 1)).thenReturn();
		
	}
	
	@Test
	public void testDeleteAlbum() {
		
	}
	
	@Test
	public void testDeleteAlbumInvalid() {
		
	}
	
	@Test
	public void testUpdateAlbum() {
		
	}
	
	@Test
	public void testUpdateAlbumInvalid() {
		
	}
	
	

}
