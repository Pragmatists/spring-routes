var app = route();

app
    .get('/hello', function (req, resp) {
        resp.status(414);
    });
