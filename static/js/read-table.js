const datosLectura = [2, 3, 1, 5, 2, 4, 8, 6, 3, 4, 2, 5];
const promedioLectura = (datosLectura.reduce((a, b) => a + b, 0) / datosLectura.length).toFixed(2);

new Chart(document.getElementById('graficaLectura').getContext('2d'), {
    type: 'bar',
    data: {
        labels: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
        datasets: [{
            ...baseDatasetStyle,
            label: 'Libros leídos',
            data: datosLectura,
        }]
    },
    options: {
        ...baseChartOptions,
        plugins: {
            ...baseChartOptions.plugins,
            legend: {
                display: true,
                position: 'top',
                onClick: null
            },
            title: {
                ...baseChartOptions.plugins.title,
                text: 'Media mensual: ' + promedioLectura + ' libros'
            }
        },
        scales: {
            ...baseChartOptions.scales,
            y: { 
                ...baseChartOptions.scales.y,
                title: { display: true, text: 'Número de libros leídos', color: '#382110', font: { size: 11 } }
            }
        }
    }
});