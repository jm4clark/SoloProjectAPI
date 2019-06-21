function onPressGetAll() {
    getAll("albums").then((res) => {
        let albums = JSON.parse(res.responseText);
        console.log(albums);
        removeAllChildren("albumResultTable");
        console.log("removed children");
        for (let a = 0; a < albums.length; a++) {
            console.log("making card...");
            albumCardMaker(albumMaker(albums[a].id, albums[a].name, albums[a].releaseDate, albums[a].albumArtLink), "albumResultTable");
            console.log("card made");
        }
    }).catch((res) => { console.log("get all Didn't work") });
}

function albumCardMaker(album, id) {

    console.log("got here card")
    let card = document.createElement("div");
    card.innerHTML = `<div class="col-12">
                    <div class="card w-100">
                    <div class="card-body">
                        <h5 class="card-title">${album.albumName} 
                        <image src="https://image.flaticon.com/icons/svg/61/61456.svg" style="height: 20px; width: 20px" data-toggle="collapse" data-target="#updateDiv" onclick="onClickUpdateIcon(${album.albumID}, '${album.albumName}', '${album.releaseDate}')" align="right"></image>
                        
                        </h5> 
                        <image class="thumbnail" src="${album.albumCover}">                        
                        <p class="card-text">ID: ${album.id}</p>     
                    </div>
                    </div>
                </div>`;
    console.log(document.getElementById(id));
    document.getElementById(id).appendChild(card);
}

function albumMaker(albumID, albumName, releaseDate, albumCover) {
    console.log("making album");
    const album = {
        albumID: albumID,
        albumName: albumName,
        releaseDate: releaseDate,
        albumCover: albumCover
    }
    console.log(album);
    return album;
}