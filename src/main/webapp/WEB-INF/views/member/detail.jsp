<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ecplatform/css/member/detail.css" />
<jsp:include page="../common/common.jsp"></jsp:include>
<title>会员详情</title>

</head>
<body>
  <table id="dataList"></table>

  <div id="tb" style="padding: 5px; height: auto">
    <div>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddPet()">新增宠物</a> <a href="javascript:void(0)" class="easyui-linkbutton"
        iconCls="icon-edit" plain="true" onclick="setMemberInputReadOnly(false)">编辑会员</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
        onclick="showAddSubMember()">新增子账号</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="resetSearch();">重置</a>

    </div>
    <div style="margin-top: 2px; margin-left: 15px;" buttons="#dlg-buttons">
      <div id="memberDiv" class="memberClass">
        <div class="member-area-title">会员信息：</div>
        <form action="" id="memberForm" >
        <div id="editArea" style="overflow: auto; margin-top: 10px;">
          <dl id="nameDl" class="">
            <dt class="member-item-title">
              <span class="mark-important">*</span> <label>姓名：</label>
            </dt>
            <dd>
              <input class="easyui-validatebox" style="width: 80px;" maxlength="10" data-options="required:true,validType:['length[0,10]']" id="detail_name" name="detail_name" />
            </dd>
          </dl>
          <dl id="genderDl" class="">
            <dt class="member-item-title">
              <span class="mark-important">*</span> <label>性别：</label>
            </dt>
            <dd>
              <select class="easyui-combobox" style="width: 84px;" data-options="editable:false" name="detail_gender" id="detail_gender">
                <option value="1" selected="selected">女</option>
                <option value="0">男</option>
              </select>
            </dd>
          </dl>
          <dl id="mobileDl" class="">
            <dt class="member-item-title">
              <span class="mark-important">*</span> <label>手机：</label>
            </dt>
            <dd>
              <input class="easyui-numberbox" maxlength="15" data-options="required:true,precision:0" id="detail_mobile" name="detail_mobile" />
            </dd>
          </dl>
          <dl id="emailDl" class="">
            <dt class="member-item-title">
              <label>邮箱：</label>
            </dt>
            <dd>
              <input class="easyui-validatebox" maxlength="40" data-options="validType:['email']" style="width: 180px;" id="detail_email" name="detail_email" />
            </dd>
          </dl>
          <dl id="idcardDl" class="">
            <dt class="member-item-title">
              <label>身份证：</label>
            </dt>
            <dd>
              <input class="easyui-validatebox" maxlength="20" style="width: 180px;" id="detail_idcard" name="detail_idcard" />
            </dd>
          </dl>
          <dl id="balanceDl" class="">
            <dt class="member-item-title">
              <label>充值：</label>
            </dt>
            <dd>
              <input class="easyui-numberbox" data-options="required:true,value:0,min:0,max:9999999999.99,precision:2,prefix:'¥',groupSeparator:','" id="detail_balance" name="detail_balance" />
            </dd>
          </dl>
          <div id="save_buttons">
            <a href="javascript:void(0)" id="saveMemberBtn" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMember()">保存</a> <a href="javascript:void(0)"
              id="cancelMemberBtn" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelEditMember()">取消</a>
          </div>
        </div>
        </form>
        <div id="lblArea" style="overflow: auto; margin-top: 10px; display: none;">

          <dl id="lblNameDl" class="">
            <dt class="member-item-title">
              <span class="mark-important">*</span> <label>姓名：</label>
            </dt>
            <dd>
              <div class="member-item-lbl" id="lbl_name"></div>
            </dd>
          </dl>
          <dl id="lblGenderDl" class="">
            <dt class="member-item-title">
              <span class="mark-important">*</span> <label>性别：</label>
            </dt>
            <dd>
              <div class="member-item-lbl" id="lbl_gender"></div>
            </dd>
          </dl>
          <dl id="lblMobileDl" class="">
            <dt class="member-item-title">
              <span class="mark-important">*</span> <label>手机：</label>
            </dt>
            <dd>
              <div class="member-item-lbl" id="lbl_mobile"></div>
            </dd>
          </dl>
          <dl id="lblEmailDl" class="">
            <dt class="member-item-title">
              <label>邮箱：</label>
            </dt>
            <dd>
              <div class="member-item-lbl" id="lbl_email"></div>
            </dd>
          </dl>
          <dl id="lblIdcardDl" class="">
            <dt class="member-item-title">
              <label>身份证：</label>
            </dt>
            <dd>
              <div class="member-item-lbl" id="lbl_idcard"></div>
            </dd>
          </dl>
          <dl id="lblBalanceDl" class="">
            <dt class="member-item-title">
              <label>余额：</label>
            </dt>
            <dd>
              <div class="member-item-lbl" id="lbl_balance"></div>
            </dd>
          </dl>
          <div id="edit_buttons">
            <a href="javascript:void(0)" id="editMemberBtn" class="easyui-linkbutton" iconCls="icon-ok" onclick="setMemberInputReadOnly(false)">编辑会员信息</a>
          </div>
          <input type="hidden" id="hidMemId" value="" />
        </div>
        <div id="subMemberDiv" class="subMemberClass">
          <div class="member-area-title">子账号信息：</div>
          <div id="subMemberArea" style="overflow: auto; margin-top: 10px;">
            <div></div>
          </div>
        </div>
      </div>

      <div id="petDiv" class="petClass">
        <div class="member-area-title">宠物信息：</div>
        <div id="petArea" style="overflow: auto; margin-top: 10px;">
          <div></div>
        </div>
      </div>
    </div>
  </div>



  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/member/detail.js"></script>
  <script type="text/javascript">
      var memId = "${member.id }";
      if(memId != ''){
          $("#hidMemId").val("${member.id }");
          $("#detail_name").val("${member.name }");
          $("#detail_mobile").val("${member.mobile }");
          $("#detail_email").val("${member.email }");
          $("#detail_idcard").val("${member.id_card }");
          $("#detail_balance").val("${member.balance }");
      }
  </script>
  <!-- 控制代码，可抽取到js文件中 -->
  <script type="text/javascript">
            var url;
            // 新增
            function addFunction1() {
                /* var row = $('#dg').datagrid('getSelected');
                $('#fm').form('clear');
                $('#dlg').dialog('open').dialog('setTitle','新增');
                $('#sysRegDate').datebox('setValue', nowDate());
                var parentId = 0;
                if (row) {
                	parentId = row.id;
                }
                $('#parentId').numberbox('setValue', parentId); */
                url = '${pageContext.request.contextPath}/system/function/save';
                showDialog('dd1', '添加会员', 400, 300, url, true);
            }
            // 修改
            function updateFunction() {
                var row = $('#dg').datagrid('getSelected');
                if (row) {
                    $('#dlg').dialog('open').dialog('setTitle', '修改');
                    $('#fm').form('load', row);
                    url = '${pageContext.request.contextPath}/system/function/update?id=' + row.id;
                    return;
                }
                $.messager.alert('提示', '请选择要修改的行！');
            }
            // 提交保存
            function saveFunction() {
                $('#fm').form('submit', {
                    url : url,
                    onSubmit : function() {
                        return $(this).form('validate');
                    },
                    success : function(result) {
                        var result = eval('(' + result + ')');
                        if (result.errorMsg) {
                            $.messager.show({
                                title : 'Error',
                                msg : result.errorMsg
                            });
                        } else {
                            $('#dlg').dialog('close');
                            $('#dg').datagrid('reload');
                        }
                    }
                });
            }
            // 删除
            function deleteFunction() {
                var row = $('#dg').datagrid('getSelected');
                if (row) {
                    $.messager.confirm('删除', '确定要删除所选中的数据行吗?', function(r) {
                        if (r) {
                            $.post('${pageContext.request.contextPath}/system/function/delete', {
                                id : row.id
                            }, function(result) {
                                if (result.success) {
                                    $('#dg').treegrid('reload');
                                } else {
                                    $.messager.show({
                                        title : '提示',
                                        msg : result.msg
                                    });
                                }
                            }, 'json');
                        }
                    });
                    return;
                }
                $.messager.alert('提示', '请选择要删除的行！');
            }
            // 查看详情
            function detailFunction() {
                var row = $('#dg').datagrid('getSelected');
                var objToString = function(obj) {
                    var s = "";
                    for ( var property in obj) {
                        s = s + "\n " + property + ": " + obj[property];
                    }
                    return s;
                };
                console.log(objToString(row));
                if (row) {
                    $('#detail-dlg').dialog('open').dialog('setTitle', '详情');
                    for ( var prop in row) {
                        $('#d-' + prop).html(row[prop]);
                    }
                    return;
                }
            }
        </script>
</body>
</html>