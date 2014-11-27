
//var bean = function(beanName){
//	
//	return {
//		__noSuchMethod__: function() {
//			
//			var method = [].splice.call(arguments, 0, 1);
//
//			print(beanName + "." + method + '(' + JSON.stringify(arguments) + ')');
//			
//			var bean = _app.bean(beanName);
//			print('bean: ' + bean);
//			
//			print('result: ' + bean.hello('Dupa', 10));
//			
//			print('method: ' + bean[method]);
//			return target.apply(bean, arguments);
//		}		
//	};
//};

var bean = function(beanName){
    _app.log(beanName.type)
	return _app.bean(beanName);
};

var console = {
	log: function(message) {
		_app.log(message);
	}
};

var route = function(_r){

	var _route = _r || _app.route();
	
	var req = function(_req){
		
		try{
			var params = {};
			_req.params.forEach(function (key, value) {
				var p = [];
				value.forEach(function (e) {
					p.push(e);
				});
				params[key] = (p.length == 1 ? p[0] : p);
			});

			var bodyJson = JSON.parse(_req.body());
		} catch(e){
			// not a json
		}
		
		return {
			param: function(param){
				return params[param];
			},
			params: params,
			body: bodyJson || {}
		};
	};

	var res = function(_res){
		
		var r = {
			send: function(obj){
				_res.send(obj);
				return r;
			},
			sendJson: function(obj){
				if(!_res.getContentType()){
					_res.contentType("application/json");
				}
				_res.send(JSON.stringify(obj));
				return r;
			},
			contentType: function(contentType){
				_res.contentType(contentType);
				return r;
			},
			status: function(status){
				_res.status(status);
				return r;
			},
			set: function(name,value){
				if(arguments.length === 2 )
					_res.header(name, value);
				else {
					for(property in name){
						_res.header(property, name[property]);
					}
				}
				return r;
			}
		};
		
		return r;
	};
	
	var delegateTo = function(callback){
		return function(_req, _res, _next){
			callback(new req(_req), new res(_res), function(){
				_next.next();
			});
		};
	};
	
	var r = {

		use: function(callback){
			return route(_route.use(delegateTo(callback))); 
		},
		get: function(url, callback){
			return route(_route.get(url, delegateTo(callback))); 
		},
		put: function(url, callback){
			return route(_route.put(url, delegateTo(callback))); 
		},
		post: function(url, callback){
			return route(_route.post(url, delegateTo(callback))); 
		},
		del: function(url, callback){
			return route(_route.del(url, delegateTo(callback))); 
		},
		path: function(url){
			return route(_route.path(url));
		},
		produces: function(contentType){
			return route(_route.produces(contentType))
				.use(function(req, res, next){
					res.contentType(contentType);
					next();
				});
		},
		consumes: function(contentType){
			return route(_route.consumes(contentType));
		},
		options: function(options){
			var _r = _route;
			if(options.consumes) _r = _r.consumes(options.consumes);
			if(options.produces) _r = _r.produces(options.produces);
			return route(_r);
		}
	};
	
	return r;
};
