var url;

var allOrgType = {
	valueField: 'value',
	textField: 'label',
	data: [{label: '公司', value: '1'},{label: '部门', value: '2'}]
};
var deptOrgType = {
	valueField: 'value',
	textField: 'label',
	data: [{label: '部门', value: '2'}]
};
var orgOrgType = {
	valueField: 'value',
	textField: 'label',
	data: [{label: '公司', value: '1'}]
};

// 新增
function addOrg(type) {
    $('#fm-org').form('clear');
    var node = $('#orgTree').tree('getSelected');
    if (!node) {
        $.messager.alert('提示','请从机构树中选择上级机构。');
        return;
    }
    var parentType = node['orgType'];
    if (parentType == 2 && type < 2) {
        $.messager.alert('提示','部门下只能添加部门，无法添加公司。');
        return;
    }
    if (parentType == 1) {
    	$("#orgTypeSelector").combobox(allOrgType);
    } else if (parentType == 2) {
    	$("#orgTypeSelector").combobox(deptOrgType);
    } else {
        $.messager.alert('提示','非法操作，请刷新后再试。');
    	return;
    }
    $('#o-parentOrgName').html(node.nameFullpath);
	$("#orgTypeSelector").combobox('setValue', type);
    $('#o-parentId').val(node.id);
    $('#o-idFullpath').val(node.idFullpath);
	url = getUrl('/system/org/save');
    $('#dlg-org').dialog('open').dialog('setTitle', '新增机构');
}
// 修改
function updateOrg() {
    var node = $('#orgTree').tree('getSelected');
    if (node) {
        var parent = $('#orgTree').tree('getParent', node.target);
        var ckb = orgOrgType;
        if (parent) {
        	var orgType = parent['orgType'];
        	// 如果上级是部门，则下级只能修改为部门
        	if (orgType == 2) {
        		ckb = deptOrgType;
        	} 
        	// 如果上级是公司，则下级可以是公司或部门，但需要考虑下级的下级。
        	else if (orgType == 1) {
        		// 下级的下级只要有一个是公司，则上级只能修改为公司
        		var justOrg = false;
        		var cldNodes = $('#orgTree').tree('getChildren', node.target);
        		for (var i = 0; i < cldNodes.length; i++) {
        			if (cldNodes[i]['orgType'] == 1) {
        				justOrg = true;
        				break;
        			}
        		}
        		ckb = justOrg ? orgOrgType : allOrgType;
        	}
        }
    	$("#orgTypeSelector").combobox(ckb);
        $('#fm-org').form('load',node);
        $('input[name=oldNameFullpath]').val(node.nameFullpath);
        $('#o-parentOrgName').html(getParentNameFullpath(node.nameFullpath));
        $('#dlg-org').dialog('open').dialog('setTitle','修改');
        url = getUrl('/system/org/update?id=' + node.id);
        return;
    }
    $.messager.alert('提示','请选择要修改的行！');
}

function getParentNameFullpath(nameFullpath) {
	var pnf = nameFullpath || '/';
	if (pnf) {
		pnf = pnf.substring(0, pnf.lastIndexOf('/'));
	}
	if (!pnf) {
		return '/';
	}
	return pnf;
}

// 提交保存
function saveOrg() {
	var pnf = $('#o-parentOrgName').html();
	if (pnf != '/') {
		pnf = pnf + '/'; 
	}
	pnf += $('input[name=shortName]').val();
	$('input[name=nameFullpath]').val(pnf);
	var mask = {};
    $('#fm-org').form('submit', {
        url: url,
        onSubmit: function() {
        	if ($(this).form('validate')) {
        		mask = new Mask({maskMsg: '正在保存，请等待。。。'}).show();
        		return true;
        	}
            return false;
        },
        success: function(responseText) {
    		mask.hide();
            if (MyTool.parseResponse(responseText, 
            		{successMsg:'保存成功。',failureMsg:'保存失败。'})) 
            {
                $('#dlg-org').dialog('close');
                $('#orgTree').tree('reload');
            }
        }
    });
}
// 删除
function deleteOrg() {
    var node = $('#orgTree').tree('getSelected');
    if (node) {
        $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
            if (r) {
            	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                $.post(
                	getUrl('/system/org/delete'),
               		{id:node.id},
               		function(result) {
               			mask.hide();
               			if (MyTool.parseResponse(result)) {
                   			$('#orgTree').tree('reload');
               			}
                	},
               		'json'
               	);
            }
        });
        return;
    }
    $.messager.alert('提示','请选择要删除的行！');
}
// 查看详情
function detailOrg() {
    var node = $('#orgTree').tree('getSelected');
    if (node) {
    	var val = '';
        for (var prop in node) {
    		val = node[prop];
        	if (prop == 'orgType') {
        		if (val == 1) {
        			val = '公司';
        		} else if (val == 2) {
        			val = '部门';
        		} else {
        			val = '其他';
        		}
        	}
            $('#d-o-' + prop).html(val);
        }
        return;
    }
}

function tooggle(tag) {
	var btnText = $('#btn-text');
	var text = btnText.html();
	if (text == '收起') {
		btnText.html('展开');
    	$('#orgTree').tree('collapseAll');
	} else {
		btnText.html('收起');
        $('#orgTree').tree('expandAll');
	}
}

function expand() {
    var node = $('#orgTree').tree('getSelected');
    $('#orgTree').tree('expand',node.target);
}

function collapse() {
    var node = $('#orgTree').tree('getSelected');
    $('#orgTree').tree('collapse',node.target);
}

$(function(){
	$('#orgTree').tree({
		onClick: function(node) {
			detailOrg();
			$('#dg').datagrid({
				url:getUrl('/system/user/query'), 
				queryParams: {
					orgId: node.id
				}
			});
		}
	});
});
