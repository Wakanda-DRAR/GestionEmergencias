function showReports() {
    document.getElementById('reports').classList.remove('hidden');
    fetchReports();
}

function fetchReports() {
    fetch('/api/incendios')
        .then(response => response.json())
        .then(data => populateTable('incendiosTable', data));

    fetch('/api/bomberos')
        .then(response => response.json())
        .then(data => populateTable('bomberosTable', data));

    fetch('/api/resolved')
        .then(response => response.json())
        .then(data => populateTable('resolvedTable', data));
}

function fetchPoliciaReports() {
    fetch('/api/delitos')
        .then(response => response.json())
        .then(data => populateTable('delitosTable', data));

    fetch('/api/policias')
        .then(response => response.json())
        .then(data => populateTable('policiasTable', data));

    fetch('/api/resolvedPolicia')
        .then(response => response.json())
        .then(data => populateTable('resolvedPoliciaTable', data));

    fetch('/api/carcel')
        .then(response => response.json())
        .then(data => populateTable('carcelTable', data));
}

function fetchAmbulanciaReports() {
    fetch('/api/ambulancias')
        .then(response => response.json())
        .then(data => populateTable('ambulanciasTable', data));

    fetch('/api/accidentados')
        .then(response => response.json())
        .then(data => populateTable('accidentadosTable', data));

    fetch('/api/resolvedAmbulancia')
        .then(response => response.json())
        .then(data => populateTable('resolvedAmbulanciaTable', data));

    fetch('/api/hospitales')
        .then(response => response.json())
        .then(data => populateTable('hospitalesTable', data));
}

function populateTable(tableId, data) {
    const tableBody = document.getElementById(tableId).getElementsByTagName('tbody')[0];
    tableBody.innerHTML = '';
    data.forEach(item => {
        const row = tableBody.insertRow();
        Object.values(item).forEach(text => {
            const cell = row.insertCell();
            cell.textContent = text;
        });
    });
}

function refreshPage() {
    location.reload();
}