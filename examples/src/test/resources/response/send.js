var app = route();

app
    .get('/hello', function (req, resp) {
        resp.send('Sent body');
    })
