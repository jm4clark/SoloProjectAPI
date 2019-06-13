package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.*;

import com.bae.business.AlbumService;

@Path("albums")
public class AlbumController {
	@Inject
	private AlbumService service;

	@Path("getAllAlbums")
	@GET
	@Produces({ "application/json" })
	public String getAllAlbums() {
		return service.getAllAlbums();
	}

	@Path("/getAnAlbum/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAnAlbum(@PathParam("id") int id) {
		return service.getAnAlbum(id);
	}

	@Path("/createAlbum")
	@POST
	@Produces({ "application/json" })
	public String addAlbum(String album) {
		return service.addAlbum(album);
	}

	@Path("/deleteAlbum/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAlbum(@PathParam("id") int id) {
		return service.deleteAlbum(id);
	}

	@Path("/updateAlbum/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAlbum(@PathParam("id") int id, String album) {
		return service.updateAlbum(id, album);
	}
}
