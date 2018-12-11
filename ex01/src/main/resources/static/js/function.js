var actionForm = $("#actionForm");

$(".goSearch").click(function(){
	
	var keyword = $("#searchText").val();
	
	if(keyword.length != 0){
		actionForm.append("<input type='hidden' id='category' name='category' value='"+$("#category_").val()+"'>");
		actionForm.append("<input type='hidden' id='keyword' name='keyword' value='"+keyword+"'>");
		actionForm.attr("action","/user/list").submit();
	}else{
		alert("검색어를 입력해주세요");
	}
});

function myPageMove(num){
	if(num == 1){
		actionForm.append("<input type='hidden' name='mid' value='member1'>");
		actionForm.attr("action","/user/mypage/modify").submit();
	}else if(num == 2){
		actionForm.attr("action","/user/mypage/orderList").submit();
	}else if(num == 3){
		actionForm.append("<input type='hidden' name='kind' value='q'>");
		actionForm.append("<input type='hidden' name='mid' value='test6'>");
		actionForm.attr("action","/user/mypage/qna").submit();
	}else if(num == 4){
		actionForm.append("<input type='hidden' name='kind' value='r'>");
		actionForm.append("<input type='hidden' name='mid' value='gg'>");
		actionForm.attr("action","/user/mypage/review").submit();
	}
}

$(".selectSearchLi").click(function(){
	$("#category_").val($(this).attr("id"))
	$("#searchOptionSelect").html($(this).html());
	slideUp();
});

function goShoppingBasket(){
	$("#amount").val("6");
	actionForm.attr("action", "/user/cart")
	.append("<input type='hidden' name='mid' value='member1'>")
	.submit();
}

function categoryClickFunc(cat){
	
	var str = "";
	var category;
	if($(".active").hasClass("womanCategory")){
		category = "women";
	}else if($(".active").hasClass("manCategory")){
		category = "men";
	}else if($(".active").hasClass("childCategory")){
		category = "children";
	}
	
	str += "<input type='hidden' name='category' value='"+category+"'/>";
	str += "<input type='hidden' name='subCategory' value='"+cat+"'/>";
	actionForm.append(str).attr("action","/user/list").submit();
}


function onlyNumber(event){
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        return false;
}
 
function removeChar(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

