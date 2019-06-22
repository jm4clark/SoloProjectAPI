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

	private List<Album> albums;

	public static final Album ALBUM_ONE = new Album(1, "Nonagon Infinity", "whenever",
			"https://images-na.ssl-images-amazon.com/images/I/81OzrxuWtGL._SY355_.jpg");
	public static final Album ALBUM_TWO = new Album(2, "Quarters!", "awhileago",
			"https://f4.bcbits.com/img/a3796441552_10.jpg");

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setJSON(util);
		albums = new ArrayList<>();
	}

	@Test
	public void testGetAll() {
		albums.add(ALBUM_ONE);
		albums.add(ALBUM_TWO);

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(albums);

		Assert.assertEquals("[" + util.getJSONForObject(ALBUM_ONE) + "," + util.getJSONForObject(ALBUM_TWO) + "]",
				repo.getAllAlbums());
	}

	@Test
	public void testGetOneAlbum() {
		albums.add(ALBUM_ONE);
		albums.add(ALBUM_TWO);

		Mockito.when(manager.find(Album.class, 1)).thenReturn(ALBUM_ONE);

		Assert.assertEquals(util.getJSONForObject(ALBUM_ONE), repo.getAnAlbum(1));
	}

	@Test
	public void testCreateAlbum() {
		Mockito.when(manager.merge(ALBUM_ONE)).thenReturn(ALBUM_ONE);

		Assert.assertEquals(util.messageToJSON("album successfully added"),
				repo.createAlbum(util.getJSONForObject(ALBUM_ONE)));
	}

	@Test
	public void testDeleteAlbum() {
		albums.add(ALBUM_ONE);
		Mockito.when(manager.contains(manager.find(Album.class, 1))).thenReturn(true);

		Assert.assertEquals(util.messageToJSON("album successfully deleted"), repo.deleteAlbum(1));
	}

	@Test
	public void testDeleteAlbumInvalid() {
		Mockito.when(manager.contains(manager.find(Album.class, 1))).thenReturn(false);

		Assert.assertEquals(util.messageToJSON("no album to delete"), repo.deleteAlbum(1));
	}

	@Test
	@Ignore
	public void testUpdateAlbum() {

		albums.add(ALBUM_ONE);
		
		Mockito.when(manager.find(Album.class, 2)).thenReturn(ALBUM_TWO);
		
		Mockito.when(manager.merge(ALBUM_ONE)).thenReturn(ALBUM_ONE);
		
		Assert.assertEquals(util.messageToJSON("album successfully updated"), repo.updateAlbum(ALBUM_ONE.getId(), util.getJSONForObject(ALBUM_TWO)));
	}

	@Test
	public void testUpdateAlbumInvalid() {

	}

}
