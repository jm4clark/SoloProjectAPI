function onLoadFunc() {
    addAlbumsToDropdown();
}

function addAlbumsToDropDown() {
    getAll("albums").then((res) => {
        let albums = JSON.parse(res.responseText);

        for (let i = 0; i < albums.length; i++) {
            let a = albums[i];
            dropDownMaker(a.name);
        }
    });
}

function dropDownMaker(albumName){
    let drop = document.createElement("li");
    drop.innerHTML = `<li><a tabindex="-1" href="#">${albumName}</a></li>`;
}

function cardMaker(album, id) {
    let card = document.createElement("div");
    card.innerHTML = `<div class="card" style="width: 18rem;">
                <img class="card-img-top" src="profile${account.id}.jpg" onerror="this.onerror=null; this.src='defaultpic.jpg'" alt="no profile picture found">
                <div class="card-body">
                    <h5 class="card-title">Account ${account.accountNumber}</h5>
                    <p class="card-text">${account.firstName} ${account.lastName}</p>                   
                </div>
            </div>`;
    document.getElementById(id).appendChild(card);
}