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

}