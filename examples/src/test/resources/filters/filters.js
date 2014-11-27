
	var app = route();
	
	app
		.use(function(req, res, next){
			
			res.send('Hello ');
			next();
			res.send('!');
			
		})
		.get('/hello', function(req, res){
			res.send('World');
		});