
let modal = document.getElementById("innerModal"); 
let btnModal = document.getElementById("btn__modal");
let span = document.getElementsByClassName("close")[0];
let getPathBtn = document.getElementById("getPath")


btnModal.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

getPathBtn.onclick = function() {
    let path = document.querySelector(".form-control").value;
    if (path==null) {
        path="/"
    }
    console.log(path);
}
