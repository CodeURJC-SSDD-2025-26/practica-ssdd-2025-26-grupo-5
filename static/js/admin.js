function sortTable(columnIndex, tableSelector) {
  let table, rows, switching, i, x, y, shouldSwitch, direction, switchCount = 0;
  table = document.querySelector(tableSelector + " table");
  switching = true;
  direction = "asc";

  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[columnIndex];
      y = rows[i + 1].getElementsByTagName("TD")[columnIndex];

      if (direction === "asc") {
        if (x.innerText.toLowerCase() > y.innerText.toLowerCase()) { shouldSwitch = true; break; }
      } else {
        if (x.innerText.toLowerCase() < y.innerText.toLowerCase()) { shouldSwitch = true; break; }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchCount++;
    } else if (switchCount === 0 && direction === "asc") {
      direction = "desc";
      switching = true;
    }
  }
}

// Sort event listeners for books table
document.getElementById('sort-title').addEventListener('click', () => sortTable(1, '#books'));
document.getElementById('sort-author').addEventListener('click', () => sortTable(2, '#books'));
document.getElementById('sort-genre').addEventListener('click', () => sortTable(3, '#books'));

// Sort event listeners for reviews table
document.getElementById('sort-user').addEventListener('click', () => sortTable(1, '#reviews'));
document.getElementById('sort-book').addEventListener('click', () => sortTable(2, '#reviews'));

// Book Modal event listener
const bookModal = document.getElementById('bookModal');
bookModal.addEventListener('show.bs.modal', function (event) {
  const trigger = event.relatedTarget;
  const mode = trigger ? trigger.getAttribute('data-mode') : 'add';
  const modalTitle = bookModal.querySelector('#bookModalLabel');
  const submitBtn = bookModal.querySelector('button[type="submit"]');
  if (mode === 'edit') {
    modalTitle.textContent = 'Modificar Libro';
    submitBtn.textContent = 'Actualizar Libro';
  } else {
    modalTitle.textContent = 'Registrar un Nuevo Libro';
    submitBtn.textContent = 'Publicar Libro';
  }
});
