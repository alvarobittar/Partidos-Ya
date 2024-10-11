async function registrarUsers() {
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirPassword = document.getElementById('txtRepetirPassword').value;

    if (datos.password !== repetirPassword) {
        alert('Las contraseñas no coinciden');
        return;
    }

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