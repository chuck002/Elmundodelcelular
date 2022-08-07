
$(document).on('change', '#idMarcas', function(event) {

    $.ajax({
        url: '/elmundodelcelular/home/search/'+$("#idMarcas option:selected").val(),
        type: "POST",
        data: '',
        success: function(response){
            $('#celularCard').empty();
            let data = response.celulares;
            let nuevaCard = '';
            for(let i = 0 ; i < data.length ; i++){

            nuevaCard = '<div class="col-lg-4 col-md-6 mb-4" id='+data[i][4]+'>'+
            '<div class="card h-100"> '+
            '  <a><img class="card-img-top" src="'+data[i][1]+'" alt="'+data[i][2]+'" id="celularImg"></a>'+
            '        <div class="card-body"> '+
            '        <h4 class="card-title">'+
            '            <a href=""></a>'+
            '          </h4>'+
            '          <h4 class="card-title">'+
            '            <h5><span id="celularTitle">'+ data[i][2]+'</span></h5>'+
            '            <a href=""></a>'+
            '          </h4>'+
            '          <h6>Precio: <span id="celularPrice">'+ data[i][3] +'</span></h6>'+
            '          <h6>Marca: <span id="celularBrand">'+ data[i][0] +'</span></h6>'+
            '          <p class="card-text"></p>'+
            '        </div>'+
            '        <div class="card-footer">'+
            '          <small class="text-muted">'+
            '          </small>'+
            '        </div>'+
            '    </div>'+
            '</div>'

            $('#celularCard').append(nuevaCard);
            }

            
            console.log(response.celulares[0][3]);
        }
    }
        


    )
    console.log('valor ---> ',$("#idMarcas option:selected").val());
});