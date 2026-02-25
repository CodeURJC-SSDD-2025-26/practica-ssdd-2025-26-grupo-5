const CHART_COLORS = {
    primary: '#382110',
    dark:    '#3c2616',
};

const baseChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
        legend: {
            display: false
        },
        title: {
            display: true,
            color: CHART_COLORS.primary,
            font: { size: 16, weight: 'bold' }
        }
    },
    scales: {
        x: {
            grid: { display: false },
            ticks: { color: CHART_COLORS.primary }
        },
        y: {
            beginAtZero: true,
            grid: { color: 'rgba(56, 33, 16, 0.08)' },
            ticks: { 
                color: CHART_COLORS.primary,
                stepSize: 1
            }
        }
    }
};

const baseDatasetStyle = {
    backgroundColor: CHART_COLORS.primary,
    borderColor:     CHART_COLORS.primary,
    borderRadius: 30,
    borderWidth: 2,
};