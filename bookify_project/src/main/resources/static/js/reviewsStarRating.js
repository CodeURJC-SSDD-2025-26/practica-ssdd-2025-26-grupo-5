const labels = Array.from(document.querySelectorAll('.star-rating label'));
const inputs = document.querySelectorAll('.star-rating input');
const ratingDisplay = document.getElementById('ratingDisplay');

// Fill stars from the hovered one to the last (from right to left)
labels.forEach((label, index) => {

    label.addEventListener('mouseenter', () => {
        labels.forEach((l, i) => {
            const icon = l.querySelector('i');
            if (i >= index) {
                icon.classList.replace('bi-star', 'bi-star-fill');
            } else {
                icon.classList.replace('bi-star-fill', 'bi-star');
            }
        });
    });

    label.addEventListener('mouseleave', () => {
        restoreSelected();
    });
});

// Restore stars to the selected value after hover
function restoreSelected() {
    const checked = document.querySelector('.star-rating input:checked');

    if (checked) {
        const selectedValue = parseInt(checked.value);
        labels.forEach((l) => {
            const icon = l.querySelector('i');
            const labelValue = parseInt(l.getAttribute('for').replace('star', ''));
            if (labelValue <= selectedValue) {
                icon.classList.replace('bi-star', 'bi-star-fill');
            } else {
                icon.classList.replace('bi-star-fill', 'bi-star');
            }
        });
    } else {
        labels.forEach((l) => {
            const icon = l.querySelector('i');
            icon.classList.replace('bi-star-fill', 'bi-star');
        });
    }
}

// Show rating number on selection
inputs.forEach(input => {
    input.addEventListener('change', () => {
        ratingDisplay.style.display = 'inline';
        ratingDisplay.textContent = input.value + ' / 10';
    });
});