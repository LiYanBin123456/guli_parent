/**
 * 定义字段集合、字段格式化和字段设置相关数据和函数
 *
 */

//----------------------------------------------------------------字段集合-----------------------
//客户管理字段集合
var columns_client = [[
    {field:'id', title: '编号',width:70},
    {field:'pname', title: '产业园名称',width:150},
    {field:'name', title: '客户名称',width:150},
    {field:'contact', title: '联系人',width:100},
    {field:'control', title: '实际控制人',width:100},
    {field:'phone', title: '联系电话',width:120},
    {field:'address', title: '地址',width:120},
    {fixed: 'right', title: '操作', toolbar: '#bar_cooperation',width:200}
]];

//所属渠道商的客户管理字段
var columns_client_distribute = [[
    {field:'id', title: '编号',width:70},
    {field:'did', title: '渠道商id',width:50},
    {field:'name', title: '名称',width:200},
    {field:'contact', title: '联系人',width:100},
    {field:'control', title: '实际控制人',width:100},
    {field:'code', title: '信用代码',width:100},
    {field:'phone', title: '联系电话',width:120},
    {field:'address', title: '地址',width:120},
    {fixed: 'right', title: '操作', toolbar: '#bar_client',width:100}
]];

//渠道商管理字段集合
var columns_distribute = [[
    {field:'id', title: '编号',width:70},
    {field:'name', title: '名称',width:200},
    {field:'contact', title: '联系人',width:100},
    {field:'code', title: '信用代码',width:100},
    {field:'phone', title: '联系电话',width:120},
    {field:'address', title: '地址',width:120},
    {fixed: 'right', title: '操作', toolbar: '#bar_cooperation',width:200}
]];

//产业园字段
var columns_park = [[
    {field:'id', title: '编号',width:70},
    {field:'name', title: '产业园名称',width:200},
    {field:'per1', title: '增值税比例比例',width:100},
    {field:'per2', title: '企业所得税比例',width:100},
    {field:'per3', title: '个人所得税比例',width:120},
    {field:'per4', title: '印花所得税比例',width:120},
    {field:'per5', title: '城建税比例',width:120},
    {fixed: 'right', title: '操作', toolbar: '#bar_cooperation',width:200}
]];

//客户结算单
var columns_settlement1 = [[
    {field:'id', title: '编号',width:60},
    {field:'pname', title: '产业园名称',width:120},
    {field:'cname', title: '客户名称',width:100},
    {field:'month', title: '月份',width:100,templet:function (d) {return dateUtil.format_month(d.month)}},
    {field:'base', title: '总额',width:100,edit:'text'},
    {field:'turnovers', title: '营业额',width:100,edit:'text'},
    {field:'tax1', title: '增值税',width:100,edit:'text'},
    {field:'tax2', title: '企业所得税',width:120,edit:'text'},
    {field:'tax3', title: '个人所得税',width:120,edit:'text'},
    {field:'tax4', title: '附加税',width:120,edit:'text'},
    {fixed: 'right', title: '操作', toolbar: '#bar_cooperation',width:200}
]];

//渠道商结算单
var columns_settlement2 = [[
    {field:'id', title: '编号',width:60},
    {field:'dname', title: '渠道商名称',width:100},
    {field:'month', title: '月份',width:100,templet:function (d) {return dateUtil.format_month(d.month)}},
    {field:'base', title: '总额',width:100,edit:'text'},
    {field:'turnovers', title: '营业额',width:100,edit:'text'},
    {field:'tax1', title: '增值税',width:100,edit:'text'},
    {field:'tax2', title: '企业所得税',width:120,edit:'text'},
    {field:'tax3', title: '个人所得税',width:120,edit:'text'},
    {field:'tax4', title: '附加税',width:120,edit:'text'},
    {fixed: 'right', title: '操作', toolbar: '#bar_cooperation',width:200}
]];

//合作单位潜在客户管理字段集合
var columns_cooperation_potential = [[
    {title: '序号',width:80,type:'numbers'},
    {field:'id', title: '编号',width:100},
    {field:'name', title: '客户名称',width:200},
    {field:'category', title: '客户性质',width:100,templet:function (d) { return array_value2text(categorys_cooperation,d.category) }},
    {field:'contact', title: '联系人',width:100},
    {field:'phone', title: '联系电话',width:120},
    {fixed: 'right', title: '操作', toolbar: '#bar_potential',width:300}
]];
//----------------------------------------------------------------数据集合-----------------------


//----------------------------------------------------------------字段格式化-----------------------


function format_percent(value) {
    return value+"%";
}

function format_number() {
    var d = new Date
    var num =d.parseInt(num,2);
    return  num;

}

function format_settlement_status(status) {
    var text = array_value2text(status_settlement,status);
    var html = "<label onmousemove='showStatus("+status+")'>"+text+"</label>";
    return html;
}
/**
 * 在数组中根据指定值转换为文本
 * @param {array} arr 数组
 * @param {*} value 指定值
 */
function array_value2text(arr,value){
    for(var i in arr){
        var obj = arr[i];
        if(obj.value == value){
            return obj.text;
        }
    }
    return "";
}


