function onPressGetAll() {
    getAll("albums").then((res) => {
        let albums = JSON.parse(res.responseText);
        removeAllChildren("resultTable");
        for (let s = 0; s < albums.length; s++) {
            cardMaker(songMaker(albums[s]), "resultTable");
        }
    }).catch(() => { console.log("Didn't work") });
}

function cardMaker(album, id) {
    let card = document.createElement("div");
    card.innerHTML = `<div class="col-12">
                    <div class="card w-100">
                    <div class="card-body">
                        <h5 class="card-title">${album.name} 
                        <image src="https://image.flaticon.com/icons/svg/61/61456.svg" style="height: 20px; width: 20px" data-toggle="collapse" data-target="#updateDiv" onclick="onClickUpdateIcon(${album.id}, '${album.name}', '${album.cover}')" align="right"></image>
                        
                        </h5> 
                        <image src="${album.cover}">                        
                        <p class="card-text">ID: ${album.id}</p>     
                    </div>
                    </div>
                </div>`;
    document.getElementById(id).appendChild(card);
}