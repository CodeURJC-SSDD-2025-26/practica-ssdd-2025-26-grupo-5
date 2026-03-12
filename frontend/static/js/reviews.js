const votos = [0, 0, 1, 2, 5, 8, 15, 40, 85, 120, 45];
const puntuacion = ['0','1','2','3','4','5','6','7','8','9','10'];

const totalVotos = votos.reduce((a, b) => a + b, 0);
const sumaNotas  = votos.reduce((acc, cantidad, nota) => acc + (cantidad * nota), 0);
const promedio   = (sumaNotas / totalVotos).toFixed(1);

new Chart(document.getElementById('reviewsChart').getContext('2d'), {
    type: 'bar',
    data: {
        labels: puntuacion,
        datasets: [{
            ...baseDatasetStyle,
            label: 'Número de lectores',
            data: votos,
        }]
    },
    options: {
        ...baseChartOptions,
        indexAxis: 'y',
        plugins: {
            ...baseChartOptions.plugins,
            title: {
                ...baseChartOptions.plugins.title,
                text: 'Puntuación Media: ' + promedio + ' / 10'
            }
        },
        scales: {
            ...baseChartOptions.scales,
            x: { ...baseChartOptions.scales.x, title: { display: true, text: 'Cantidad de personas' } },
            y: { ...baseChartOptions.scales.y, title: { display: true, text: 'Puntuación' } }
        }
    }
});