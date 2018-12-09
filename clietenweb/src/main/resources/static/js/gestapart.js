

/**
 * Genera los campos de username y passwd y los carga en el formulario
 */
function generarCampos() {
    var nombre = document.getElementById("nombre");
    var apellidos = document.getElementById("apellidos");
    if (nombre.value === "") {
        alert("Falta nombre de usuario");
        return;
    }
    if (apellidos.value === "") {
        alert("Faltan apellidos del usuario");
        return;
    }
    $.ajax({
        url: '/usuarios/campos',
        data: {nombre: nombre.value, apellidos: apellidos.value},
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data, textStatus, xhr) {
            // alert(xhr.status);
            var usuario = document.getElementById('username');
            var passwd = document.getElementById('passwd');
            usuario.value = data[0];
            passwd.value = data[1];
            console.log(data)

        },
        error: function (data, textStatus, xhr) {
            alert("Error inesperado, intentelo mas tarde");

        }
    })

}

/**
 * Cambiar la contraseña del usuario en la BBDD
 */
function cambiarPasswd() {

    var pass_actual = $("#passwd_actual").val();
    var pass_nueva = $("#passwd_nueva").val();
    var pass_repe = $("#passwd_repetir").val();

    document.getElementById("error_actual").innerHTML = " ";
    document.getElementById("error_nueva").innerHTML = " ";
    document.getElementById("error_repetir").innerHTML = " ";

    $.ajax({
        url: '/usuarios/passwd',
        data: JSON.stringify({passwdActual: pass_actual, passwdNueva: pass_nueva, passwdRepetida: pass_repe}),
        type: 'POST',
        contentType: 'application/json; charset=utf-8',

        success: function (data, textStatus, xhr) {
            // $("#error_nueva").innerHTML = "  ";
            // $("#error_repetir").innerHTML = "  ";
            // $("#error_actual").innerHTML = "  ";
            // $("#passwd_actual").innerText = " ";
            // $("#passwd_nueva").innerText = " ";
            // $("#passwd_repetir").innerText = "";
            alert("Cambio de contraseña realizado!");
            window.location.href = "/home";
        },
        error: function (data, textStatus, xhr) {
            if (data.status === 400) {
                document.getElementById("error_actual").innerHTML = "<i class=\"fas fa-thumbs-down\" style=\"color:red\"></i> ";
                document.getElementById("error_nueva").innerHTML = "<i class=\"fas fa-thumbs-down\" style=\"color:red\"></i> ";
                alert("La contraseña actual y la nueva NO deben ser iguales!");
            } else if (data.status === 406) {
                document.getElementById("error_nueva").innerHTML = "<i class=\"fas fa-thumbs-down\" style=\"color:red\"></i> ";
                alert("Nueva contraseña no cumple los requisitos minimos!!!");
            } else if (data.status === 409) {
                document.getElementById("error_actual").innerHTML = "<i class=\"fas fa-thumbs-down\" style=\"color:red\"></i> ";
                alert("La contraseña actual NO ES CORRECTA!");
            } else if (data.status === 412) {
                document.getElementById("error_nueva").innerHTML = "<i class=\"fas fa-thumbs-down\" style=\"color:red\"></i> ";
                document.getElementById("error_repetir").innerHTML = "<i class=\"fas fa-thumbs-down\" style=\"color:red\"></i> ";
                alert("La contraseña nueva y su repeticion, NO COINCIDEN!");
            } else {
                alert("Error del servicio!")
            }
        }
    })
}

/**
 * Cargar datos del usuario de la fila seleccionada al modal.
 * @param fila - la fila seleccionada
 */
