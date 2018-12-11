var replyService = (function(){
	
	//댓글추가
	function add(reply, callback){
		$.ajax({
			type:'post',
			url:'/replies/new',
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status, er){
				if(error){
					error(er);
				}
			}	
		});
	}
	
	//댓글목록
	function getList(param, callback, error){
		
		var pno = param.pno;
		var page = param.page||1;
		var kind = param.kind;
		
		$.getJSON("/replies/pages/" +kind+ "/" +pno+ "/" +page,
				
		  function(data){
			if(callback){
				callback(data.replyCnt,data.parentCnt,data.list,data.scoreAverage);
			}
		}).fail(function(xhr,status,err){
			if(error){
				error();
			}
		});
		
	
	}
	
	//댓글삭제
	function remove(rno, callback, error){
		
		$.ajax({
			
			type: 'put',
			url: '/replies/delete/' + rno,
			success: function(deleteResult, staus, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	//댓글수정
	function update(reply, callback, error){
		
		$.ajax({
			type:'put',
			url:'/replies/' + reply.rno,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		
		
	}
	
	//댓글조회
	function get(rno,callback,error){
		
		$.get("/replies/" +rno,function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});		
	}
	
	return {
			add:add,
			getList:getList,
			remove:remove,
			update:update,
			get:get
	};
})();