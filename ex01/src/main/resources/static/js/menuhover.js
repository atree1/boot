    $(".categoryMenu").hover(
        function () {
            $(this).show();
        },
        function () {
            $(this).hide();
        }
    );

    function categoryMenuChange(num){
        $(".selectCategoryArea").hide();
        $(".selectCategory").removeClass('active');

        if(num == 1){
            $(".womanCategoryArea").show();
            $(".womanCategory").addClass('active');
        }else if(num == 2){
            $(".manCategoryArea").show();
            $(".manCategory").addClass('active');
        }else if(num == 3){
            $(".childCategoryArea").show();
            $(".childCategory").addClass('active');
        }
        $(this).addClass('active');
    }

    function categoryChange(num){
        $(".selectCategoryArea").removeClass('active');
        if(num == 1){
            $(".womanCategoryArea").addClass('active');
        }else if(num == 2){
            $(".manCategoryArea").addClass('active');
        }else if(num == 3){
            $(".childCategoryArea").addClass('active');
        }

    }

    function categoryMenuShow(num){
    	$(".selectCategoryArea").hide();
        $(".womanCategoryArea").show();
        if(num == 1){
            $(".categoryMenu").show();
        }else if(num == 2){
            $(".buildingMenu").show();
        }

    }