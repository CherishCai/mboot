	/**
	 * 员工管理页面的js
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
				var param = userManage.getQueryCondition(data);
				$.ajax({
					type : "GET",
					url : "/user/page",//TODO
					cache : false, //禁用缓存
					data : param, //传入已封装的参数
					dataType : "json",
					success : function(result) {
						//异常判断与处理
						if (!result.success) {
							myModalFail("查询失败，" + result.message);
							return;
						}
						var pageInfo = result.data;
						//封装返回数据，这里仅演示了修改属性名
						var returnData = {};
						returnData.draw = result.message;//这里直接自行返回了draw计数器,应该由后台返回
						returnData.recordsTotal = pageInfo.totalElements;
						returnData.recordsFiltered = pageInfo.totalElements;//后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = pageInfo.content;
						//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
						//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
						callback(returnData);
					},
					error : function(XMLHttpRequest,textStatus,errorThrown) {
						myModalFail("查询失败");
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
					"data" : 'position'
                }, {
                    "data" : 'hiredate'
				}, {
					"data" : 'username'
				}, {
					"data" : 'activeStr'
				},
				CONSTANT.DATA_TABLES.COLUMN.OPERATION
				],
			"columnDefs" : [ {
					"searchable" : false,
					"orderable" : false,
					"targets" : "_all"
				}]
		}));//end $('#otable').DataTable($.extend({
		
		//查询
		$("#btn_search").click(function(){
			//reload效果与draw(true)或者draw()类似,
			//draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
			//oTable.ajax.reload(); oTable.draw(false);
			oTable.draw();
		});
		//重置
		$("#btn_reset").click(function(){
			$("#tel").val("");
			$("#nick").val("");
			oTable.draw();
		});
		//刷新
		$("#btn_fresh").click(function(){
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
	var userManage = {
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
					param.orderColumn = "position";
					break;
				case 4:
					param.orderColumn = "hiredate";
					break;
				default:
					param.orderColumn = "id";
					break;
				}
				param.orderDir = data.order[0].dir;
			}
			//组装查询参数
			param.fuzzySearch = userManage.fuzzySearch;
			if (userManage.fuzzySearch) {//模糊查询
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

	 //新增行
    $('#otable_new').on('click', function (e) {
        e.preventDefault();
        var url = "/user/add";
        window.open(url, "_self");
    });

    //表格行删除操作
    $('#otable').on('click', 'a.op_delete', function (e) {
        e.preventDefault();

		var nRow = $(this).parents('tr')[0];
		var id = oTable.row(nRow).id();

        myConfirm("你确定要删除吗?",function(){
            //向服务器提交删除请求
            var url = "/user/"+id+"/delete";
            var result = delAjax(url);

            if(result.success){
                myModalSuccess(result.message);
                //删除页面中的原有行
                oTable.row(nRow).remove().draw(false);
            }else{
                myModalFail(result.message);
            }
        });

    });

    //表格行编辑操作
    $('#otable').on('click', 'a.op_edit', function (e) {
        e.preventDefault();
        /* Get the row as a parent of the link that was clicked on */
        var nRow = $(this).parents('tr')[0];
        var id = oTable.row(nRow).id();
        var url = "/user/" + id + "/update";
        window.open(url, "_self");
    });