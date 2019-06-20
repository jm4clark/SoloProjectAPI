const baseUrl = "http://localhost:8080/KingGizzAPI/api/"; //"http://34.77.53.12:8888/KingGizzAPI/api/";

function makeRequest(requestType, url, sendData) {
    return new Promise((res, rej) => {
        let req = new XMLHttpRequest();
        req.onload = () => {
            if (req.status >= 200 && req.status <= 299) {
                res(req);
            } else {
                rej(req);
            }
        }
        req.open(requestType, url);

        req.send(sendData);
    });
}


function getAll(object) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    return makeRequest("GET", `${baseUrl}${object}/getAll${objectEnd}`);
    // .then((req) => {
    //     console.log(`${baseUrl}${object}/getAll${objectEnd}`);
    //     console.log("It worked!");
    //     return req.responseText;        
    // }).catch(() => { console.log("Didn't work") });

}

function get(object, id) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    if (objectEnd.charAt(0) === 'A' || objectEnd.charAt(0) === 'E' || objectEnd.charAt(0) === 'I' || 
            objectEnd.charAt(0) === 'O' || objectEnd.charAt(0) === 'U') { //if object starts with a vowel...
        objectEnd = 'n' + objectEnd;                                      //...change 'get A ___' to 'get An ___'
    }
    makeRequest("GET", `${baseUrl}${object}s/getA${objectEnd}/${id.value}`).then((req) => {
        console.log("It worked!");
        console.log(req.responseText);
        let a = JSON.parse(req.responseText);
        document.getElementById("albumName").innerText = a.name;
        document.getElementById("albumArt").src = a.albumArtLink;
    }).catch(() => { console.log("Didn't work") });
}

function create(object, sendData) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    makeRequest("POST", `${baseUrl}${object}/create${objectEnd}`, sendData).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}


function goTo(address) {
    window.location.href = `${address}.html`;
}

function fadeOut() {

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