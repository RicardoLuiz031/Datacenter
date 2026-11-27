// ===== Configuração =====
// Endpoint que devolve a lista de acessos (ajuste para a rota do seu backend).
const URL_RELATORIO = "/api/acessos";

// Salas: chave (usada em ?sala=), título, trecho que identifica a área no JSON e página da sala.
const SALAS = {
    servidor: { titulo: "Sala de Servidor", trecho: "servidor", pagina: "./servidor/sala-servidor.html" },
    rede:     { titulo: "Sala de Rede",     trecho: "rede",     pagina: "./rede/sala-rede.html" },
    energia:  { titulo: "Sala de Energia",  trecho: "energia",  pagina: "./energia/sala-energia.html" }
};

let acessosDaSala = [];

// Remove acentos e deixa minúsculo para comparar nomes de área.
function normalizar(texto) {
    return (texto || "").normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
}

function formatarData(iso) {
    const data = new Date(iso);
    if (isNaN(data)) return iso || "-";
    return data.toLocaleString("pt-BR", { dateStyle: "short", timeStyle: "medium" });
}

function criarCelula(texto, classe) {
    const td = document.createElement("td");
    td.textContent = texto;
    if (classe) td.className = classe;
    return td;
}

function criarSelo(resultado) {
    const td = document.createElement("td");
    const selo = document.createElement("span");
    const valor = normalizar(resultado);
    selo.className = "status status-" + valor;
    selo.textContent = valor ? valor.charAt(0).toUpperCase() + valor.slice(1) : "-";
    td.appendChild(selo);
    return td;
}

function mostrarMensagem(texto) {
    const mensagem = document.getElementById("mensagem");
    mensagem.textContent = texto;
    mensagem.className = texto ? "erro" : "";
}

function atualizarResumo() {
    const permitidos = acessosDaSala.filter(a => normalizar(a.resultado) === "permitido").length;
    document.getElementById("total").textContent = acessosDaSala.length;
    document.getElementById("total-permitidos").textContent = permitidos;
    document.getElementById("total-negados").textContent = acessosDaSala.length - permitidos;
}

function desenharTabela() {
    const filtro = document.getElementById("filtro-resultado").value;
    const corpo = document.getElementById("tabela-acessos");
    corpo.innerHTML = "";

    const linhas = acessosDaSala.filter(a => !filtro || normalizar(a.resultado) === filtro);

    if (linhas.length === 0) {
        const tr = document.createElement("tr");
        const td = criarCelula("Nenhum acesso encontrado.", "vazio");
        td.colSpan = 7;
        tr.appendChild(td);
        corpo.appendChild(tr);
        return;
    }

    linhas.forEach(acesso => {
        const tr = document.createElement("tr");
        tr.appendChild(criarCelula(formatarData(acesso.dataAcesso)));
        tr.appendChild(criarCelula(acesso.nomeUsuario || "-"));
        tr.appendChild(criarCelula(acesso.loginUsuario || "-"));
        tr.appendChild(criarCelula(acesso.tipoUsuario || "-"));
        tr.appendChild(criarCelula(acesso.tipoMovimento || "-", "capitalizar"));
        tr.appendChild(criarSelo(acesso.resultado));
        tr.appendChild(criarCelula(acesso.motivoNegacao || "-", "motivo"));
        corpo.appendChild(tr);
    });
}

document.addEventListener("DOMContentLoaded", async () => {
    const chave = new URLSearchParams(location.search).get("sala");
    const sala = SALAS[chave];

    if (!sala) {
        document.getElementById("tabela-acessos").innerHTML = "";
        mostrarMensagem("Sala não informada. Abra o relatório a partir de uma sala.");
        return;
    }

    document.title = "Relatório - " + sala.titulo;
    document.getElementById("titulo-relatorio").textContent = "Relatório - " + sala.titulo;
    const voltar = document.getElementById("botao-voltar");
    voltar.href = sala.pagina;
    voltar.textContent = "Voltar para a sala";

    document.getElementById("filtro-resultado").addEventListener("change", desenharTabela);

    try {
        const resposta = await fetch(URL_RELATORIO, { credentials: "include" });
        if (!resposta.ok) throw new Error("Status " + resposta.status);

        const todos = await resposta.json();

        // Só os acessos desta sala, do mais recente para o mais antigo
        acessosDaSala = todos
            .filter(a => normalizar(a.areaAcessada).includes(sala.trecho))
            .sort((a, b) => new Date(b.dataAcesso) - new Date(a.dataAcesso));

        atualizarResumo();
        desenharTabela();
    } catch (erro) {
        console.error("Erro ao carregar o relatório:", erro);
        document.getElementById("tabela-acessos").innerHTML = "";
        mostrarMensagem("Não foi possível carregar o relatório.");
    }
});