$(document).ready(function(){ 
    var id = $("#hidMemId").val();
        if(id!=''){
            setMemberInputReadOnly(true);
        }
    }
);
/**
 * 保存会员
 */
function saveMember(){
    enableValidate(true);

    if(!$("#memberForm").form("validate")){
        return;
    }
    var url;
    var memId = $("#hidMemId").val();
    if(memId==''){
        url = "../add";
    }else{
        url = "../update";
    }
    var balance = $.trim($("#detail_balance").val());
    if(balance!=''&&balance!=null){
        balance = balance.replace('¥','').replace(/,/g,'');
    }
    var params ={
        id:memId,
        name:$.trim($("#detail_name").val()),
        mobile:$.trim($("#detail_mobile").val()),
        gender:$("#detail_gender").combobox("getValue"),
        email:$.trim($("#detail_email").val()),
        id_card:$.trim($("#detail_idcard").val()),
        balance:balance
    };
    
    doAjaxServiceRequest(url,params,function(data){
        if (MyTool.parseResponse(data,
            {successMsg:data.msg,failureMsg:data.msg})){
            if(memId==''){
                memId = data.entity.id;
                $("#hidMemId").val(memId);
            }
            setMemberInputReadOnly(true);
        }
    });
}

// 取消编辑状态
function cancelEditMember(){
    setMemberInputReadOnly(true);
}

/**
 * 设置输入项为只读
 * @param readOnly
 */
function setMemberInputReadOnly(readOnly){
    if(readOnly){
        var gender = $("#detail_gender").combobox("getValue");
        if(gender == '1'){
            gender='女';
        }else{
            gender='男';
        }
        $("#lbl_name").text( $.trim($("#detail_name").val()));
        $("#lbl_mobile").text( $.trim($("#detail_mobile").val()));
        $("#lbl_gender").text(gender);
        $("#lbl_email").text( $.trim($("#detail_email").val()));
        $("#lbl_idcard").text( $.trim($("#detail_idcard").val()));
        $("#lbl_balance").text( $.trim($("#detail_balance").val()));
        $("#editArea").hide();
        $("#lblArea").show();
    }else{
        $("#editArea").show();
        $("#lblArea").hide();
    }
    
}
