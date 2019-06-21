function onPressGetAll() {
    getAll("songs").then((res) => {
        let songs = JSON.parse(res.responseText);
        removeAllChildren("resultTable");
        for (let s = 0; s < songs.length; s++) {
            cardMaker(songMaker(songs[s]), "resultTable");
        }
    }).catch(() => { console.log("Didn't work") });
}

function onPressGetAllByAlbumID(object, aID) {
    getAll(object).then((res) => {
        let songs = JSON.parse(res.responseText);
        removeAllChildren("resultTable");
        for (let i = 0; i < songs.length; i++) {
            let s = songs[i];
            // console.log(aID.value);
            // console.log(s.albumID == aID.value);

            if (s.albumID == aID.value) {
                // console.log(s);
                cardMaker(songMaker(s), "resultTable");
                // console.log("card");
            }
        }

    }).catch(() => {
        console.log("Didn't work...");
    });

}

function onPressGet(object, id) {
    get(object, id).then((res) => {
        let s = JSON.parse(res.responseText);
        console.log(s);
        removeAllChildren("resultTable");
        cardMaker(songMaker(s), "resultTable");
    }).catch(() => {
        console.log("Didn't work...");
    });
}

function onPressCreate(name, yt, aid) {
    let song = songMakerVals(0, name.value, aid.value, yt.value);
    console.log(song);
    create("song", JSON.stringify(song)).then(() => {
        console.log("it worked??");
    }).catch(() => { console.log("didnt work") });
}

function onSearchName(name) {
    getAll("songs").then((res) => {
        let songs = JSON.parse(res.responseText);
        for (let i = 0; i < songs.length; i++) {
            let s = songs[i];
            if (s.name = name) {
                onPressGet("song", s.id).then();
            }
        }
    })
}
function onClickUpdateIcon(songID, songName, songYT, songAlbumID) {
    console.log("onclickupdate");
    console.log(document.getElementById("inpName").value);
    console.log(songName);
    document.getElementById("inpID").value = songID;
    document.getElementById("inpName").value = songName;
    document.getElementById("inpYT").value = songYT;
    console.log(document.getElementById("inpName").value);
    document.getElementById("albID").value = songAlbumID;
}

function onPressUpdate(oldID, newName, newYT, newAID) {
    let newSong = songMakerVals(oldID.value, newName.value, newAID.value, newYT.value);
    console.log(newSong);
    update("song", oldID.value, JSON.stringify(newSong)).then(() => {
        console.log("It updated!");
    }).catch(() => { console.log("It didn't update") });
}

function onPressDelete(id) {
    deleteFunc("song", id.value).then(() => {
        console.log("It worked!");
    });
}

function songMakerVals(id, name, albumID, youtubeLink) {
    const song = {
        id: id,
        name: name,
        albumID: albumID,
        youtubeLink: youtubeLink
    }
    console.log(song);
    return song;
}

function songMaker(songObj) {
    const song = {
        id: songObj.id,
        name: songObj.name,
        albumID: songObj.albumID,
        youtubeLink: songObj.youtubeLink
    }
    return song;
}

function cardMaker(song, id) {
    let card = document.createElement("div");
    card.innerHTML = `<div class="col-12">
                    <div class="card w-100">
                    <div class="card-body">
                        <h5 class="card-title">${song.name} 
                        <image src="https://image.flaticon.com/icons/svg/61/61456.svg" style="height: 20px; width: 20px" data-toggle="collapse" data-target="#updateDiv" onclick="onClickUpdateIcon(${song.id}, '${song.name}', '${song.youtubeLink}', ${song.albumID})" align="right"></image>
                        
                        </h5>                         
                        <p class="card-text">ID: ${song.id}</p> 
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal" onclick="youtubePopup('${song.name}', '${song.youtubeLink}')">
                            Listen
                        </button>                  
                    </div>
                    </div>
                </div>`;
    document.getElementById(id).appendChild(card);
}

function youtubePopup(songName, songYoutubeLink) {
    removeAllChildren("modalBodySong");
    console.log(songName);
    document.getElementById("modalTitle").innerText = songName;
    console.log(document.getElementById("modalBodySong"));

    let video = document.createElement("div");
    if (songYoutubeLink != "") {
        songYoutubeLink = songYoutubeLink.replace("watch?v=", "embed/");
        songYoutubeLink = songYoutubeLink + "?autoplay=1";
    }
    console.log(songYoutubeLink);
    video.innerHTML = `<div><iframe width="450" height="325" src="${songYoutubeLink}" alt="no video..."></iframe></div>`;
    document.getElementById("modalBodySong").appendChild(video);
}

function removeAllChildren(id) {
    let result = document.getElementById(id);
    while (result.hasChildNodes()) {
        result.removeChild(result.firstChild);
    }
}

function getAllSongNames() {
    return getAll("songs").then((res) => {
        console.log(res.responseText);
        let songs = JSON.parse(res.responseText);
        let songNames;
        for (let s = 0; s < songs.length; s++) {
            songNames.push(songs[s].name);
        }
    }).catch(() => { console.log("Didn't work") });
}