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

function onPressGetAll(object) {
    let res = getAll(object);

    console.log(res.responseText);
}

function get(object, id) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    makeRequest("GET", `http://localhost:8080/KingGizzAPI/api/${object}s/getA${objectEnd}/${id.value}`).then((req) => {
        console.log("It worked!");
        console.log(req.responseText);
    }).catch(() => { console.log("Didn't work") });
}

function getAll(object) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    makeRequest("GET", `http://localhost:8080/KingGizzAPI/api/${object}/getAll${objectEnd}`).then((req) => {
        console.log("It worked!");
        return req.responseText;
    }).catch(() => { console.log("Didn't work") });
}

function create(object, sendData) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    makeRequest("POST", `http://localhost:8080/KingGizzAPI/api/${object}/create${objectEnd}`, sendData).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}


function goTo(address) {
    window.location.href=`${address}.html`;
}
