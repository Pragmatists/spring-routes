var app = route();

app
    .get('/hello', function (req, resp) {
        resp.contentType("text/html");
    });
