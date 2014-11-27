
	var app = route();
	
	app
		.use(function(req, res, next){
			
			res.send('outer before -> ');
			next();
			res.send(' -> outer after');
			
		})
		.use(function(req, res, next){
			
			res.send('inner before -> ');
			next();
			res.send(' -> inner after');
			
		})
		.get('/stuff', function(req, res){
			res.send('stuff');
		});