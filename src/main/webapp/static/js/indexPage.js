let indexType = document.querySelectorAll(".searchType");


for (let i = 0; i < indexType.length; i++) {
    indexType[i].addEventListener('click',function () {
        console.log(indexType[i].dataset.id)
        console.log(indexType[i].dataset.index)
        window.location.replace(`http://localhost:8080/?type=${indexType[i].dataset.index}&id=${indexType[i].dataset.id}&head=${indexType[i].innerHTML}`);
    });}



function printer(indexType) {
    console.log(indexType);
}





