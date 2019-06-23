const baseUrl =  "/KingGizzAPI/api/"; //http://34.90.83.75:8888/KingGizzAPI/api/"; //  //"http://localhost:8080/KingGizzAPI/api/"; http://35.246.178.250:8888/

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
}

function get(object, id) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    if (objectEnd.charAt(0) === 'A' || objectEnd.charAt(0) === 'E' || objectEnd.charAt(0) === 'I' ||
        objectEnd.charAt(0) === 'O' || objectEnd.charAt(0) === 'U') { //if object starts with a vowel...
        objectEnd = 'n' + objectEnd;                                  //...change 'get A ___' to 'get An ___'
    }
    return makeRequest("GET", `${baseUrl}${object}s/getA${objectEnd}/${id.value}`);
}

function create(object, sendData) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    return makeRequest("POST", `${baseUrl}${object}s/create${objectEnd}`, sendData)
    .then((res) => {
        console.log(res.responseText);
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}

function update(object, id, sendData) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    return makeRequest("PUT",  `${baseUrl}${object}s/update${objectEnd}/${id}`, sendData);
    // .then((req) => {
    //     console.log("It worked!");
    // }).catch(() => { console.log("Didn't work") });
}

function deleteFunc(object, id){
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    return makeRequest("DELETE",  `${baseUrl}${object}s/delete${objectEnd}/${id}`).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}


function goTo(address) {
    window.location.href = `${address}.html`;
}

function removeAllChildren(id) {
    let result = document.getElementById(id);
    while (result.hasChildNodes()) {
        result.removeChild(result.firstChild);
    }
}