function onPressGetAll(object) {
    getAll(object).then((res) => {
        let s = JSON.parse(res.responseText);
        console.log(res.responseText);
        let songs = JSON.parse(res.responseText);
        for (let s = 0; s < songs.length; s++) {
            console.log("card start");
            console.log(songs[s]);
            cardMaker(songMaker(songs[s]), "resultTable");
            console.log("card made!");
            console.log("It worked!");
        }
    }).catch(() => { console.log("Didn't work") });
}

function onPressGetAllByAlbumID(object, aID) {
    getAll(object).then((res) => {
        let songs = JSON.parse(res.responseText);

        for (s in songs) {
            if (s.albumID === aID) {
                console.log(s.id.value + s.name.value + s.albumID.value);
                cardMaker(songMaker(s.id, s.name, s.albumID), resultTable);
                console.log("card");
            }
        }

    });

}

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
                console.log(card);
    document.getElementById(id).appendChild(card);
}