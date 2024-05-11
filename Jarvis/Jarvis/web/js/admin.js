// JavaScript Document
	$(document).ready(function(){
    		$(".click-open").click(function(){
				$(this).parent().find(".open-item").slideToggle(200);
				$(this).toggleClass("menu-item-opened");
				return false;
			});
		});
	
	function changePlus(x) {
    	x.classList.toggle("change");
	}	