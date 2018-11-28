console.log("reply module...........");
var cartService = (function() {
	function add(cart ,callback,error){
		console.log("add reply....");
		
		$.ajax({
			
			type:'post',
			url:'/cart/new',
			data:JSON.stringify(cart),
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		})
	}
	function getList(param,callback,error){
		var mid=param.mid;
		var page=param.page||1;
		$.getJSON("/cart/pages/"+mid+"/"+page,
				function(data){
			if(callback){
				callback(data);
//				callback(data.replyCnt,data.list);
	//			console.log(data.replyCnt,data.list);
			}
		}).fail(function(xhr,status,err){
			if(error){
				error();
			}
		})
	}
	function remove(cart,callback,error){
		console.log("cno: "+cart.cno);
		$.ajax({
			type:'put',
			url:'/cart/delete/'+reply.rno,
			data:JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	function update(reply,callback,error){
		console.log("RNO: "+reply.rno);
		$.ajax({
			type:'put',
			url:'/replies/'+reply.rno,
			data:JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function get(rno,callback,error){
		$.get("/replies/"+rno+".json",function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr,status,err) {
			if(error){
				error();
				}	
		});
	}

	};
	return {add:add,
		getList:getList,
		update:update,
		remove:remove,
		get:get
	};
})();
