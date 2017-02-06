$(function(){
	/*$("#tabs").tabs({
		width:$("#tabs").parent().width(),
		height:'100%'
	}); *///适配IE
	/*if(getUserName()!=''){
		$("#nameSpan").text(getUserName());
	}else{
		window.location.href = "../consoleLogin/toLogin";
	}*/
	tabs=$("#tabs");
	$("#sysmenu-ul").tree({
		onClick:function(node) {
			if( node.menuUrl == null || node.state == null || node.state == '' || node.state == undefined){
				$("#sysmenu-ul").tree('toggle', node.target);
				return false;
			}
			if (tabs.tabs('exists', node.text)){//如果tab已经存在,则选中         
				tabs.tabs('select', node.text);
				//getAccessToken(node.menuUrl,function(token){
					var token = "";
					refreshTab(node,token);
				//});
				
	        } else { 
	        	//getAccessToken(node.menuUrl,function(token){
	        		var token = "";
	        		addTab(node,token);
				//});
	        }
		}
	});
	tabs.tabs('add',{
		title:'首页',
	    content:'<div style=\"margin:30px 50px\"><h1>首页</h1><h2>1.什么样的应用都会有一个首页</h2><h2>2.漂亮的首页能给用户留下美好的映像。</h2><h2>3.管理系统的首页做成多个portal即可。</h2><h2>4.构建神奇应用，先从首页开始。</h2><h2>5.努力吧，少年。。。</h2></div>',
	    closable:false
	});
});

// 添加tab
function addTab(node,token){
	$('#tabs').tabs('add',{
		title:node.text,
	    content:'<iframe name=\"'+node.id+'\" id=\"'+node.id+'\" scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\" width=\"100%\" height=\"100%\" bordercolor=\"#242424\" src=\"'+node.menuUrl+'?token='+token+'\" border=\"0\"></iframe>',
	    closable:true
	});
}

// 刷新tab
function refreshTab(node,token){
	var tab = $('#tabs').tabs('getSelected'); 
	$('#tabs').tabs('update', {
		tab: tab,
		options: {
			title:node.text,
		    content:'<iframe name=\"'+node.id+'\" id=\"'+node.id+'\" scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\" width=\"100%\" height=\"100%\" bordercolor=\"#242424\" src=\"'+node.menuUrl+'?token='+token+'\" border=\"0\"></iframe>',
		    closable:true
		}
	});
}