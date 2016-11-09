var lottery={
	index:-1,	//当前转动到哪个位置，起点位置
	count:0,	//总共有多少个位置
	timer:0,	//setTimeout的ID，用clearTimeout清除
	speed:20,	//初始转动速度
	times:0,	//转动次数
	cycle:50,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
	prize:-1,	//中奖位置
	result:'',
	init:function(id){
		if ($("#"+id).find(".lottery-unit").length>0) {
			$lottery = $("#"+id);
			$units = $lottery.find(".lottery-unit");
			this.obj = $lottery;
			this.count = $units.length;
			$lottery.find(".lottery-unit-"+this.index).addClass("active");
		};
	},
	roll:function(){
		var index = this.index;
		var count = this.count;
		var lottery = this.obj;
		$(lottery).find(".lottery-unit-"+index).removeClass("active");
		index += 1;
		if (index > count-1) {
			index = 0;
		};
		$(lottery).find(".lottery-unit-"+index).addClass("active");
		this.index=index;
		return false;
	},
	stop:function(index){
		this.prize=index;
		return false;
	},
	draw: function(orderNo) {
		var lottery = this.obj;
		$('.lottery-unit').removeClass("active");
		var awards = $(lottery).find(".lottery-orderno-"+orderNo);
		if (!!awards){
			var length = awards.length;
			var random = randomInt(length);
			var award = awards.get(random);
			$(award).addClass("active");
			$('#errorMsg').show();
			$('#errorMsg').val(lottery.result);
		}
	}
};

function randomInt(max) {
	return Math.floor(Math.random() * max);
}

var result = null;

function roll(){
	lottery.times += 1;
	lottery.roll();
	if (lottery.times > lottery.cycle+10 && result == null) {
		lottery.result = '未发现奖品';
		$('#errorMsg').show();
		$('#errorMsg').text(lottery.result);
		clearTimeout(lottery.timer);
		lottery.draw(lottery.prize);
		lottery.prize=-1;
		lottery.times=0;
		clickable=true;
	}
	if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
		clearTimeout(lottery.timer);
		lottery.draw(lottery.prize);
		$('#errorMsg').show();
		$('#errorMsg').text(lottery.result);
		lottery.prize=-1;
		lottery.times=0;
		clickable=true;
	}else{
		if (lottery.times<lottery.cycle) {
			lottery.speed -= 10;
		}else if(lottery.times==lottery.cycle) {
//			var index = Math.random()*(lottery.count)|0;
//			lottery.prize = index;	
			if (result != null) {
				if (result.success && result.isHappy) {
					lottery.prize = result.awardPOJO.orderNo;
					lottery.result = result.result;
				} else {
					lottery.prize = result.awardPOJO.orderNo;
					lottery.result = result.result;
				}
			} else {
				lottery.result = '未发现奖品';
			}
		}else{
			if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
				lottery.speed += 110;
			}else{
				lottery.speed += 20;
			}
		}
		if (lottery.speed<40) {
			lottery.speed=40;
		};
		//console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
		lottery.timer = setTimeout(roll,lottery.speed);
	}
	if (lottery.prize > -1) {
		$('#errorMsg').show();
		$('#errorMsg').val(lottery.result);
	}
	console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
	return false;
}

function lotteryHappy() {
	var interactiveId = $('#interactiveId').val();
	
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/lottery/" + interactiveId + '/happy',
		/*"type" : "POST",*/
		"async": true,
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		"data": ({
			interactiveId: interactiveId
        }),
        success: function(data, textStatus, jqXHR ) {
        	result = data;
        	console.log('result: ' + result);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Ajax error');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});	// ajax
}

var clickable=true;

window.onload=function(){
	lottery.init('lottery');
	$("#lottery a").click(function(){
		if (!clickable) {
			alert('您已经抽过奖了');
			return false;
		} else {
			result = null;
			lotteryHappy();
			lottery.speed=100;
			roll();
			clickable=false;
			return false;
		}
	});
};
