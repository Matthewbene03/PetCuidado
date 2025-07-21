$(document).ready(function () {
    $('#nota').mask('000');
    $('#cpf').mask('000.000.000-00');

    if (!localStorage.alunos) {
        let alunos = [];
        localStorage.setItem('alunos', JSON.stringify(alunos));
    }

    exibirTodos();
});

$("#formulario").validate({
    rules: {
        cpf: {
            required: true
        },
        nome: {
            required: true
        },
        curso: {
            required: true
        },
        nota: {
            required: true,
            min: 0,
            max: 100
        }
    },
    messages: {
        cpf: {
            required: "Campo obrigatório"
        },
        nome: {
            required: "Campo obrigatório"
        },
        curso: {
            required: "Campo obrigatório"
        },
        nota: {
            required: "Campo obrigatório",
            min: "Valor mínimo é 0",
            max: "Valor máximo é 100"
        }
    }
});


function cadastrar() {
    //verifica se o formulário atende as regras de validação do jQuery Validation.
    if ($("#formulario").valid()) {

        var tabela = $("#tabela");

        //recupera o vetor de registros na memória secundária
        var alunos = JSON.parse(localStorage.getItem('alunos'));

        var aluno = new Object();
        aluno.cpf = $("#cpf").val();
        aluno.nome = $("#nome").val();
        aluno.curso = $("#curso").val();
        aluno.nota = parseInt($("#nota").val());
        aluno.situacao = aluno.nota >= 60 ? "Aprovado" : "Reprovado";

        if (alunos.some(a => a.cpf == aluno.cpf)) {
            alert('O CPF informado já foi cadastrado');
        } else {
            alunos.push(aluno);

            //armazena o vetor na memória secundária
            localStorage.setItem('alunos', JSON.stringify(alunos));

            alert('Cadastro realizado com sucesso');

            //limpa os campos do formulário
            $("#cpf").val("");
            $("#nome").val("");
            $("#curso").val("");
            $("#nota").val("")

            adicionarLinha(tabela, aluno)
        }
    }
}

function adicionarLinha(tabela, aluno) {
    var tbody = tabela.find("tbody");
    var linha = $("<tr></tr>");

    $("<td></td>").text(aluno.cpf).appendTo(linha);
    $("<td></td>").text(aluno.nome).appendTo(linha);
    $("<td></td>").text(aluno.curso).appendTo(linha);
    $("<td></td>").text(aluno.nota).appendTo(linha);
    $("<td></td>").text(aluno.situacao).appendTo(linha);

    tbody.append(linha);
}

function consultar() {
    var tabela = $("#tabela");
    //apaga todas as linhas do corpo da tabela
    tabela.find("tbody").empty();

    var criterio = $("#criterio").val();
    var quantidade = 0;

    //recupera o vetor de registros na memória secundária
    var alunos = JSON.parse(localStorage.getItem('alunos'));

    alunos.forEach(aluno => {
        if (aluno.curso == criterio) {
            quantidade++;
            adicionarLinha(tabela, aluno)
        }

    })

    $("#quantidade").val(quantidade);
}

function exibirTodos() {
    var tabela = $("#tabela");
    //apaga todas as linhas do corpo da tabela
    tabela.find("tbody").empty();

    //recupera o vetor de registros na memória secundária
    var alunos = JSON.parse(localStorage.getItem('alunos'));

    alunos.forEach(aluno => {
        adicionarLinha(tabela, aluno)
    })

    $("#quantidade").val("");

}