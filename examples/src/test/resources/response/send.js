var app = route();

app
    .get('/hello', function (req, resp) {
        resp.send('Sent body');
    })
    .get('/hello-json', function (req, resp) {
        resp.sendJson({
            stringValue: 'hello',
            number: 3,
            array: [1, 2, 3],
            object: {
                property: 'value'
            }
        });
    })
    .get('/hello-json-with-custom-content-type', function (req, resp) {
        resp.contentType('custom_content_type').sendJson({});
    });
