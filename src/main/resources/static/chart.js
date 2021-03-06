let sprintNumber;
let data;
let myChart;

getSprintNumber().then(response => {
    return response.json();
}).then(number => {
    sprintNumber = number;
    fetchEvaluation(sprintNumber);
    setInterval(() => {
        fetchEvaluation(sprintNumber);
    }, 10000);
});

function fetchEvaluation(sprintNumber) {
    fetch(`http://${host}:${port}/evaluation/` + sprintNumber).then(response => {
        return response.json();
    }).then(evaluationData => {
        let tmp = convertEvaluationData(evaluationData);
        if ( JSON.stringify(data) != JSON.stringify(tmp)  ) {
            data = tmp;
            let convertedData = convertEvaluationData(evaluationData);
            if ( myChart === undefined ) {
                createStackedBarChart(convertedData);
            } else {
                let dataset = myChart.data.datasets;
                dataset[0].data = convertedData.disaster;
                dataset[1].data = convertedData.meh;
                dataset[2].data = convertedData.helpful;
                myChart.update();
            }
            updateComments(evaluationData.comments)
        }
    });
}

function updateComments( comments ) {
    $( "#comments" ).empty();
    if ( comments.length > 0 ) {
        for ( let i = 0; i < comments.length; ++i ) {
            $( "#comments" ).append( `
                    <div class="card">
                        <div class="card-body">
                            ${comments[i]}
                        </div>
                    </div>
                    ` );
        }
    }
}

function convertEvaluationData(evaluationData) {
    let data = {};

    let disaster = [];
    disaster.push(evaluationData.deliveringValue.disaster);
    disaster.push(evaluationData.easyToRelease.disaster);
    disaster.push(evaluationData.fun.disaster);
    disaster.push(evaluationData.healthOfCodebase.disaster);
    disaster.push(evaluationData.teamwork.disaster);
    disaster.push(evaluationData.connectionToMission.disaster);
    disaster.push(evaluationData.learning.disaster);
    disaster.push(evaluationData.control.disaster);
    disaster.push(evaluationData.coordination.disaster);
    data.disaster = disaster;

    let meh = [];
    meh.push(evaluationData.deliveringValue.meh);
    meh.push(evaluationData.easyToRelease.meh);
    meh.push(evaluationData.fun.meh);
    meh.push(evaluationData.healthOfCodebase.meh);
    meh.push(evaluationData.teamwork.meh);
    meh.push(evaluationData.connectionToMission.meh);
    meh.push(evaluationData.learning.meh);
    meh.push(evaluationData.control.meh);
    meh.push(evaluationData.coordination.meh);
    data.meh = meh;

    let helpful = [];
    helpful.push(evaluationData.deliveringValue.helpful);
    helpful.push(evaluationData.easyToRelease.helpful);
    helpful.push(evaluationData.fun.helpful);
    helpful.push(evaluationData.healthOfCodebase.helpful);
    helpful.push(evaluationData.teamwork.helpful);
    helpful.push(evaluationData.connectionToMission.helpful);
    helpful.push(evaluationData.learning.helpful);
    helpful.push(evaluationData.control.helpful);
    helpful.push(evaluationData.coordination.helpful);
    data.helpful = helpful;

    return data;
}

function createStackedBarChart(data) {
    let ctx = document.getElementById('myChart').getContext('2d');
    myChart = new Chart(ctx, {
        type: 'bar',
        maintainAspectRatio: false,
        data: {
            labels: ['DELIVERING VALUE', 'EASY TO RELEASE', 'FUN', 'HEALTH OF CODEBASE', 'TEAMWORK', 'CONNECTION TO MISSION', 'LEARNING', 'CONTROL', 'COORDINATION'],
            datasets: [{
                label: 'Disaster',
                data: data.disaster,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            },
                {
                    label: 'Meh..',
                    data: data.meh,
                    backgroundColor: 'rgba(255, 206, 86, 0.2)',
                    borderColor: 'rgba(255, 206, 86, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Helpful',
                    data: data.helpful,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [{
                    stacked: true,

                }]
            }
        }
    });
}