var app = route();
app
    .get('/hello', function (req, resp) {
        resp.send("Hello " + req.param('name') + "!");
    })
    .get('/hello-array', function (req, resp) {
        resp.send("Hello " + req.param('name').join(' ') + "!");
    })
    .get('/echo', function (req, resp) {
        resp.sendJson(req.params);
    });