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

function onPressCreate(object, id, sendData) {
    let s = songMaker(0, sendData[0], sendData[1]);
}

function onSearchName(name) {
    getAll("songs").then((res) =>{
        let songs = JSON.parse(res.responseText);
        for(let i = 0; i < songs.length; i++){
            let s = songs[i];
            if(s.name = name){
                onPressGet("song", s.id).then();
            }
        }
    })
}

function onPressUpdate() { }

function onPressDelete() { }

function songMaker(id, name, albumID, youtubeLink) {
    const song = {
        id: id,
        name: name,
        albumID: albumID,
        youtubeLink: youtubeLink
    }
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
                        <h5 class="card-title">${song.name} <image src="https://image.flaticon.com/icons/svg/61/61456.svg" style="height: 20px; width: 20px" onclick="onClickUpdate()" align="right"></image></h5>                         
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
    songYoutubeLink = songYoutubeLink.replace("watch?v=", "embed/");
    console.log(songYoutubeLink);
    video.innerHTML = `<div><iframe width="450" height="325" src="${songYoutubeLink}"></iframe></div>`;
    document.getElementById("modalBodySong").appendChild(video);
}

function removeAllChildren(id) {
    let result = document.getElementById(id);
    while (result.hasChildNodes()) {
        result.removeChild(result.firstChild);
    }
}

function fadeInCards(id) {
    let children = id.children;
    for (let i = 0; i < children.length; i++) {
        let child = children[i];
        child.fadeIn("slow");
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