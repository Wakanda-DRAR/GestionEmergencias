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