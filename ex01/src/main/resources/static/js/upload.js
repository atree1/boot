var uploadService=(function() {
    
	$('.uploadResult').on("click","button",function(e){
		e.preventDefault();
		var targetFile=$(this).data("file");
		
		var targetLi=$(this).closest('li');
		console.log(targetFile);
		$.ajax({
			url:'/deleteFile',
		data:{fileName:targetFile},
		dataType:'text',
		type:'POST',
		success:function(result){
			alert(result);
			targetLi.remove();
		}
		});
	});
	$('#registerBtn').on("click", function(e) {
		e.preventDefault();
		var registerForm=$('#registerForm');
		console.log($("uploadResult ul li"));
		var str="";
		$(".uploadResult ul li").each(function(i,obj){
			var jobj=$(obj);
			console.log(jobj);
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
		
			
			
		})
		console.log(str);
		registerForm.append(str).submit();
	});
	var result = '<c:out value="${result}"/>';

	var regex=new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize=5242880;
	function checkExtension(fileName,fileSize){
		if(fileSize>=maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert('해당 파일 업로드 불가');
			return false;
		}
		return true;
	}

	
	$('#files').change(function(e){
		var formData=new FormData();
	
		var inputFile=$("input[name='uploadFile']");
		
		var files=inputFile[0].files;
		
		for(var i=0;i<files.length;i++){
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			console.log(files[i]);
			formData.append("uploadFile",files[i]);
		}
		$.ajax({
			url:'/upload',
			processData:false,
			contentType:false,
			data:formData,
			type:'POST',
			dataType:'json',
			success:function(result){
				console.log(result);
				showUploadResult(result);
			}
		});
	});
	function showUploadResult(uploadResultArr) {
		if(!uploadResultArr||uploadResultArr.length==0){
			return;
		}
		var uploadUL=$(".uploadResult ul");
		var str="";
		$(uploadResultArr).each(function(i,obj){
			
			
				var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"'><div>";
				str += "<span> "+ obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' class='btn btn-icons btn-rounded btn-outline-warning'>x</button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div>";
				str +"</li>";
		});
		uploadUL.append(str);
})();