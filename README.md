spring-routes
=============
h1. Introduction
TBD
h1. Features:
h2. Supported methods:
```
var app = routes();
app.get('/user', function(req, resp){
    // ...
  })
  .post('/user', function(req, resp){
    // ...
  })
  .put('/user/', function(req, resp){
    // ...
  })
  .delete('/user/{id}', function(req, resp){
    // ...
  });
```