function cargarModalListaUsuarios(fila, origen) {
    var td_idUsuario = fila.children[0].innerText;
    var td_foto = fila.children[1].querySelector('img').getAttribute('src');
    var td_username = fila.children[2].innerText;
    var td_perfil = fila.children[3].innerText;
    var td_nombre = fila.children[4].innerText;
    var td_apellidos = fila.children[5].innerText;

    var usuario = {
        idUsuario: td_idUsuario,
        foto: td_foto,
        nombre: td_nombre,
        apellidos: td_apellidos,
        username: td_username,
        perfil: td_perfil
    };
    $('#myModal').modal('show');
    $('#idUsuario').val(usuario.idUsuario);
    $('#foto_preview').attr("src", usuario.foto);
    $('#foto_fantasma').val(usuario.foto);
    $('#nombre').val(usuario.nombre);
    $('#apellidos').val(usuario.apellidos);
    $('#username').val(usuario.username);
    $('#perfil').val(usuario.perfil);
    $('#passwd').val("*privada*");
    $('#origen').val(origen);
}

/**
 * Cargar datos del apartamento de la fila seleccionada al modal.
 * @param fila - la fila seleccionada
 */
function cargarModalListaApartamentos(fila, origen) {
    var td_idApartamento = fila.children[0].innerText;
    var td_foto = fila.children[1].querySelector('img').getAttribute('src');
    var td_direccion = fila.children[2].innerText;
    var td_capacidad = fila.children[3].getAttribute("title");
    var td_piso = fila.children[4].innerText;
    var td_puerta = fila.children[5].innerText;
    var td_propietario = fila.children[6].getAttribute("title");
    var td_huesped = fila.children[7].getAttribute("title");
    var td_disponible;

    if (td_huesped == null){
        console.log("huesped es null:"+td_huesped);
        td_disponible = true;
    } else{
        console.log("huesped No es null: "+td_huesped);
        td_disponible = false;
    }

    var apartamento = {
        idApartamento: td_idApartamento,
        foto: td_foto,
        direccion: td_direccion,
        capacidad: td_capacidad,
        piso: td_piso,
        puerta: td_puerta,
        propietario: td_propietario,
        disponible: td_disponible
    };

    $('#myModal').modal('show');
    $('#idApartamento').val(apartamento.idApartamento);
    $('#foto_preview').attr("src", apartamento.foto);
    $('#foto_fantasma').val(apartamento.foto);
    $('#selector_capacidad').val(apartamento.capacidad);
    $('#direccion').val(apartamento.direccion);
    $('#piso').val(apartamento.piso);
    $('#puerta').val(apartamento.puerta);
    $('#select_propietario').val(apartamento.propietario);
    if (apartamento.disponible){
        $('#btn_disponible').attr('checked', 'checked');
    }else {
        $('#btn_nodisponible').attr('checked', 'checked');
    }
    $('#origen').val(origen);
    $('#huesped').val(td_huesped);

    var nombre = $('#nombre');
    var apellidos = $('#apellidos');
    var telefono = $('#telefono');
    var email = $('#email');
    var selectorCapacidad = $('#selector_capacidad');
    nombre.prop('disabled', true);
    apellidos.prop('disabled', true);
    telefono.prop('disabled', true);
    email.prop('disabled', true);
    $.ajax({
        url: '/apartamentos/propietarios/'+apartamento.propietario,
        type: 'GET',
        success: function (data, textStatus, xhr) {
            nombre.attr("placeholder", data.nombre);
            nombre.val("");
            apellidos.attr("placeholder",data.apellidos);
            apellidos.val("");
            telefono.attr("placeholder",data.telefono);
            telefono.val("");
            email.attr("placeholder",data.email);
            email.val("");
        },
        error: function (data, textStatus, xhr) {
        }
    })
    $.ajax({
        url: '/apartamentos/capacidades/'+apartamento.capacidad,
        type: 'GET',
        success: function (data, textStatus, xhr) {
            selectorCapacidad.val(data.idCapacidad);
        },
        error: function (data, textStatus, xhr) {
        }
    })

}

/**
 * Cargar datos del apartamento de la fila seleccionada al modal.
 * @param fila - la fila seleccionada
 */
