function onPressGetAll() {
    getAll("songs").then((res) => {
        console.log(res.responseText);
        let songs = JSON.parse(res.responseText);
        removeAllChildren("resultTable");
        for (let s = 0; s < songs.length; s++) {
            console.log("card start");
            console.log(songs[s]);

            cardMaker(songMaker(songs[s]), "resultTable");
            //fadeInCards("resultTable");
            console.log("card made!");
            console.log("It worked!");
        }
    }).catch(() => { console.log("Didn't work") });
}

function onPressGetAllByAlbumID(object, aID) {
    getAll(object).then((res) => {
        let songs = JSON.parse(res.responseText);
        console.log("working...")
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
        removeAllChildren("resultTable");
        cardMaker(songMaker(s), "resultTable");
    }).catch(() => {
        console.log("Didn't work...");
    });
}

function onPressCreate(object, id, ){}

function onPressUpdate(){}

function onPressDelete(){}

function songMaker(id, name, albumID) {
    const song = {
        id: id,
        name: name,
        albumID: albumID
    }
    return song;
}

function songMaker(songObj) {
    const song = {
        id: songObj.id,
        name: songObj.name,
        albumID: songObj.albumID
    }
    return song;
}

function cardMaker(song, id) {
    let card = document.createElement("div");
    card.innerHTML = `<div class="col-12">
                    <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Song ${song.id}</h5>
                        <p class="card-text">${song.name}</p>                   
                    </div>
                    </div>
                </div>`;
    document.getElementById(id).appendChild(card);
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