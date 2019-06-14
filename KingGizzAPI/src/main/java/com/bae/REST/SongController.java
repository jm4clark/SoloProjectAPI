package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.*;

import com.bae.business.SongService;

@Path("songs")
public class SongController {
	@Inject
	private SongService service;

	@Path("getAllSongs")
	@GET
	@Produces
	public String getAllSongs() {
		return service.getAllSongs();
	}
	
	@Path("/getASong/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAnSong(@PathParam("id") int id) {
		return service.getAnSong(id);
	}

	@Path("/createSong")
	@POST
	@Produces({ "application/json" })
	public String addSong(String song) {
		return service.addSong(song);
	}

	@Path("/deleteSong/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteSong(@PathParam("id") int id) {
		return service.deleteSong(id);
	}

	@Path("/updateSong/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateSong(@PathParam("id") int id, String song) {
		return service.updateSong(id, song);
	}

}