function cargarModalAsignacionApartamentos(fila, origen) {
    var td_idApartamento = fila.children[0].innerText;
    var td_foto = fila.children[1].querySelector('img').getAttribute('src');
    var td_direccion = fila.children[2].innerText;
    var td_capacidad = fila.children[3].getAttribute("title");
    var td_piso = fila.children[4].innerText;
    var td_puerta = fila.children[5].innerText;
    var td_propietario = fila.children[6].getAttribute("title");
    var td_disponible = fila.children[7].getAttribute("title");

    var apartamento = {
        idApartamento: td_idApartamento,
        foto: td_foto,
        direccion: td_direccion,
        capacidad: td_capacidad,
        piso: td_piso,
        puerta: td_puerta,
        propietario: td_propietario,
        disponible: td_disponible
    };

    $('#myModal').modal('show');
    $('#idApartamento').val(apartamento.idApartamento);
    $('#foto_preview').attr("src", apartamento.foto);
    $('#foto_fantasma').val(apartamento.foto);
    $('#selector_capacidad').val(apartamento.capacidad);
    $('#direccion').val(apartamento.direccion);
    $('#piso').val(apartamento.piso);
    $('#puerta').val(apartamento.puerta);
    $('#select_propietario').val(apartamento.propietario);
    if (apartamento.disponible == "true"){
        $('#btn_disponible').attr('checked', 'checked');
    }else {
        $('#btn_nodisponible').attr('checked', 'checked');
    }
    $('#origen').val(origen);

    var nombre = $('#nombre');
    var apellidos = $('#apellidos');
    var telefono = $('#telefono');
    var email = $('#email');
    var selectorCapacidad = $('#selector_capacidad');
    nombre.prop('disabled', true);
    apellidos.prop('disabled', true);
    telefono.prop('disabled', true);
    email.prop('disabled', true);
    $.ajax({
        url: '/apartamentos/propietarios/'+apartamento.propietario,
        type: 'GET',
        success: function (data, textStatus, xhr) {
            nombre.attr("placeholder", data.nombre);
            nombre.val("");
            apellidos.attr("placeholder",data.apellidos);
            apellidos.val("");
            telefono.attr("placeholder",data.telefono);
            telefono.val("");
            email.attr("placeholder",data.email);
            email.val("");
        },
        error: function (data, textStatus, xhr) {
        }
    })
    $.ajax({
        url: '/apartamentos/capacidades/'+apartamento.capacidad,
        type: 'GET',
        success: function (data, textStatus, xhr) {
            selectorCapacidad.val(data.idCapacidad);
        },
        error: function (data, textStatus, xhr) {
        }
    })

}

/**
 * Sript para previsualizar la foto del usuario/apartamento antes de crear o modificarlo
 * @param event
 */
function preview_image(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById("foto_preview");
        console.log(output);
        // console.log(reader.result);
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}

/**
 * Funcion para añadir de forma automatica los datos del propietario si lo eliges desde el selector y en caso de "Añadir nuevo" puedas
 * introducirlos de forma manual.
 */
function gestionNuevoApartamento() {
    var nombre = $('#nombre');
    var apellidos = $('#apellidos');
    var telefono = $('#telefono');
    var email = $('#email');
    var selector = $('#select_propietario');
    selector.on('change', function (e) {
        if (this.value === 'default') {
            nombre.prop('disabled', false);
            nombre.attr("placeholder", "Nombre");
            apellidos.prop('disabled', false);
            apellidos.attr("placeholder","Apellidos");
            telefono.prop('disabled', false);
            telefono.attr("placeholder","Telefono");
            email.prop('disabled', false);
            email.attr("placeholder","Email");

        } else {
            nombre.prop('disabled', true);
            apellidos.prop('disabled', true);
            telefono.prop('disabled', true);
            email.prop('disabled', true);
            $.ajax({
                url: '/apartamentos/propietarios/'+selector.val(),
                type: 'GET',
                success: function (data, textStatus, xhr) {
                    // console.log(data);
                    // console.log(textStatus);
                    // console.log(xhr);
                    nombre.attr("placeholder", data.nombre);
                    nombre.val("");
                    apellidos.attr("placeholder",data.apellidos);
                    apellidos.val("");
                    telefono.attr("placeholder",data.telefono);
                    telefono.val("");
                    email.attr("placeholder",data.email);
                    email.val("");
                },
                error: function (data, textStatus, xhr) {
                    // console.log(data);
                    // console.log(textStatus);
                    // console.log(xhr);
                }
            })
        }

    });


}

