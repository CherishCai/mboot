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
							myModalFail("查询失败。错误信息：" + result.message);
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
						myModalFail("查询失败,error！");
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
					"data" : 'hiredate'
                }, {
                    "data" : 'roleId',
                    "render" : function (data, type, row, meta) {
                        return roleHTML(data);
                    }
				}, {
					"data" : 'createdTime'
				}, {
					"data" : 'username'
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
		
		//查询
		$("#btn_search").click(function(){
			userManage.fuzzySearch = false;
			//reload效果与draw(true)或者draw()类似,
			//draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
			//oTable.ajax.reload(); oTable.draw(false);
			oTable.draw();
		});
		//重置
		$("#btn_reset").click(function(){
			userManage.fuzzySearch = false;
			$("#tel").val("");
			$("#nick").val("");
			oTable.draw();
		});
		//刷新
		$("#btn_fresh").click(function(){
			userManage.fuzzySearch = false;
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
					param.orderColumn = "createtime";
					break;
				case 4:
					param.orderColumn = "username";
					break;
				default:
					param.orderColumn = "createtime";
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

    });

    //表格行删除操作
    $('#otable').on('click', 'a.op_delete', function (e) {
        e.preventDefault();

        window.top.myConfirm("你确定要删除吗?",function(){
            var nRow = $(this).parents('tr')[0];
            var id = oTable.row(nRow).id();
            //向服务器提交删除请求
            var url = "/user/"+id;
            var result = delAjax(url);

            if(result.success){
                window.top.myModalSuccess(result.message);
                //删除页面中的原有行
                oTable.row(nRow).remove().draw(false);
            }else{
                window.top.myModalFail(result.message);
            }
        });

    });

    //表格行编辑操作
    $('#otable').on('click', 'a.op_edit', function (e) {
        e.preventDefault();
        /* Get the row as a parent of the link that was clicked on */
        var nRow = $(this).parents('tr')[0];

        editRow(oTable, nRow);
    });

    //编辑，打开模态框
    function editRow(oTable, nRow) {
        var rowData = oTable.row(nRow).data();

        var bodyHTML = '<input type="hidden" id="id_modal" value="'+rowData.id+'">'
                   +'姓名：'
                   +'<input type="text" id="nickname_modal" class="form-control" placeholder="员工姓名" value="'+rowData.nickname+'">'
                   +'手机：'
                   +'<input type="text" id="telephone_modal" class="form-control" placeholder="员工手机号" value="'+rowData.telephone+'">'
                   +'入职时间：'
                   +'<input type="text" id="createtime_modal" class="form-control" placeholder="入职时间" value="'+rowData.createtime+'">'
                   +'登陆账号：'
                   +'<input type="text" id="username_modal" class="form-control" readonly value="'+rowData.username+'">'
                   +'登陆密码：'
                   +'<input type="text" id="password_modal" class="form-control" placeholder="默认不显示，若要修改请填写新密码" value="">'
                   +'职业：'
                   +roleSelect(rowData.roleId)
                   +'状态：'
                   +activeSelect(rowData.active)
                   +'描述：'
                   +window.top.toolbar()
                   +'<div id="editor" contenteditable="true">'+rowData.description+'</div>'
                   +'';
        window.top.myEditModal(bodyHTML,function(){
            var id = window.top.$("#id_modal").val();
            var nickname = window.top.$("#nickname_modal").val();
            var telephone = window.top.$("#telephone_modal").val();
            var createtime = window.top.$("#createtime_modal").val();
            var username = window.top.$("#username_modal").val();
            var password = window.top.$("#password_modal").val();
            var roleId = window.top.$("#roleId :selected").val();
            var active = window.top.$("#active :selected").val();
            var description = window.top.$("#editor").html();

            var json = {};
            json.id = id;
            json.nickname = nickname;
            json.telephone = telephone;
            json.createtime = createtime;
            json.username = username;
            json.password = password;
            json.roleId = roleId;
            json.active = active;
            json.description = description;

            var url = "/user/edit";
            var result = postAjax(url, json);

            if(result.success){
                window.top.myModalSuccess(result.message);
                oTable.draw(false);
                return true;
            }else{
                window.top.myModalFail(result.message);
                return false;
            }
        });
    }

	function roleHTML(roleId){
	    var s = "<span style='color:#CC0000'>前台人员</span>";
    		if(1 == roleId)
    			s = "<span style='color:blue;font-weight:blod;'>管理员</span>";
    		else if(2 == roleId)
    			s = "<span style='color:#009900;font-weight:blod;'>推拿师</span>";
    		else
    			s = "<span style='color:#CC0000;font-weight:blod;'>前台人员</span>";
    		return s;
	}

	function roleSelect(roleId){
	    var s = '<select name="roleId" id="roleId" class="form-control">'
            if(1 == roleId){
                s += '<option value="1" selected>管理员</option>'
                      +'<option value="2">推拿师</option>'
                      +'<option value="3">前台人员</option>';
            }
            else if(2 == roleId){
                s += '<option value="1">管理员</option>'
                          +'<option value="2" selected>推拿师</option>'
                          +'<option value="3">前台人员</option>';
            }
            else{
                s += '<option value="1" selected>管理员</option>'
                      +'<option value="2">推拿师</option>'
                      +'<option value="3" selected>前台人员</option>';
            }
            s +='</select>';
            return s;
	}

	function activeHTML(isActive){
		var s = "<span style='color:#000000'>冻结/已离职</span>";
		if(0 == isActive)
			s = "<span style='color:#000000;font-weight:blod;'>冻结/已离职</span>";
		else if(1 == isActive)
			s = "<span style='color:#009900;font-weight:blod;'>激活/在职</span>";
		else
			s = "<span style='color:#CC0000;font-weight:blod;'>未知</span>";
		return s;
	}

	function activeSelect(active){
        var s = '<select name="active" id="active" class="form-control">'
        if("0" == active){
            s += '<option value="0" selected>冻结/离职</option>'
                 +'<option value="1">激活/在职</option>';
        }
        else if("1" == active){
            s += '<option value="0">冻结/离职</option>'
                 +'<option value="1" selected>激活/在职</option>';
        }
        else{
            s += +'<option value="">请选择</option>'
                 +'<option value="0">冻结/离职</option>'
                 +'<option value="1">激活/在职</option>';
        }
        s +='</select>';
        return s;
    }