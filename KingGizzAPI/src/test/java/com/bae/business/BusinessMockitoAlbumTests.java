package com.bae.business;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.AlbumServiceImpl;
import com.bae.persistence.repository.AlbumDBRepo;

@RunWith(MockitoJUnitRunner.class)
public class BusinessMockitoAlbumTests {

	@InjectMocks
	private AlbumServiceImpl service;

	@Mock
	private AlbumDBRepo repo;

	@Test
	public void testGetAllAlbums() {
		Mockito.when(repo.getAllAlbums()).thenReturn("all albums");
		assertEquals("all albums", service.getAllAlbums());
	}

	@Test
	public void testGetAnAlbum() {
		Mockito.when(repo.getAnAlbum(1)).thenReturn("an album");
		assertEquals("an album", service.getAnAlbum(1));
	}

	@Test
	public void testDeleteAlbum() {
		Mockito.when(repo.deleteAlbum(1)).thenReturn("album deleted");
		assertEquals("album deleted", service.deleteAlbum(1));
	}

	@Test
	public void testUpdateAlbum() {
		Mockito.when(repo.updateAlbum(1, "album")).thenReturn("album updated");
		assertEquals("album updated", service.updateAlbum(1, "album"));
	}

	@Test
	public void testCreateAlbum() {
		Mockito.when(repo.createAlbum("album")).thenReturn("album created");
		assertEquals("album created", service.addAlbum("album"));
	}

}
