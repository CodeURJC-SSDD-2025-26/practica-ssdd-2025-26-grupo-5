const ctx = document.getElementById('reviewsChart').getContext('2d');

// Solo son datos de ejemplo:
const votos = [0, 0, 1, 2, 5, 8, 15, 40, 85, 120, 45]; 
const puntuacion = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

// Cálculo de la media:
let totalVotos = votos.reduce((a, b) => a + b, 0);
let sumaNotas = votos.reduce((acc, cantidad, nota) => acc + (cantidad * nota), 0);
let promedio = (sumaNotas / totalVotos).toFixed(1);

new Chart(ctx, {
  type: 'bar', // Gráfico de Barras
  data: {
    labels: puntuacion, // Puntuaciones del 0 al 10
    datasets: [{
      label: 'Número de lectores',
      data: votos, // Cantidad de gente
      backgroundColor: '#382110',
      borderRadius: 5
    }]
  },
  options: {
    indexAxis: 'y', // Horizontal
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      x: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'Cantidad de personas'
        }
      },
      y: {
        title: {
          display: true,
          text: 'Puntuación'
        }
      }
    },
    plugins: {
      legend: { display: false },
      // Mostrar promedio dentro del gráfico
      title: {
        display: true,
        text: 'Puntuación Media: ' + promedio + ' / 10',
        color: '#382110',
        font: { size: 16, weight: 'bold' }
      }
    }
  }
});