/**
 * Eliminar usuario de la BBDD
 */
// function eliminarUsuario() {
//     // var entrada = document.getElementById("resultados").rows[1].cells[0].innerHTML;
//     var idUsuario = document.getElementById("idUsuario");
//     console.log(idUsuario.value);
//     // console.log(entrada[0].value);
//     $.ajax({
//         url: '/eliminarUsuario/' + idUsuario.value,
//         type: 'DELETE',
//         contentType: 'application/json; charset=utf-8',
//         dataType: "json",
//         success: function (data, textStatus, xhr) {
//         },
//         error: function (data, textStatus, xhr) {
//             var titulo = document.getElementById('titulo');
//             console.log(titulo.innerText);
//             if (titulo.innerText === "Buscar usuario") {
//                 $('#myModal').modal('hide');
//                 $('#tb').empty();
//             } else {
//                 alert("USUARIO ELIMINADO");
//                 $('#myModal').modal('hide');
//                 $('body').removeClass('modal-open');
//                 $('.modal-backdrop').remove();
//                 cargarCodigo('/usuariosLista');
//             }
//
//
//         }
//     })
// }

/**
 * Crea un nuevo usuario recogiendo los datos del formulario
 */
// function comprobarCamposNuevoUsuario() {
//     var boton = document.getElementById("btn_crear");
//     var idUsuario = null;
//     var nombre = document.getElementById("nombre");
//     var apellidos = document.getElementById("apellidos");
//     var username = document.getElementById("username");
//     var passwd = document.getElementById("passwd");
//     var selectorPerfil = document.getElementById("perfil");
//     var perfil = selectorPerfil.options[selectorPerfil.selectedIndex];
//     var files = document.querySelector('[type=file]').files;
//     var formData = new FormData();
//     for (var i = 0; i < files.length; i++) {
//         var file = files[i];
//         formData.append('files[]', file);
//     }
//     var usuario = {
//         idUsuario: idUsuario,
//         nombre: nombre.value,
//         apellidos: apellidos.value,
//         username: username.value,
//         passwd: passwd.value,
//         perfil: perfil.value,
//         foto: formData
//     };
//
//
//
//     console.log(usuario);
//
//     if (nombre.value == "") {
//         nombre.style.backgroundColor = "yellow";
//     }
//     else{
//         nombre.style.backgroundColor = "";
//     }
//     if (usuario.apellidos === "") {
//         // alert("Faltan apellidos del usuario");
//         return;
//     }
//     if (usuario.username === "") {
//         // alert("Falta generar campos profesionales");
//         return;
//     }
//     if (usuario.perfil === "default") {
//         // alert("Falta perfil del usuario");
//         return;
//     }
//     boton.onmousedown = function (ev) {
//         document.getElementById("formulario_nuevo_usuario").setAttribute("action","/crearUsuario");
//         console.log(usuario)
//
//     }
//
//     $.ajax({
//         url: '/crearUsuario',
//         data: JSON.stringify(usuario),
//         type: 'POST',
//         contentType: 'application/json; charset=utf-8',
//         dataType: "json",
//         success: function (data, textStatus, xhr) {
//
//         },
//         error: function (data, textStatus, xhr) {
//             if (textStatus.toString() === 'parsererror') {
//                 console.log("data: " + data.responseText);
//                 console.log("textStatus: " + textStatus.toString());
//                 console.log("xhr: " + xhr.toString());
//                 nombre.value = " ";
//                 apellidos.value = " ";
//                 username.value = " ";
//                 passwd.value = " ";
//                 selectorPerfil.options[0].selected = true;
//                 alert("Usuario creado de forma correcta");
//             } else {
//                 alert("No se ha podido crear al usuario")
//             }
//
//         }
//     })
//
// }

