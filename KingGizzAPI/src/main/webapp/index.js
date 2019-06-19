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