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

function get(id) {
    let object = "album";
    let endString = "nAlbum";
    makeRequest("GET", `http://localhost:8080/KingGizzAPI/api/${object}/getA${endString}/${id}`).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}

function getAll() {
    let object = "album";
    makeRequest("GET", `http://localhost:8080/KingGizzAPI/api/${object}/getAll${object}s}/${id}`).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}

function create(object, sendData){
    makeRequest("POST", `http://localhost:8080/KingGizzAPI/api/${object}/create`, sendData).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}

