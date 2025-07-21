$(document).ready(function () {
    $('#cpf').mask("000.000.000-00");
    $('#cep').mask("00000-000");

    if (!localStorage.pessoas) {
        let pessoas = [];
        localStorage.setItem('pessoas', JSON.stringify(pessoas));
    }
    
    exibirDados();    
});

$("#formulario").validate({
    rules: {
        cpf: {
            required: true,
            minlength: 14
        },
        nome: {
            required: true
        },
        cep: {
            required: true,
            minlength: 9
        },
        logradouro: {
            required: true
        },
        localidade: {
            required: true
        }        

    },
    messages: {
        cpf: {
            required: "Campo obrigatório",
            minlength: "Verifique o preenchimento"
        },
        nome: {
            required: "Campo obrigatório"
        },
        cep: {
            required: "Campo obrigatório",
            minlength: "Verifique o preenchimento"
        },
        logradouro: {
            required: "Campo obrigatório"
        },
        localidade: {
            required: "Campo obrigatório"
        },                                
    }
});


function cadastrar() {
    
    //verifica se o formulário atende as regras de validação do jQuery Validation.
    if ($("#formulario").valid()) {

        var tabela = $("#tabela");

        //recupera o vetor de registros na memória secundária
        var pessoas = JSON.parse(localStorage.getItem('pessoas'));

        var pessoa = new Object();
        pessoa.cpf = $("#cpf").val();
        pessoa.nome = $("#nome").val();        
        pessoa.cep = $("#cep").val();   
        pessoa.logradouro = $("#logradouro").val();   
        pessoa.localidade = $("#localidade").val();   

        if (pessoas.some(p => p.cpf == pessoa.cpf)) {
            alert('O CPF informado já foi cadastrado');
        } else {  
            pessoas.push(pessoa);

            //armazena o vetor na memória secundária
            localStorage.setItem('pessoas', JSON.stringify(pessoas));

            alert('Cadastro realizado com sucesso');

            //limpa os campos do formulário
            $("#cpf").val("");
            $("#nome").val("");
            $("#cep").val("");
            $("#logradouro").val("")
            $("#localidade").val("")

            adicionarLinha(tabela, pessoa)            
        }

    
    }
}

async function preencherEndereco() {
    var cep = $("#cep").val();
    try {
        if(cep.length >= 9){
            var resposta = await fetch("https://viacep.com.br/ws/" + cep + "/json/");
            var endereco = await resposta.json();
            $("#logradouro").val(endereco.logradouro)
            $("#localidade").val(endereco.localidade) 
        }
    } catch (erro) {
        console.error('Erro na requisição:', erro);
    }
}



function exibirDados() {
    var tabela = $("#tabela");
    //apaga todas as linhas do corpo da tabela
    tabela.find("tbody").empty();

    //recupera o vetor de registros na memória secundária
    var pessoas = JSON.parse(localStorage.getItem('pessoas'));

    pessoas.forEach(pessoa => {
        adicionarLinha(tabela, pessoa)
    })
}

function adicionarLinha(tabela, pessoa) {
    var tbody = tabela.find("tbody");
    var linha = $("<tr></tr>");

    $("<td></td>").text(pessoa.cpf).appendTo(linha);
    $("<td></td>").text(pessoa.nome).appendTo(linha);
    $("<td></td>").text(pessoa.localidade).appendTo(linha);

    tbody.append(linha);
}