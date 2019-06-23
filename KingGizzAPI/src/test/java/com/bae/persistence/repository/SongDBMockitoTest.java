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

import com.bae.persistence.domain.Song;
import com.bae.persistence.repository.SongDBRepo;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class SongDBMockitoTest {

	@InjectMocks
	private SongDBRepo repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	@Mock
	private JSONUtil util;

	private List<Song> songs;

	public static final Song SONG_ONE = new Song(1, "Robot Stop");
	public static final Song SONG_TWO = new Song(3, "Gamma Knife");

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setJSON(util);
		songs = new ArrayList<>();
	}

	@Test
	public void testGetAll() {
		songs.add(SONG_ONE);
		songs.add(SONG_TWO);

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(songs);

		Assert.assertEquals("[" + util.getJSONForObject(SONG_ONE) + "," + util.getJSONForObject(SONG_TWO) + "]",
				repo.getAllSongs());
	}

	@Test
	public void testGetOneSong() {
		songs.add(SONG_ONE);
		songs.add(SONG_TWO);

		Mockito.when(manager.find(Song.class, 1)).thenReturn(SONG_ONE);

		Assert.assertEquals(util.getJSONForObject(SONG_ONE), repo.getAnSong(1));
	}

	@Test
	public void testCreateSong() {
		Mockito.when(manager.merge(SONG_ONE)).thenReturn(SONG_ONE);

		Assert.assertEquals(util.messageToJSON("Song successfully added"),
				repo.createSong(util.getJSONForObject(SONG_ONE)));
	}

	@Test
	public void testDeleteSong() {
		songs.add(SONG_ONE);
		Mockito.when(manager.contains(manager.find(Song.class, 1))).thenReturn(true);

		Assert.assertEquals(util.messageToJSON("Song successfully deleted"), repo.deleteSong(1));
	}

	@Test
	public void testDeleteSongInvalid() {
		Mockito.when(manager.contains(manager.find(Song.class, 1))).thenReturn(false);

		Assert.assertEquals(util.messageToJSON("no song to be deleted"), repo.deleteSong(1));
	}

	@Test
	public void testUpdateSong() {

		songs.add(SONG_ONE);

		Mockito.when(manager.find(Song.class, 1)).thenReturn(SONG_ONE);

		Mockito.when(manager.merge(SONG_TWO)).thenReturn(SONG_TWO);

		Assert.assertEquals(util.messageToJSON("Song successfully updated"),
				repo.updateSong(SONG_ONE.getId(), util.getJSONForObject(SONG_TWO)));
	}

	@Test
	public void testUpdateSongInvalid() {
		Mockito.when(manager.find(Song.class, 1)).thenReturn(null);

		Assert.assertEquals(util.messageToJSON("no song to update"),
				repo.updateSong(SONG_ONE.getId(), util.getJSONForObject(SONG_TWO)));
	}

}
