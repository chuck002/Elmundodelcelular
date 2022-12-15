$(document).on('click', '#btnAddUser', function (e) {
    console.log('btnAdd presionado');
    $('#modal-add-user').modal('show');
})

$(document).on('click', '#btnAddOk', function (e) {

    var fd = new FormData();
    fd.append('file', input.files[0]);

    $.ajax({
        url: '/elmundodelcelular/home/search/' + $("#idMarcas option:selected").val(),
        type: "POST",
        data: {
            user: '1'
        },
        success: function (response) {
            $('#celularCard').empty();
            let data = response.celulares;
            let nuevaCard = '';
            for (let i = 0; i < data.length; i++) {

                nuevaCard = '<div class="col-lg-4 col-md-6 mb-4" id=' + data[i][4] + '>' +
                    '<div class="card h-100 celularCard" id="' + data[i][4] + '"> ' +
                    '  <a><img class="card-img-top" src="' + data[i][1] + '" alt="' + data[i][2] + '" id="celularImg"></a>' +
                    '        <div class="card-body"> ' +
                    '        <h4 class="card-title">' +
                    '          </h4>' +
                    '          <h4 class="card-title">' +
                    '            <h5><span id="celularTitle">' + data[i][2] + '</span></h5>' +
                    '          </h4>' +
                    '          <h6>Precio: $<span id="celularPrice">' + data[i][3] + '</span></h6>' +
                    '          <h6>Marca: <span id="celularBrand">' + data[i][0] + '</span></h6>' +
                    '          <p class="card-text"></p>' +
                    '        </div>' +
                    '        <div class="card-footer">' +
                    '          <small class="text-muted">' +
                    '          </small>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>'

                $('#celularCard').append(nuevaCard);
            }
        }
    })
})

$(document).on('click', '#btnComprasUser', function (e) {
    console.log($(this).attr('id'));
    location.replace('/elmundodelcelular/home/search/muestra/' + $(this).attr('idUser'));
});

$(document).on('click', '#btnEliminarUser', function (e) {
    console.log($(this).attr('idUser'));

    $.ajax({
        url: '/elmundodelcelular/home/user/delete/' + $(this).attr('idUser'),
        type: 'DELETE',
        success: function (response) {
            console.log(response);
            $(this).parent().parent().empty();
        }
    })
});

$(document).on('click', '#btnVerPedido', function (e) {
    console.log($(this).attr('id'));
    location.replace('/elmundodelcelular/home/search/muestra/pedido/' + $(this).attr('idPedido'));
});