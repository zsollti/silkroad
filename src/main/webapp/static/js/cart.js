console.log("adsd")
let totalPrice;
let deleteButtons
let cartButtons
let itemcount
let headerItemCount
let newtotal;
let minusButtons
let plusButtons
let countInputs
document.addEventListener("DOMContentLoaded", function () {
    totalPrice = document.querySelector(".total");
    deleteButtons = document.querySelectorAll(".delete-btn");
    cartButtons = document.querySelectorAll(".add-to-cart");
    plusButtons = document.querySelectorAll(".increase-count-btn");
    minusButtons = document.querySelectorAll(".decrease-count-btn");
    countInputs = document.querySelectorAll(".item-count-input");
    itemcount = document.querySelector("#item-count");
    headerItemCount = document.querySelector("#header-item-count");
    setupAddAndDeleteButtons();
    setupPlusMinusButtons();
    setupCountInput()
});

async function apiGET(url) {
    return await fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });
}

async function setupAddAndDeleteButtons() {
    for (let i = 0; i < cartButtons.length; i++) {
        cartButtons[i].addEventListener('click', async function () {
            let productID = cartButtons[i].dataset['product_id'];
            await apiGET(`/api/cart/add?id=${productID}`);
            newtotal = await (await apiGET(`/api/cart/total`)).json();
            headerItemCount.textContent = await newtotal.count;
        });
    }
    for (let i = 0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener('click', async function () {
            let productID = deleteButtons[i].dataset['product_id'];
            await apiGET(`/api/cart/remove?id=${productID}`);
            deleteButtons[i].parentElement.parentElement.remove();
            newtotal = await (await apiGET(`/api/cart/total`)).json();
            totalPrice.textContent = "Total: " + await newtotal.total + "$";
            itemcount.textContent = await newtotal.count + " Items in cart:";
            headerItemCount.textContent = await newtotal.count;
        });
    }
}

async function setupPlusMinusButtons() {
    for (let i = 0; i < plusButtons.length; i++) {
        plusButtons[i].addEventListener('click', async function () {
            let productID = plusButtons[i].dataset['product_id'];
            await apiGET(`/api/cart/add?id=${productID}`);
            newtotal = await (await apiGET(`/api/cart/total`)).json();
            totalPrice.textContent = "Total: " + await newtotal.total + "$";
            itemcount.textContent = await newtotal.count + " Items in cart:";
            headerItemCount.textContent = await newtotal.count;
            countInputs[i].value = (countInputs[i].value * 1) + 1;
        });
    }
    for (let i = 0; i < minusButtons.length; i++) {
        minusButtons[i].addEventListener('click', async function () {
            let productID = minusButtons[i].dataset['product_id'];
            await apiGET(`/api/cart/decrease?id=${productID}`);
            newtotal = await (await apiGET(`/api/cart/total`)).json();
            totalPrice.textContent = "Total: " + await newtotal.total + "$";
            itemcount.textContent = await newtotal.count + " Items in cart:";
            headerItemCount.textContent = await newtotal.count;
            countInputs[i].value = (countInputs[i].value * 1) - 1;
            if (countInputs[i].value == 0) {
                countInputs[i].parentElement.parentElement.parentElement.parentElement.remove();
            }
        });
    }
}

async function setupCountInput() {
    for (let i = 0; i < countInputs.length; i++) {
        countInputs[i].addEventListener('change', async function () {
            let productID = countInputs[i].dataset['product_id'];
            let count = countInputs[i].value;
            await apiGET(`/api/cart/update?id=${productID}&count=${count}`);
            newtotal = await (await apiGET(`/api/cart/total`)).json();
            totalPrice.textContent = "Total: " + await newtotal.total + "$";
            itemcount.textContent = await newtotal.count + " Items in cart:";
            headerItemCount.textContent = await newtotal.count;
            if (countInputs[i].value == 0) {
                countInputs[i].parentElement.parentElement.parentElement.parentElement.remove();
            }
        });
    }
}