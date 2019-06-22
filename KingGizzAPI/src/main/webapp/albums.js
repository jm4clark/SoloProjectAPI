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
        let make = albumMaker(a);
        console.log(make);
        albumCardMaker(make, "albumResultTable");
    }).catch(() => { console.log("didn't work") });
}

function onPressCreate(name, release, cover) {
    console.log(cover.value);
    let album = albumMakerVals(0, name.value, release.value, cover.value);
    console.log(album);
    create("album", JSON.stringify(album)).then(() => {
        console.log("Album created!");
    }).catch(() => { console.log("Album not created..."); })
}

function onPressDelete(id) {
    deleteFunc("album", id.value).then(() => {
        console.log("Deleted!");
    });
}

function onPressUpdate(oldID, newName, newDate, newCover) {
    let newAlbum = albumMakerVals(oldID.value, newName.value, newDate.value, newCover.value);
    console.log(newAlbum);
    update("album", oldID.value, JSON.stringify(newAlbum)).then(() => {
        console.log("Updated!");
    })
}

function onClickUpdateIcon(id, name, date, cover) {
    document.getElementById("updID").value = id;
    document.getElementById("updName").value = name;
    document.getElementById("updDate").value = date;
    document.getElementById("updCover").value = cover;
}

function albumCardMaker(album, id) {
    let card = document.createElement("div");
    card.innerHTML = `<div class="col-12">
                    <div class="card w-100">
                    <div class="card-body">
                        <h5 class="card-title">${album.name} 
                        <image src="https://image.flaticon.com/icons/svg/61/61456.svg" style="height: 20px; width: 20px" data-toggle="collapse" data-target="#updateDivA" onclick="onClickUpdateIcon(${album.albumID}, '${album.name}', '${album.releaseDate}', '${album.albumArtLink}')" align="right"></image>
                        </h5>
                        <div class="albumPicDiv"> 
                        <image class="thumbnail" src="${album.albumArtLink}">   
                        </div>                     
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
        albumArtLink: cover,
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

    return album;
}

function albumMakerWithSongs(albumObj) {
    return getAll("songs").then((res) => {
        let songs = JSON.parse(res.responseText);
        let songList = [];
        for (let i = 0; i < songs.length; i++) {
            let s = songs[i];
            if (s.albumID == albumObj.id) {
                songList.push(s);
            }
        }
        const album = {
            albumID: albumObj.id,
            name: albumObj.name,
            releaseDate: albumObj.releaseDate,
            albumArtLink: albumObj.albumArtLink,
            songs: songList
        }
        console.log(album);
        console.log(album.songs);
        return album;
    });
}