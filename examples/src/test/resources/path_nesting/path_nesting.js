
	var app = route();
	
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

				
