package com.bae.REST;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.AlbumService;
import com.bae.business.AlbumServiceImpl;
import com.bae.REST.AlbumController;

@RunWith(MockitoJUnitRunner.class)
public class RESTMockitoAlbumTests {
	@InjectMocks
	private AlbumController controller;

	@Mock
	private AlbumService service;

	@Test
	public void testGetAllAlbums() {
		Mockito.when(service.getAllAlbums()).thenReturn("all albums");
		assertEquals("all albums", controller.getAllAlbums());
	}

	@Test
	public void testGetAnAlbum() {
		Mockito.when(service.getAnAlbum(1)).thenReturn("an album");
		assertEquals("an album", controller.getAnAlbum(1));
	}

	@Test
	public void testDeleteAlbum() {
		Mockito.when(service.deleteAlbum(1)).thenReturn("album deleted");
		assertEquals("album deleted", controller.deleteAlbum(1));
	}

	@Test
	public void testUpdateAlbum() {
		Mockito.when(service.updateAlbum(1, "album")).thenReturn("album updated");
		assertEquals("album updated", controller.updateAlbum(1, "album"));
	}

	@Test
	public void testCreateAlbum() {
		Mockito.when(service.addAlbum("album")).thenReturn("album created");
		assertEquals("album created", controller.addAlbum("album"));
	}
}
