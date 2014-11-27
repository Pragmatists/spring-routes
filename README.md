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
## Filters:
```javascript
var app = routes();
app
  .use(function(req, res, next){
  	req.send('Hello ');
  	next();
  	req.send('!');
  })
  .get('/hello/{name}', function(req, resp){
    resp.send(req.param('name'));
  });
```
## Request content negotiation
### Consume:
```javascript
var app = routes();
app.consumes('text/csv').post('/import', function(req, resp){
  // ...
});
app.consumes('text/*').post('/wildcard', function(req, resp){
  // ...
});
```
### Produces:
```javascript
var app = routes();
app
  .produces("text/plain")
  .get('/greeting', function (req, resp) {
    resp.send('Hello John');
  });
```
## Response
```
var app = routes();
app.get('/greeting', function (req, resp) {
    resp.send('Hello John');
});

app.get('/greeting', function (req, resp) {
    resp.sendJson({'thiIs': 'Json'});
});

app.get('/greeting', function (req, resp) {
    resp.status(200);
});

app.get('/greeting', function (req, resp) {
    resp.contentType("text/plain");
});

app.get('/greeting', function (req, resp) {
    resp.set("header", "value");
    resp.set({
          "header1": "value1",
          "header2": "value2"
          });
});
```
## Spring
```
var app = routes();
app.get('/greeting', function (req, resp) {
    var springBean = bean('myBean');
    var springBean = bean(org.bean.SpringBean);
});
```
