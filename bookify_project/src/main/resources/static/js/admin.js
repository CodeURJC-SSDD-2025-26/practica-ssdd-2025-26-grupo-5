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
// Book Modal event listener
const bookModal = document.getElementById('bookModal');
bookModal.addEventListener('show.bs.modal', function(event) {
  const btn = event.relatedTarget;
  const form = document.getElementById('bookForm');
  const submitBtn = bookModal.querySelector('button[type="submit"]');
  const id = btn ? btn.getAttribute('data-id') : null;

  if (id) {
    document.getElementById('bookId').value = id;
    form.action = '/admin/book/' + id + '/edit';
    document.getElementById('bookModalLabel').textContent = 'Editar Libro';
    submitBtn.textContent = 'Actualizar Libro';
    form.querySelector('[name="title"]').value = btn.getAttribute('data-title');
    form.querySelector('[name="author"]').value = btn.getAttribute('data-author');
    form.querySelector('[name="genre"]').value = btn.getAttribute('data-genre');
    form.querySelector('[name="isbn"]').value = btn.getAttribute('data-isbn');
    form.querySelector('[name="coverUrl"]').value = btn.getAttribute('data-cover');
    form.querySelector('[name="pages"]').value = btn.getAttribute('data-pages');
    form.querySelector('[name="language"]').value = btn.getAttribute('data-language');
    form.querySelector('[name="publicationYear"]').value = btn.getAttribute('data-year');
    form.querySelector('[name="synopsis"]').value = btn.getAttribute('data-synopsis');
  } else {
    document.getElementById('bookId').value = '';
    form.action = '/admin/book/new';
    form.reset();
    document.getElementById('bookModalLabel').textContent = 'Registrar un Nuevo Libro';
    submitBtn.textContent = 'Publicar Libro';
  }
});

document.addEventListener('DOMContentLoaded', function() {
    const hash = window.location.hash;
    const savedTab = sessionStorage.getItem('activeTab');
    
    const targetTab = hash || savedTab;
    
    if (targetTab) {
        const tabEl = document.querySelector(`button[data-bs-target="${targetTab}"]`);
        if (tabEl) {
            const tab = new bootstrap.Tab(tabEl);
            tab.show();
        }
    }
    
    if (hash) {
        history.replaceState(null, null, window.location.pathname);
    }

    document.querySelectorAll('button[data-bs-toggle="tab"]').forEach(function(tabEl) {
        tabEl.addEventListener('shown.bs.tab', function(e) {
            const target = e.target.getAttribute('data-bs-target');
            sessionStorage.setItem('activeTab', target);
        });
    });
});
