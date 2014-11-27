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

var countries = app.path('/countries');

countries
	.path('/poland')
		.get('/capital', function(req, res){
			res.send('Warsaw');
		});

countries
	.path('/germany')
  		.get('/capital', function(req, res){
			res.send('Berlin');
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
