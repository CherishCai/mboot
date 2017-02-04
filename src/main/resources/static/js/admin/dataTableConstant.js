/*常量*/
var CONSTANT = {
		DATA_TABLES : {
			DEFAULT_OPTION : { //DataTables初始化选项
				"lengthMenu" : [
						[ 10, 20, 50, 100 ],
						[ 10, 20, 50, 100 ] ],
				"displayLength" : 20,
				"dom" : "<'row'<'col-sm-6'l><'col-sm-6'p>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
				"pagingType" : "simple_numbers",
				"language" : {
					"lengthMenu" : "每页  _MENU_ 项",
					"zeroRecords" : "没有匹配结果",
					"info" : "当前显示第  _START_ 至  _END_ 项，共  _TOTAL_ 项，_PAGES_ 页。",
					"infoEmpty" : "当前显示第 0 至 0 项，共 0 项",
					"infoFiltered" : "(由  _MAX_ 项结果过滤)",
					"processing" : "处理中...",
					"infoPostFix" : "",
					"search" : "搜索:",
					"url" : "",
					"emptyTable" : "表中数据为空",
					"loadingRecords" : "载入中...",
					"infoThousands" : ",",
					"paginate" : {
						"first" : "首页",
						"previous" : "上页",
						"next" : "下页",
						"last" : "末页"
					},
					"aria" : {
						"sortAscending" : ": 以升序排列此列",
						"sortDescending" : ": 以降序排列此列"
					}
				},
				"processing" : true,
				"serverSide" : true,
				"rowId" : 'id',
				"order" : [], //取消默认排序查询,否则复选框一列会出现小箭头
				"autoWidth" : true, //自动调整列宽
				"responsive" : true, //响应式
				"stripeClasses" : [ "odd","even" ],//为奇偶行加上样式，兼容不支持CSS伪类的场合
				"searching" : false //禁用原生搜索
				/*
				 //draw回调函数，第0列添加行号
				"drawCallback": function(settings){
					var api = this.api();
					//为每行添加索引列，即行号
					var startIndex = api.context[0]._iDisplayStart;//获取到本页开始的条数
					api.column(0).nodes().each(function(cell, i) {
						cell.innerHTML = startIndex + i + 1;
					}); 
				},
				*/
			},
			COLUMN: {
				DETAILS: {
	                "className": "details-control",
	                "orderable": false,
	                "data": null,
	                "defaultContent": ""
	            },
                NO: {	//行号
                	"className": "td_no",
                    "orderable": false,
                    "data": null,
                    "render": function (data, type, row, meta) {
                    	var no = meta.settings._iDisplayStart + meta.row + 1;
                        return no;
                    }
                },
                OPERATION: { //操作
                	"className": "td_operation",
                	"orderable": false,
                	"width": "100px",
                    "data": null,
                    "render": function (data, type, row, meta) {// btn-group-justified
                    	var btn_group =  "<div class='btn-group btn-group-sm btn-group-justified' role='group' aria-label='操作'>"
			                    +"<a href='#' class='op_edit btn btn-warning' role='button'>编辑</a>"
			                    +"<a href='#' class='op_delete btn btn-danger' role='button'>删除</a>"
			                    +"</div>";
			            return btn_group;
                    }
                },
                CHECKBOX: {	//复选框单元格
                    "className": "td-checkbox",
                    "orderable": false,
                    "width": "30px",
                    "data": null,
                    "render": function (data, type, row, meta) {
                        return '<input type="checkbox" class="iCheck">';
                    }
                }
            },
            RENDER: {	//常用render可以抽取出来，如日期时间、头像等
                ELLIPSIS: function (data, type, row, meta) {
                	data = data||"";
                	return '<span title="' + data + '">' + data + '</span>';
                },
	            LOG: function (data, type, row, meta) {
	                	console.log(data);
	                	console.log(type);
	                	console.log(row);
	                	console.log(meta);
	                    return 'log';
	                }
	            }
		}
};