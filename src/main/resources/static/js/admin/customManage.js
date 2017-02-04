	/**
	 * 客户管理页面的js
	 * 包含表格的处理逻辑
	 * @author Cherish
	 * @date 2016年8月21日 下午9:10:05
	 */
	var oTable;
	$(document).ready(function() {
		oTable = $('#otable').DataTable(
			//拼接options参数
			$.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
			//ajax配置为function,手动调用异步查询
			"ajax" : function(data, callback, settings) {
				//封装请求参数
				var param = customManage.getQueryCondition(data);
				$.ajax({
					type : "GET",
					url : "/custom/list",//TODO
					cache : false, //禁用缓存
					data : param, //传入已封装的参数
					dataType : "json",
					success : function(result) {
						//异常判断与处理
						if (!result.success) {
							window.top.myModalFail("查询失败。错误信息：" + result.message);
							return;
						}
						result = result.data;
						//封装返回数据，这里仅演示了修改属性名
						var returnData = {};
						returnData.draw = result.draw;//这里直接自行返回了draw计数器,应该由后台返回
						returnData.recordsTotal = result.total;
						returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.pageData;
						//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
						//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
						callback(returnData);
					},
					error : function(XMLHttpRequest,textStatus,errorThrown) {
						window.top.myModalFail("查询失败,error！");
					}
				});
			},
			"columns" : [
			    CONSTANT.DATA_TABLES.COLUMN.NO,
			    {
					"data" : 'nickname'
				}, {
					"data" : 'telephone'
				}, {
					"data" : 'createtime'
				}, {
					"data" : 'active',
					"render" : function (data, type, row, meta) {
                        return activeHTML(data);
                    }
				},
				CONSTANT.DATA_TABLES.COLUMN.OPERATION
				],
			"columnDefs" : [ {
					"searchable" : false,
					"orderable" : false,
					"targets" : "_all"
				}]
		}));//end $('#otable').DataTable($.extend({
		
		//表体的点击事件，添加点击行样式
		$('#otable tbody').on('click','tr',function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				oTable.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		//查询
		$("#btn_search").click(function(){
			customManage.fuzzySearch = false;
			//reload效果与draw(true)或者draw()类似,
			//draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
			//oTable.ajax.reload(); oTable.draw(false);
			oTable.draw();
		});
		//重置
		$("#btn_reset").click(function(){
			customManage.fuzzySearch = false;
			$("#tel").val("");
			$("#nick").val("");
			oTable.draw();
		});
		//刷新
		$("#btn_fresh").click(function(){
			customManage.fuzzySearch = false;
			oTable.draw(false);
		});
		
		// 回车键事件 
		$("#tel").keypress(function(e) { 
	        if(e.keyCode == 13) {
	    	   $("#btn_search").click();
	        }
	        return;
	    });
		$("#nick").keypress(function(e) { 
	        if(e.keyCode == 13) {
	    	   $("#btn_search").click();
	        }
	        return;
	    });
		
	});

	//客户表格的管理机制
	var customManage = {
		currentItem : null,//储存当前被选中的行
		fuzzySearch : false,//是否模糊查询
		getQueryCondition : function(data) {
			var param = {};
			//组装排序参数
			if (data.order && data.order.length && data.order[0]) {
				switch (data.order[0].column) {
				case 1:
					param.orderColumn = "nickname";
					break;
				case 2:
					param.orderColumn = "telephone";
					break;
				case 3:
					param.orderColumn = "createtime";
					break;
				default:
					param.orderColumn = "createtime";
					break;
				}
				param.orderDir = data.order[0].dir;
			}
			//组装查询参数
			param.fuzzySearch = customManage.fuzzySearch;
			if (customManage.fuzzySearch) {//模糊查询
				param.fuzzy = $("#fuzzy-search").val();
			} else {//非模糊查询
				param.telephone = $("#tel").val();
				param.nickname = $("#nick").val();
			}
			//组装分页参数
			param.startIndex = data.start;
			param.pageSize = data.length;

			param.draw = data.draw;

			return param;
		}
	};
	
	function activeHTML(active){
		var s = "<span style='color:#000000'>冻结</span>";
		if("0" == active)
			s = "<span style='color:#000000;font-weight:blod;'>冻结</span>";
		else if("1" == active)
			s = "<span style='color:#009900;font-weight:blod;'>激活</span>";
		else
			s = "<span style='color:#CC0000;font-weight:blod;'>未知</span>";
		return s;
	}