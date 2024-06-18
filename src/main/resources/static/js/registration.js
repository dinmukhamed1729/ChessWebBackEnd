let submitBtn = document.getElementById("submitButton");
let inputName = document.getElementById("inputName");
let inputEmail = document.getElementById("inputEmail");
let inputPassword = document.getElementById("inputPassword");
let inputConfirmPassword = document.getElementById("inputConfirmPassword");

submitBtn.onclick = function() {
    let vname = inputName.value.trim();
    let vemail = inputEmail.value.trim();
    let vpassword = inputPassword.value;
    let confirmPassword = inputConfirmPassword.value;
    console.log(vname,vemail,vpassword);

    // Сброс предыдущих сообщений об ошибках
    document.getElementById('nameError').textContent = '';
    document.getElementById('emailError').textContent = '';
    document.getElementById('passwordError').textContent = '';
    document.getElementById('confirmPasswordError').textContent = '';

    // Проверка на пустоту имени
    if (vname === '') {
        document.getElementById('nameError').textContent = 'Введите имя';
        document.getElementById('nameError').style.color = 'red';
        return;
    }

    // Проверка на пустоту email
    if (vemail === '') {
        document.getElementById('emailError').textContent = 'Введите email';
        document.getElementById('emailError').style.color = 'red';
        return;
    }

    // Проверка на пустоту пароля
    if (vpassword === '') {
        document.getElementById('passwordError').textContent = 'Введите пароль';
        document.getElementById('passwordError').style.color = 'red';
        return;
    }

    // Проверка на совпадение пароля и подтверждения пароля
    if (vpassword !== confirmPassword) {
        document.getElementById('confirmPasswordError').textContent = 'Пароль и подтверждение пароля не совпадают';
        document.getElementById('confirmPasswordError').style.color = 'red';
        return;
    }

    
    let registrationData = {
        name: vname,
        email: vemail,
        password: vpassword
    };
    console.log(registrationData)
    fetch("http://localhost:8081/registration",{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'},
        body:JSON.stringify(registrationData)
    }).then(response => response.text() )
    .then(data =>   console.log(data))
    .catch(err => console.error(err))
};
