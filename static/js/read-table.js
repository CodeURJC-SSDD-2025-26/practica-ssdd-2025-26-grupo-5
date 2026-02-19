const ctx = document.getElementById('graficaLectura').getContext('2d');
const datosLectura = {
    //Months of the year as labels
    labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
    datasets: [{
        label: 'Libros leídos por mes',
        // User data below
        data: [2, 3, 1, 5, 2, 4, 8, 6, 3, 4, 2, 5], 
        backgroundColor: '#3c2616', // Bar color and personalization
        borderColor: '#3c2616',
        borderWidth: 2,
        borderRadius: 30
    }]
};
let promedio = datosLectura.datasets[0].data.reduce((a, b) => a + b, 0) / datosLectura.datasets[0].data.length;

const config = {
    type: 'bar', // Tipo de gráfica: barras verticales
    data: datosLectura,
    options: {
        scales: {
            y: {
                beginAtZero: true, // Asegura que empiece en 0
                ticks: {
                    stepSize: 1 // Ideal para contar libros (números enteros)
                },
                title: {
                    display: true,
                    text: 'Número de libros leídos',
                    color: '#382110',
                    font: { size: 11}
                }
            }
        },
        plugins: {
            legend: {
                display: true,
                position: 'top'
            },
            title: {
                display: true,
                text: 'Puntuación Media: ' + promedio.toFixed(2),
                color: '#382110',
                font: { size: 16, weight: 'bold' }
            }
        }
    }
};


new Chart(ctx, config);