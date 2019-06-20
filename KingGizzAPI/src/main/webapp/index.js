const searchResultsIndex = ["Songs", "Albums"];

function onLoadFunc() {
    addAlbumsToDropDown().then((res) => {
        //console.log("added albums to dropdown");
    });
}

function addAlbumsToDropDown() {
    return getAll("albums").then((res) => {
        let albums = JSON.parse(res.responseText);

        for (let i = 0; i < albums.length; i++) {
            let a = albums[i];
            dropDownMaker(a.name);
        }
    });
}

function dropDownMaker(albumName) {
    let drop = document.createElement("li");
    drop.innerHTML = `<li><a tabindex="-1" href="#" class="dropdown-item">${albumName}</a></li>`;

    let divider = document.createElement("div");
    divider.innerHTML = `<div class="dropdown-divider"></div>`;

    document.getElementById("albumdropdown").appendChild(divider);
    document.getElementById("albumdropdown").appendChild(drop);
}