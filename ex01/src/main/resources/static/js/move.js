var actionForm = $("#actionForm");

function goShoppingBasket(){
	window.location.href = '/user/cart';
}; 
function myPageMove(num){
	
};

function dBuyingOrder(){
	window.location.href = '/user/productOrder';
}


function memberJoin(){
	window.location.href = '/memberJoin';
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