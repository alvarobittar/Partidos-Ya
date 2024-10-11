// Call the dataTables jQuery plugin
$(document).ready(function() {

  cargarUsers();

  $('#users').DataTable();
});

async function cargarUsers() {


  const request = await fetch('USER/{id}', {           //utiliza el await para esperar la respuesta del servidor
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const users = await request.json();

  console.log(users);

    let listadoHTML = '';
    for (let user of users) {
    }

    let userHTML = <tr> <td>${user.id}</td> <td>${user.nombre}</td> <td>${user.email}</td> <td>${user.password}</td></tr>;

    listadoHTML += userHTML;

    document.querySelector('#users tbody').innerHTML = listadoHTML;


}


      //<button class="btn btn-danger" onclick="eliminarUser(${user.id})">Eliminar</button>