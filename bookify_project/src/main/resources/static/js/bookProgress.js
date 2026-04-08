// Update progress bars width from data attribute (carga inicial)
document.querySelectorAll('.progress-bar').forEach(bar => {
    const progress = bar.dataset.progress;
    bar.style.width = progress + '%';
});

// Botón Actualizar
document.querySelectorAll('.input-group').forEach(group => {
    const input = group.querySelector('input[type="number"]');
    const button = group.querySelector('button');
    const progressContainer = group.closest('.mt-2');
    const progressBar = progressContainer.querySelector('.progress-bar');
    const progressText = progressContainer.querySelector('.fw-bold');
    const maxPages = parseInt(input.max);

    button.addEventListener('click', () => {
        const currentPage = parseInt(input.value);

        // Validación
        if (isNaN(currentPage) || currentPage < 0 || currentPage > maxPages) {
            alert(`Introduce una página válida entre 0 y ${maxPages}`);
            return;
        }

        // Calcular porcentaje
        const percentage = Math.round((currentPage / maxPages) * 100);

        // Actualizar barra
        progressBar.dataset.progress = percentage;
        progressBar.style.width = percentage + '%';

        // Actualizar texto
        progressText.textContent = `${currentPage} / ${maxPages}`;
    });
});