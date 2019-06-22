function onPressGetAll() {
    getAll("albums").then((res) => {
        let albums = JSON.parse(res.responseText);
        console.log(albums);
        removeAllChildren("albumResultTable");
        console.log("removed children");
        for (let a = 0; a < albums.length; a++) {
            console.log("making card...");
            let album = albumMakerVals(albums[a].id, albums[a].name, albums[a].releaseDate, albums[a].albumArtLink);
            console.log(album);
            albumCardMaker(album, "albumResultTable");
            console.log("card made");
        }
    }).catch(() => { console.log("get all Didn't work") });
}

function onPressGet(id) {
    console.log(id.value);
    get("album", id).then((res) => {
        let a = JSON.parse(res.responseText);
        console.log(a);
        removeAllChildren("albumResultTable");
        let make= albumMaker(a);
        console.log(make);
        albumCardMaker(make, "albumResultTable");
    }).catch(() => { console.log("didn't work") });
}

function onPressCreateAlbum(name, release, cover) {
    console.log(cover.value);
    let album = albumMakerVals(0, name.value, release.value, cover.value);
    console.log(album);
    create("album", JSON.stringify(album)).then(() => {
        console.log("Album created!");
    }).catch(() => { console.log("Album not created..."); })
}

function onPressDelete(id){
    deleteFunc("album", id.value).then(()=> {
        console.log("Deleted!");
    });
}

function albumCardMaker(album, id) {
    console.log(album);
    console.log(album.albumCover);
    let card = document.createElement("div");
    card.innerHTML = `<div class="col-12">
                    <div class="card w-100">
                    <div class="card-body">
                        <h5 class="card-title">${album.name} 
                        <image src="https://image.flaticon.com/icons/svg/61/61456.svg" style="height: 20px; width: 20px" data-toggle="collapse" data-target="#updateDiv" onclick="onClickUpdateIcon(${album.albumID}, '${album.name}', '${album.releaseDate}')" align="right"></image>
                        </h5> 
                        <image class="thumbnail" src="${album.albumArtLink}">                        
                        <p class="card-text">ID: ${album.albumID}</p>     
                        <p class="card-subtext">Release date: ${album.releaseDate}</p>
                    </div>
                    </div>
                </div>`;
    document.getElementById(id).appendChild(card);
}

function albumMakerVals(iD, name, date, cover) {

    const album = {
        albumID: iD,
        name: name,
        releaseDate: date,
        albumArtLink: cover
    }
    console.log(album);
    return album;
}

function albumMaker(albumObj) {
    const album = {
        albumID: albumObj.id,
        name: albumObj.name,
        releaseDate: albumObj.releaseDate,
        albumArtLink: albumObj.albumArtLink
    }
    console.log(album);
    return album;
}