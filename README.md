spring-routes
=============
# Introduction
TBD
# Features:
## Supported HTTP methods:
```javascript
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
```javascript
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
```javascript
var app = routes();
app
  .get('/hello/{name}', function(req, resp){
    resp.send('Hello ' + req.param('name') + '!');
  });
```
## Request
### Consuming support:
```javascript
var app = routes();
app.consumes('text/csv').post('/import', function(req, resp){
  // ...
});
app.consumes('text/*').post('/wildcard', function(req, resp){
  // ...
});
```
