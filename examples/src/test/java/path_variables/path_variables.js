
	var app = route();
	
	app
		.get('/hello/{name:.+}', function(req, resp){
				resp.send('Hello ' + req.param('name') + '!');
		})
		.get('/hello-regexp/{name:a.+}', function(req, resp){
			resp.send('Hello ' + req.param('name') + '!');
		});