/**
 * Busca un usuario por username o id y carga los datos en caso de seleccionarle
 */
// function buscarUsuario() {
//     var entrada = document.getElementById("entrada");
//     var tableRef = document.getElementById('resultados').getElementsByTagName('tbody')[0];
//
//     $.ajax({
//         url: '/buscarUsuario',
//         data: entrada.value,
//         type: 'POST',
//         contentType: 'application/json; charset=utf-8',
//         dataType: "json",
//         success: function (data, textStatus, xhr) {
//             if (data == undefined) {
//                 alert("USUARIO NO ENCONTRADO");
//             } else {
//                 $('#tb').empty()
//                 var newRow = tableRef.insertRow(tableRef.rows.length);
//
//                 for (var i = 0; i < 6; i++) {
//                     if (i == 0) {
//                         var newCell = newRow.insertCell(i);
//                         var newText = document.createTextNode(data[0].idUsuario);
//                         newCell.appendChild(newText);
//                     }
//                     if (i == 1) {
//                         var newCell = newRow.insertCell(i);
//                         var newText = document.createTextNode("FOTO");
//                         newCell.appendChild(newText);
//                     }
//                     if (i == 2) {
//                         var newCell = newRow.insertCell(i);
//                         var newText = document.createTextNode(data[0].username);
//                         newCell.appendChild(newText);
//                     }
//                     if (i == 3) {
//                         var newCell = newRow.insertCell(i);
//                         var newText = document.createTextNode(data[0].perfil);
//                         newCell.appendChild(newText);
//                     }
//                     if (i == 4) {
//                         var newCell = newRow.insertCell(i);
//                         var newText = document.createTextNode(data[0].nombre);
//                         newCell.appendChild(newText);
//                     }
//                     if (i == 5) {
//                         var newCell = newRow.insertCell(i);
//                         var newText = document.createTextNode(data[0].apellidos);
//                         newCell.appendChild(newText);
//                     }
//                 }
//                 newRow.onclick = function (ev) {
//                     cargarModalListaUsuarios(this);
//                 }
//             }
//             console.log("Esto es el DATA: " + JSON.stringify(data[0]));
//         },
//         error: function (data, textStatus, xhr) {
//             console.log("USUARIO NO ENCONTRADO")
//         }
//     })
//
// }

/**
 * Modificar usuario de la BBDD
 */
// function modificarUsuario() {
//     var idUsuario = document.getElementById("idUsuario");
//     var nombre = document.getElementById("nombre");
//     var apellidos = document.getElementById("apellidos");
//     var username = document.getElementById("username");
//     var selectorPerfil = document.getElementById("perfil");
//     var perfil = selectorPerfil.options[selectorPerfil.selectedIndex];
//
//     var usuario = {
//         idUsuario: idUsuario.value,
//         nombre: nombre.value,
//         apellidos: apellidos.value,
//         username: username.value,
//         passwd: passwd.value,
//         perfil: perfil.value
//     };
//
//     console.log(usuario)
//
//     $.ajax({
//         url: '/modificarUsuario',
//         data: JSON.stringify(usuario),
//         type: 'PUT',
//         contentType: 'application/json; charset=utf-8',
//         dataType: "json",
//         success: function (data, textStatus, xhr) {
//             // console.log(xhr.status);
//             alert("Usuario modificado de forma correcta")
//             // borrar los datos del formulario para que queden en blanco, y ya lo usamos la funcion para el reset
//         },
//         error: function (data, textStatus, xhr) {
//             // console.log(data.responseText);
//             alert("Usuario modificado de forma correcta")
//             $('#myModal').modal('hide');
//             $('body').removeClass('modal-open');
//             $('.modal-backdrop').remove();
//             cargarCodigo('/usuariosLista');
//         }
//     })
//
// }

/**
 * Carga el código HTML de una página externa en el div etiquetado como "#contenido"
 * @param url - direccion de la página a cargar
 */
// function cargarCodigo(url) {
//     $("#contenido").load(url);
// }
