var app = route();

//app.use();
app.post('/without_consumes', function (req, resp) {
});

app.consumes("text/plain")
    .post('/text', function (req, resp) {

    });

app.consumes("text/*")
    .post('/text_wildcard', function (req, resp) {

    });
