let nuevoPedido = [];
$(document).on('change', '#idMarcas', function (event) {

    $.ajax({
        url: '/elmundodelcelular/home/search/' + $("#idMarcas option:selected").val(),
        type: "POST",
        data: '',
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

});

$(document).on('change', '#equiposOrden', function (event) {

    $.ajax({
        url: '/elmundodelcelular/home/search/' + $("#idMarcas option:selected").val() + '/' + $("#equiposOrden option:selected").val(),
        type: "POST",
        data: '',
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

});

/*$(document).on('click', '.celularCard', function(e) {
    console.log($(this).attr('id'));
    location.replace('/elmundodelcelular/home/search/muestra/'+$(this).attr('id'));
})*/

$(document).on('click', '.celularCardCliente', function (e) {
    console.log($(this).attr('id'));
    $(this).css('border-color', 'red #32a1ce').css('border-width', '1em');
    nuevoPedido.push($(this).attr('id'));
    //location.replace('/elmundodelcelular/home/search/muestra/'+$(this).attr('id'));
    console.log(nuevoPedido);
})

$(document).on('click', '#btnGenerarPedido', function (e) {
    console.log(nuevoPedido);
    $('#modal-pedido').modal('show');

    nuevoPedido.forEach(equipo => {

        console.log('equipo ---> ', equipo);

        $.ajax({
            url: '/elmundodelcelular/cliente/pedido/' + equipo,
            type: "POST",
            data: '',
            success: function (response) {

                console.log('equipo ---> ', response.equipo[0]);

                $('#celularCard').empty();
                let nuevaCard = '';
                nuevaCard =
                    '<tr></tr><th><span th:text="${pedido[0]}">' + response.equipo[0][0] + '</span></th>' +
                    '<th><span th:text="${pedido[1]}">' + response.equipo[0][1] + '</span></th>' +
                    '<th><span th:text="${pedido[2]}">' + response.equipo[0][2] + '</span></th>' +
                    '<th><span th:text="${pedido[3]}">' + response.equipo[0][3] + '</span></th>' +
                    '<th><img th:src="' + response.equipo[0][4] + '" width="100px" height="100px"/></th>' +
                    '<th><span th:text="${pedido[6]}">' + response.equipo[0][5] + '</span></th></tr>';
                $('#filaPedido').append(nuevaCard);
            }
        });

        });

});
    
$(document).on('click', '#btnConfirmaPedido', function (e) {
    $('#modal-pedido').modal('hide');
    console.log($(this).attr('id'));
    location.replace('/elmundodelcelular/home/search/muestra/' + $(this).attr('idUser'));
});

