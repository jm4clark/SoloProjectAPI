package com.bae.REST;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.SongService;
import com.bae.business.SongServiceImpl;
import com.bae.REST.SongController;

@RunWith(MockitoJUnitRunner.class)
public class RESTMockitoSongTests {
	@InjectMocks
	private SongController controller;

	@Mock
	private SongService service;

	@Test
	public void testGetAllSongs() {
		Mockito.when(service.getAllSongs()).thenReturn("all Songs");
		assertEquals("all Songs", controller.getAllSongs());
	}

	@Test
	public void testGetAnSong() {
		Mockito.when(service.getAnSong(1)).thenReturn("an Song");
		assertEquals("an Song", controller.getAnSong(1));
	}

	@Test
	public void testDeleteSong() {
		Mockito.when(service.deleteSong(1)).thenReturn("Song deleted");
		assertEquals("Song deleted", controller.deleteSong(1));
	}

	@Test
	public void testUpdateSong() {
		Mockito.when(service.updateSong(1, "Song")).thenReturn("Song updated");
		assertEquals("Song updated", controller.updateSong(1, "Song"));
	}

	@Test
	public void testCreateSong() {
		Mockito.when(service.addSong("Song")).thenReturn("Song created");
		assertEquals("Song created", controller.addSong("Song"));
	}
}
