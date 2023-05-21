function eliminarPaciente(id){
    swal({
        title: "Esta seguro de eliminar?",
        text: "Una vez eliminado, no podras recuperar la informacion!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url:"/centro-salud/palmeritas/pacientes/" + id + "/eliminar",
                    success: function (respuesta){
                        console.log(respuesta);
                    }
                })
                swal("Poof! Paciente ha sido eliminado!", {
                    icon: "success",
                }).then((OK)=>{
                    if(OK){
                        location.href="/centro-salud/palmeritas/pacientes/";
                    }
                });
            } else {
                swal("Paciente ha sido guardado!");
            }
        });
}

function eliminarMedica(id){
    swal({
        title: "Esta seguro de eliminar?",
        text: "Una vez eliminado, no podras recuperar la informacion!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url:"/centro-salud/palmeritas/medicas/" + id + "/eliminar",
                    success: function (respuesta){
                        console.log(respuesta);
                    }
                })
                swal("Poof! Medica ha sido eliminada!", {
                    icon: "success",
                }).then((OK)=>{
                    if(OK){
                        location.href="/centro-salud/palmeritas/medicas/";
                    }
                });
            } else {
                swal("Medica ha sido guardado!");
            }
        });
}

function eliminarIngreso(id){
    swal({
        title: "Esta seguro de eliminar?",
        text: "Una vez eliminado, no podras recuperar la informacion!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url:"/centro-salud/palmeritas/ingresos/" + id + "/eliminar",
                    success: function (respuesta){
                        console.log(respuesta);
                    }
                })
                swal("Poof! Ingreso ha sido eliminado!", {
                    icon: "success",
                }).then((OK)=>{
                    if(OK){
                        location.href="/centro-salud/palmeritas/ingresos/";
                    }
                });
            } else {
                swal("Ingreso ha sido guardado!");
            }
        });
}