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
	},
	calcPrize: function(orderNo) {
		if (orderNo == -1) {
			this.prize = -1;
			console.log('calcPrize1: ' + this.prize + ', orderNo: ' + orderNo);
			return this.prize;
		}
		var lottery = this.obj;
		console.log('orderNo: ' + orderNo)
//		$('.lottery-unit').removeClass("active");
		var awards = $(lottery).find(".lottery-orderno-"+orderNo);
		if (!!awards){
			var length = awards.length;
			var random = randomInt(length);
			var award = awards.get(random);
			var clazz = $(award).attr('class');
			var re = /lottery-unit-(\d+)/;
			var match = re.exec(clazz);
			var no = match[1];
			this.prize = parseInt(no);
			console.log('calcPrize: ' + this.prize + ', orderNo: ' + orderNo);
			return this.prize;
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
	if (lottery.times > lottery.cycle+15 && (result == null || lottery.prize == -1) ) {
		lottery.result = '未发现奖品1';
		$('#errorMsg').show();
		$('#errorMsg').text(lottery.result);
		clearTimeout(lottery.timer);
		/*lottery.draw(lottery.prize);*/
		lottery.prize=-1;
		lottery.times=0;
		clickable=true;
		alert(lottery.result);
		return;
	}
	if (lottery.times > lottery.cycle+10 && (lottery.prize==lottery.index || lottery.prize != -1)) {
		clearTimeout(lottery.timer);
//		lottery.draw(lottery.prize);
		$('#errorMsg').show();
		$('#errorMsg').text(lottery.result);
		lottery.prize=-1;
		lottery.times=0;
		clickable=true;
		alert(lottery.result);
		return;
	}else{
		if (lottery.times<lottery.cycle) {
			lottery.speed -= 10;
		}else if(lottery.times==lottery.cycle) {
//			var index = Math.random()*(lottery.count)|0;
//			lottery.prize = index;	
			if (result != null) {
				if (result.success) {
					lottery.prize = lottery.calcPrize(result.awardPOJO.orderNo);
					lottery.result = result.result;
				} else {
					lottery.prize = -1;
					lottery.result = result.result;
				}
			} else {
				lottery.result = '未发现奖品2';
				lottery.prize = -1;
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
	/*if (lottery.prize > -1) {
		$('#errorMsg').show();
		$('#errorMsg').val(lottery.result);
	}*/
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

var checkValid = function() {
	var ret = {
			"valid": false,
			"description": ''
	};
	var interactiveId = $("#interactiveId").val();
	
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/awardRecord/checkValid?interactiveId=" + interactiveId,
		/*"type" : "POST",*/
		"async": false,
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		"data": ({
			interactiveId: interactiveId
        }),
        success: function(data, textStatus, jqXHR ) {
        	if (data.success) {
        		ret.valid = data.valid;
        		ret.description = data.description;
        	} else {
        		ret.valid = false;
        		ret.description = data.description;
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Ajax error');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});	// ajax
	return ret;
}

var clickable=true;

window.onload=function(){
	lottery.init('lottery');
	$("#lottery a").click(function(){
		var result = checkValid();
		
		if (!result.valid) {
			alert(result.description);
			return false;
		}
		
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
