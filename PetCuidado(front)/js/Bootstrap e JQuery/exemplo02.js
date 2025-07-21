$(document).ready(function () {
    $('#iteracoes').mask("#0", { reverse: true });
});

$("#formulario").validate({
    rules: {
        iteracoes: {
            required: true,
            min: 0
        }
    },
    messages: {
        iteracoes: {
            required: "Campo obrigatório",
            min: "Valor mínimo é 0"
        }
    }
});


function executarSincrono() {
    //verifica se o formulário atende as regras de validação do jQuery Validation.
    if ($("#formulario").valid()) {

        var iteracoes = parseInt($("#iteracoes").val());
        $('#situacao').val("Executando (Síncrono)...");
        $('#contador').val("")

        for (let i = 1; i <= iteracoes; i++) {
            if (i % 100 == 0) {
                $('#contador').val(i)
            }
        }

        $('#situacao').val("Finalizado (Síncrono)");
    }
}

async function executarAssincrono() {
    //verifica se o formulário atende as regras de validação do jQuery Validation.
    if ($("#formulario").valid()) {

        var iteracoes = parseInt($("#iteracoes").val());
        $('#situacao').val("Executando (Assíncrono)...");
        $('#contador').val("")

        for (let i = 1; i <= iteracoes; i++) {
            if (i % 100 == 0) {
                $('#contador').val(i)
                await new Promise(resolve => setTimeout(resolve, 0)); // Libera o event loop
            }
        }

        $('#situacao').val("Finalizado (Assíncrono)");

    }
}
