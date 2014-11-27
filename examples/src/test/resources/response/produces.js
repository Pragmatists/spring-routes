var app = route();

app.post('/without_produces', function (req, resp) {
});

app.produces("text/plain")
    .get('/text', function (req, resp) {

    });
