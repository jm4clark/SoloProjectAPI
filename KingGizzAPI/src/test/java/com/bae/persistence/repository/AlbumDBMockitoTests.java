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
public class AlbumDBMockitoTests {
	
	@InjectMocks
	private AlbumDBRepo repo;
	
	@Mock
	private EntityManager manager;
	
	@Mock 
	private Query query;
	
	@Mock
	private JSONUtil util;
	
	
	public static final Album ALBUM_ONE = new Album(1, "Nonagon Infinity", "whenever");
	public static final Album ALBUM_TWO = new Album(2, "Quarters!", "awhileago");
	
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
		
		Assert.assertEquals("[" + util.getJSONForObject(ALBUM_ONE) + "," + util.getJSONForObject(ALBUM_TWO) + "]", repo.getAllAlbums());			
	}
	
	@Test
	public void testGetOneAlbum() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		
	}

}
