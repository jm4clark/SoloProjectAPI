const baseUrl = "http://34.77.53.12:8888/KingGizzAPI/api";//"http://localhost:8080/KingGizzAPI/api/";

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

    console.log(res);
}

function get(object, id) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    if(objectEnd.charAt(0) === 'a' || objectEnd.charAt(0) === 'e' || objectEnd.charAt(0) === 'i' || objectEnd.charAt(0) === 'o' || objectEnd.charAt(0) === 'u'){ //if object starts with a vowel
        objectEnd = 'n' + objectEnd; //change 'get A ___' to 'get An ___'
    }
    makeRequest("GET", `${baseUrl}${object}s/getA${objectEnd}/${id.value}`).then((req) => {
        console.log("It worked!");
        console.log(req.responseText);
    }).catch(() => { console.log("Didn't work") });
}

function getAll(object) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    return makeRequest("GET", `${baseUrl}${object}/getAll${objectEnd}`).then((req) => {
        console.log(`${baseUrl}${object}/getAll${objectEnd}`);
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });

}

function create(object, sendData) {
    let objectEnd = object.charAt(0).toUpperCase() + object.substring(1);
    makeRequest("POST", `${baseUrl}${object}/create${objectEnd}`, sendData).then((req) => {
        console.log("It worked!");
    }).catch(() => { console.log("Didn't work") });
}


function goTo(address) {
    window.location.href=`${address}.html`;
}

function fadeOut(){

}
