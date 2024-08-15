async function registrarUsers() {
    let datos = {};
    datos.nombre = document.getElementById('nombre').value;
    datos.email = document.getElementById('email').value;
    datos.password = document.getElementById('password').value;

    const request = await fetch('/users', { // Updated endpoint URL
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const user = await request.json();
    console.log(user); // Log the registered user
}