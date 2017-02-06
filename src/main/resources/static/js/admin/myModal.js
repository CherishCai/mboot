/**
 * 自行封装的Bootstrap模态框
 * @author Cherish
 * @date 2016年8月29日 下午9:10:05
 */
$(document).ready(function() {
	var modalHtml = '<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">'
						+'<div class="modal-dialog modal-sm">'
						    +'<div class="modal-content">'
							    +'<div class="modal-header">'
								    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
								    +'<h4 class="modal-title">Modal 头部</h4>'
								+'</div>'
								+'<div class="modal-body">'
								    +'<p class="text-center">身体</p>'
							    +'</div>'
							    +'<div class="modal-footer">'
								    +'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
							    +'</div>'
						    +'</div>'
					    +'</div>'
				    +'</div>';
	var confirmModalHtml = '<div id="myConfirm" class="modal fade" tabindex="-2" role="dialog" aria-labelledby="myConfirmModalLabel">'
								+'<div class="modal-dialog modal-sm">'
								    +'<div class="modal-content">'
									    +'<div class="modal-header">'
										    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
										    +'<h4 class="modal-title">提示</h4>'
										+'</div>'
										+'<div class="modal-body">'
										    +'<p class="text-center">你确定要删除吗?</p>'
									    +'</div>'
									    +'<div class="modal-footer">'
									    	+'<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>'
										    +'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
									    +'</div>'
								    +'</div>'
							    +'</div>'
						    +'</div>';
	var editModalHtml = '<div id="myEditModal" class="modal fade bs-example-modal-lg" tabindex="-3" role="dialog" aria-labelledby="myEditModalLabel">'
								+'<div class="modal-dialog modal-lg">'
								    +'<div class="modal-content">'
									    +'<div class="modal-header">'
										    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
										    +'<h4 class="modal-title">修改数据</h4>'
										+'</div>'
										+'<div class="modal-body">'
										    +'<p class="text-center">修改的详细情况</p>'
									    +'</div>'
									    +'<div class="modal-footer">'
									    	+'<button type="button" class="btn btn-primary">确定</button>'
										    +'<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'
									    +'</div>'
								    +'</div>'
							    +'</div>'
							+'</div>';
	$("body").append(modalHtml).append(confirmModalHtml).append(editModalHtml);
});

//自行封装的模态框调用函数
function myModalWarning(warningBody){
	myModal("white","#FF9900",'提示',warningBody);
}
function myModalSuccess(successBody){
	myModal("white","#00CC00",'成功',successBody);
}
function myModalFail(failBody){
	myModal("white","#FF0000",'错误',failBody);
}
function myModal(title,warningBody){
	myModal("white","#FF9900",title,warningBody);
}
function myModal(color,background,title,body,hideFunction){
	$("#myModal")
			//show 方法调用之后立即触发该事件
			.on('show.bs.modal', function (event) {
				  var modal = $(this);
				  modal.find('.modal-header').css({
					  "color":color,
					  "background-color":background
					  });
				  modal.find('.modal-title').text(title);
				  modal.find('.modal-body .text-center').text(body);
			})
			//hide 方法调用之后立即触发该事件。
			.one('hide.bs.modal', hideFunction);
	$("#myModal").modal({
		backdrop:"static"
	});
}
/* 模态框之confirm */
function myConfirm(body,sureFunction){
	$("#myConfirm")
	//show 方法调用之后立即触发该事件
	.on('show.bs.modal', function (event) {
		var modal = $(this);
		modal.find('.modal-body .text-center').text(body);
		var btn = modal.find('.modal-footer .btn-primary');
		$(btn).removeClass("OK");
		$(btn).unbind();
		$(btn).click(function(){
			$(this).addClass("OK");
		});
	})
	//hide 方法调用之后立即触发该事件
	.one('hidden.bs.modal', function(event){
		var modal = $(this);
		var btn = modal.find('.modal-footer .btn-primary');
		var isDel = $(btn).hasClass("OK");
		$(btn).removeClass("OK");
		if (!isDel) {
			return;
		}
		sureFunction();//调用隐藏函数
	});
	$("#myConfirm").modal({
		backdrop:"static"
	});
}


/* 模态框之edit */
function myEditModal(bodyHTML,editFunction){ 
	$("#myEditModal")
	//show 方法调用之后立即触发该事件
	.on('show.bs.modal', function (event) {
		  var modal = $(this);
		  modal.find('.modal-body').html(bodyHTML);
		  var btn = modal.find('.modal-footer .btn-primary');
		  $(btn).unbind();
		  $(btn).one("click",function(){
			  var success = editFunction();
			  $("#myEditModal").modal("hide");
		  });
		  $('#editor').wysiwyg();
	})
	//hide 方法调用之后立即触发该事件
	.one('hidden.bs.modal', function(event){
		var modal = $(this);
		modal.find('.modal-body .text-center').text("出错，请重新打开！");
	});
	$("#myEditModal").modal({
		backdrop:"static"
	});
}
