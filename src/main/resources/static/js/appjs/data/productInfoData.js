var prefix = "/dd";
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
        {
            method: 'get', // 服务器数据的请求方式 get or post
            url: prefix + "/productDataList", // 服务器数据的加载地址
            // showRefresh : true,
            // showToggle : true,
            showColumns: true,
            iconSize: 'outline',
            toolbar: '#exampleToolbar',
            striped: true, // 设置为true会有隔行变色效果
            dataType: "json", // 服务器返回的数据类型
            pagination: true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect: false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageNumber: 1, // 如果设置了分布，首页页码
            pageSize: 10, // 如果设置了分页，每页数据条数
            pageList: [10, 20, 30, 40], //可供选择的每页的行数（*）
            // search : true, // 是否显示搜索框
            //showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"

            queryParams: function (params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    limit: params.limit,//页面大小
                    offset: params.offset,//页码
                    planId: $('#proFactory').val(),
                    id: $('#skuName').val(),
                    planTitle: $('#batchNumber').val()
                };
            },
            columns: [
                {
                    field: 'id',
                    title: '详细ID'
                },
                {
                    field: 'scode',
                    title: '搜索码'
                },
                {
                    field: 'proFactory',
                    title: '生产工厂'
                },
                {
                    field: 'skuName',
                    title: '品项名称'
                },
                {
                    field: 'batchNumber',
                    title: '批次码'
                },
                {
                    field: 'qurl',
                    title: '质检报告',
                    formatter : function(value, row, index) {
                        var qurl = row.qurl;
                        if (qurl != null) {
                            return '<a href="'
                                + qurl
                                + '" target="view_window" class="easyui-linkbutton" data-options="plain:true" title="在线预览")">'
                                + '在线预览'
                                + '</a>';
                        }
                        return '';
                    }
                },
                {
                    field: 'adderss',
                    title: '定位地址'
                },
                {
                    field: 'isCargo',
                    title: '是否串货'
                },
                {
                    field: 'kunnrName',
                    title: '经销商'
                },
                {
                    field: 'userId',
                    title: '查询人'
                }
            ]
        });
}

/**
 * 查询
 */
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
/**
 * 重置
 */
function clearData() {
    $('#proFactory').val(''),$('#skuName').val(''), $('#batchNumber').val('')
}

