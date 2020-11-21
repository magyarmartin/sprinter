const host = "dc.loxon.swarm";
const port = 8271;

$( "#evaluation-form" ).submit(function( event ) {
  //  $("#submit").animate({opacity: 'toggle'}, 'slow');
    $("#submit").text("");
    $("#submit").append(`
        <div class="spinner-border" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    `);
    let $inputs = $('#evaluation-form :input');

    let values = {};

        values.deliveringValue     = getNumericValue($inputs[0].value);
        values.easyToRelease       = getNumericValue($inputs[1].value);
        values.fun                 = getNumericValue($inputs[2].value);
        values.healthOfCodebase    = getNumericValue($inputs[3].value);
        values.teamwork            = getNumericValue($inputs[4].value);
        values.connectionToMission = getNumericValue($inputs[5].value);
        values.learning            = getNumericValue($inputs[6].value);
        values.control             = getNumericValue($inputs[7].value);
        values.coordination        = getNumericValue($inputs[8].value);
        values.comment             = $inputs[9].value;

    getSprintNumber().then(response => {
        return response.json();
    }).then(sprintNumber => {
        values.sprintNumber = sprintNumber;
        submitEvaluation(values);
        location.replace("index.html")
    });
    event.preventDefault();
});

$( "#resetSprint" ).click(function( event ) {
    resetSprint(sprintNumber).then(() => {
        getSprintNumber().then(response => {
            return response.json();
        }).then(number => {
            fetchEvaluation(sprintNumber);
        });
    });
});

$( "#newsprint" ).click(function( event ) {
    startNewSprint(sprintNumber + 1).then(() => {
        getSprintNumber().then(response => {
            return response.json();
        }).then(number => {
            sprintNumber = number;
            fetchEvaluation(sprintNumber);
        });
    });
});

function getNumericValue( textValue ) {
    if ( textValue === "Helpful" ) {
        return 3;
    } else if ( textValue === "Meh.." ) {
        return 2;
    } else if ( textValue === "Disaster" ) {
        return 1;
    }
}

function getSprintNumber() {
    return fetch(`http://${host}:${port}/sprint/number`);
}

function submitEvaluation(data = {}) {
    // Default options are marked with *
    fetch(`http://${host}:${port}/evaluation`, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
}

function resetSprint(data = {}) {
    // Default options are marked with *
    return fetch(`http://${host}:${port}/sprint/reset`, {
        method: 'DELETE', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
}

function startNewSprint(data = {}) {
    // Default options are marked with *
    return fetch(`http://${host}:${port}/sprint/new`, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
}

