document.addEventListener('DOMContentLoaded', function() {

    const labels = Array.from(document.querySelectorAll('.star-rating label'));
    const inputs = document.querySelectorAll('.star-rating input');
    const ratingDisplay = document.getElementById('ratingDisplay');

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

    inputs.forEach(input => {
        input.addEventListener('change', () => {
            ratingDisplay.style.display = 'inline';
            ratingDisplay.textContent = input.value + ' / 10';
            
            const errorDiv = document.getElementById('ratingError');
            if (errorDiv) {
                errorDiv.classList.add('d-none');
            }
        });
    });

    const reviewForm = document.getElementById('formReview');
    
    if(reviewForm) {
        reviewForm.addEventListener('submit', function(event) {
            
            const starChecked = document.querySelector('input[name="reviewRating"]:checked');
            const errorDiv = document.getElementById('ratingError');

            if (!starChecked) {
                event.preventDefault(); 
                errorDiv.classList.remove('d-none'); 
            } else {
                errorDiv.classList.add('d-none');
            }
        });
    }

});