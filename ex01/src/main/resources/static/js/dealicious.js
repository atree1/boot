

var rn = 9000;
var rn2 = 7000;

// var deviceWidth = 0;

// deviceWidth = parseInt(viewport.deviceWidth);

var iPhoneCenter = 0;

$(document).ready(function(){
	//숫자 올라가는 효과 등등 표현 하려고 내가 쓰려고 하는 js

    
    

    // if (agt.indexOf("msie") != -1) {
    //     console.log("userAgent : " + agt);
    // } 

    



	//console.log("불리냐..?");
	  //var rn = Math.round(Math.random() * 99999);
	
	//$("#msg").text("Random Number = " + rn);
	//var tt =  $('.bullet').attr('class');

	//console.log("tt : " + tt);
	
    //console.log("deviceWidth : " + deviceWidth);

    


    if(deviceWidth < 480){
        iPhoneCenter = (deviceWidth - 320) / 2;
        // console.log("iphoneCenter : " + iPhoneCenter);
        $('.smartPhoneContainer').css("margin-left", iPhoneCenter);
    }
    

    
	// if($('.bullet last').attr('class').indexOf('selected') != -1){	

	

    Chart.defaults.global.pointHitDetectionRadius = 1;
    var randomScalingFactor = function() {
        return Math.round(Math.random() * 300);
    };
    var lineChartData = {
        labels: ["2015.1", "02", "03", "04", "05", "06", "07","08","09","10","11"],
        datasets: [{
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.2)",
            strokeColor: "rgba(220,220,220,1)",
            pointColor: "rgba(220,220,220,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(220,220,220,1)",
            data: [100, 100, 142, 162, 177, 196, 196, 169, 215, 234, 230, 230, 0]
        }, {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.2)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(151,187,205,1)",
            //data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }]
    };

    //2번 그래프
    Chart.defaults.global.customTooltips = function(tooltip) {

        // console.log("tooltip : " + tooltip);

        var tooltipEl = $('#chartjs-tooltip2');

        if (!tooltip) {
            tooltipEl.css({
                opacity: 0
            });
            return;
        }

        tooltipEl.removeClass('above below');
        tooltipEl.addClass(tooltip.yAlign);

        var innerHtml = '';
        for (var i = tooltip.labels.length - 1; i >= 0; i--) {
            innerHtml += [
                '<div class="chartjs-tooltip-section">',
                '   <span class="chartjs-tooltip-key" style="background-color:' + tooltip.legendColors[i].fill + '"></span>',
                '   <span class="chartjs-tooltip-value">' + tooltip.labels[i] + '</span>',
                '</div>'
            ].join('');
        }
        tooltipEl.html(innerHtml);

        tooltipEl.css({
            opacity: 1,
            // left: tooltip.chart.canvas.offsetLeft + tooltip.x + 'px',
            // top: tooltip.chart.canvas.offsetTop + tooltip.y + 'px',
            fontFamily: tooltip.fontFamily,
            fontSize: tooltip.fontSize,
            fontStyle: tooltip.fontStyle,
        });

    };
    var lineChartData2 = {
        labels: ["2015.1", "02", "03", "04", "05", "06", "07","08","09","10","11"],
        datasets: [{
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.2)",
            strokeColor: "rgba(220,220,220,1)",
            pointColor: "rgba(220,220,220,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(220,220,220,1)",
            // data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }, {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.2)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(151,187,205,1)",
            data: [100, 106, 171, 166, 179, 187, 178, 129, 228, 266, 287, 287, 0]
        }]
    };



    window.onload = function() {
        var ctx1 = document.getElementById("chart").getContext("2d");
        window.myLine = new Chart(ctx1).Line(lineChartData, {
            // showScale: false,
            // pointDot : true,
            responsive: true
        });

        var ctx2 = document.getElementById("chart2").getContext("2d");
        window.myLine2 = new Chart(ctx2).Line(lineChartData2, {
            responsive: true
        });
    };





    
    var swiper = new Swiper('.smartPhoneContainer .swiper-container', {
    	initialSlide:0,
    	autoplay:2000,
        pagination: '.smartPhoneContainer .swiper-pagination',
        paginationClickable: true,
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        parallax: true,
        grabCursor: true,
        speed: 600,

        onTransitionStart: function(index) {

        	

        	// for(i in index){
        	// 	console.log("index["+i+"] : " + index[i]);
        	// }


        	// if(){

        	// }else(){

        	// }
        },
        onSlideChangeEnd: function(swiper){

        	
        },
        onSetTranslate: function(swiper, translate){
        	//translate가 저체 이미지를 쭉 이어 붙인걸로 봤을 때 얼마만큼 이동했는지를 보여준다. 이걸로 현재 이미지에 대한 설명을 표기할 수 있을 것 같다.
        	

        	if(translate == 0){
        		$('.textContainerBest').hide();
        		$('.textContainerTrade').hide();
        		$('.textContainerLoveItStyle').hide();
        		$('.textContainer').show('fade');
        	}else if(translate == -276){
        		$('.textContainer').hide();
        		$('.textContainerTrade').hide();
        		$('.textContainerLoveItStyle').hide();
        		$('.textContainerBest').show('slide', { direction: 'down'}, 600);
        	}else if(translate == -552){
        		$('.textContainer').hide();
        		$('.textContainerBest').hide();
        		$('.textContainerLoveItStyle').hide();
        		$('.textContainerTrade').show('slide', { direction: 'down'}, 600);
        	}else if(translate == -828){
        		$('.textContainer').hide();
        		$('.textContainerBest').hide();
        		$('.textContainerTrade').hide();
        		$('.textContainerLoveItStyle').show('slide', { direction: 'down'}, 600);
        	}


        }

    });
   


    var swiper1 = new Swiper('.single-table .swiper-container', {
        initialSlide:0,
        autoplay:false,
        direction:'vertical',
        pagination: 'single-table .swiper-pagination',
        paginationClickable: true,
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        // parallax: true,
        grabCursor: true,
        speed: 3000

        });



    $('.nav navbar-nav navbar-right').bind('click', function() {
        // console.log("언제든지 네비바 클릭 감시");


    });


	

});

    

function numberUp(){

    var $el = $("#number1");
    $({ val : 0 }).animate({ val : rn }, {
      duration: 2000,
      step: function() {
        $el.text(Math.floor(this.val));
      },
      complete: function() {
        $el.text(Math.floor(this.val));
      }
    });

    var $el2 = $("#number2");
    $({ val : 0 }).animate({ val : rn2 }, {
      duration: 2000,
      step: function() {
        $el2.text(Math.floor(this.val));
      },
      complete: function() {
        $el2.text(Math.floor(this.val));
      }
    });


}
