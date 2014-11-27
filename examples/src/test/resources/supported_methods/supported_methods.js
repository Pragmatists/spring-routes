
	var app = route();
	
	app
		.get('/hello', function(req, res){
			res.send('Hello GET!');
		})
		.post('/hello', function(req, res){
			res.send('Hello POST!');
		})
		.put('/hello', function(req, res){
			res.send('Hello PUT!');
		})
		.del('/hello', function(req, res){
			res.send('Hello DELETE!');
		})
		.path('/parent')
			.get('/child', function(req, resp){
				resp.send('Hello from child!');
			});
		app.get('/hello/{name}', function(req, resp){
				resp.send('Hello ' + req.param('name') + '!');
			});