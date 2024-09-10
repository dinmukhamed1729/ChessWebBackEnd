

const form = document.forms.item(0)

form.addEventListener("submit", function (event) {
    console.log("submit")

    let name = document.getElementById("name").value,
        email = document.getElementById("email").value,
        password = document.getElementById("password").value,
        confirmPassword = document.getElementById("confirmPassword").value,
        errorMessage = document.getElementById("errorMessage"),
        confirmPasswordLabel = document.getElementById("confirmPasswordLabel");

    if (password !== confirmPassword) {
        confirmPasswordLabel.style.display = "none"
        errorMessage.style.display = "block"
        return
    }
    confirmPasswordLabel.style.display = "block"
    errorMessage.style.display = "none"

    fetch(`${window.location.origin}/registration`, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify({
            name,
            email,
            password,
        })
    }).then(response =>{
        console.log(response.status)
       return  response.text()

    } )
        .then(data => console.log(data))
        .catch(err => console.error(err));
})
