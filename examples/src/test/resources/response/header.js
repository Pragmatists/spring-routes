var app = route();

app
    .get('/hello', function (req, resp) {
        resp.set('headerProperty', 'headerValue');
    })
    .get('/set-header-from-object', function (req, resp) {
        resp.set({
            headerProperty: 'headerValue',
            otherProperty: 'otherValue'
        });
    });
