package com.bae.business;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.SongServiceImpl;
import com.bae.persistence.repository.SongDBRepo;

@RunWith(MockitoJUnitRunner.class)
public class BusinessMockitoSongTests {

	@InjectMocks
	private SongServiceImpl service;

	@Mock
	private SongDBRepo repo;

	@Test
	public void testGetAllSongs() {
		Mockito.when(repo.getAllSongs()).thenReturn("all Songs");
		assertEquals("all Songs", service.getAllSongs());
	}

	@Test
	public void testGetAnSong() {
		Mockito.when(repo.getAnSong(1)).thenReturn("an Song");
		assertEquals("an Song", service.getAnSong(1));
	}

	@Test
	public void testDeleteSong() {
		Mockito.when(repo.deleteSong(1)).thenReturn("Song deleted");
		assertEquals("Song deleted", service.deleteSong(1));
	}

	@Test
	public void testUpdateSong() {
		Mockito.when(repo.updateSong(1, "Song")).thenReturn("Song updated");
		assertEquals("Song updated", service.updateSong(1, "Song"));
	}

	@Test
	public void testCreateSong() {
		Mockito.when(repo.createSong("Song")).thenReturn("Song created");
		assertEquals("Song created", service.addSong("Song"));
	}

}
