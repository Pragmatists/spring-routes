spring-routes
=============
# Introduction
TBD
# Features:
## Supported HTTP methods:
```
var app = routes();
app
  .get('/hello', function(req, resp){
    // ...
  })
  .post('/hello', function(req, resp){
    // ...
  })
  .put('/hello', function(req, resp){
    // ...
  })
  .delete('/hello', function(req, resp){
    // ...
  });
```
## Fluent path building:
```
var app = routes();
app
  .path('/parent')
    .get('/child', function(req, resp){
      // responds to GET /parent/child
    });

```
## Support for path variables:
```
var app = routes();
app
  .get('/hello/{name}', function(req, resp){
    resp.send('Hello ' + req.param('name') + '!');
  });
```
