function allcheck(){
	var checkObj = document.getElementsByName("check");
	for(var i=0; i<checkObj.length; i++)
		checkObj[i].checked = true;			
}

function allnotcheck(){
	var checkObj = document.getElementsByName("check");
	for(var i=0; i<checkObj.length; i++)
		checkObj[i].checked = false;			
}

function backcheck(){
	var checkObj = document.getElementsByName("check");
	for(var i=0; i<checkObj.length; i++)
		if(checkObj[i].checked == true)
			checkObj[i].checked = false;
		else
			checkObj[i].checked = true;
}

function insertCheck(){
	location.href="insertCheckGoodsInfoServlet.jsp";
}	

