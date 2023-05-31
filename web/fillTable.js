function fillTable(params) {

    $(function () {
        "use strict";

        $("#stdTable").jqGrid({
            url: params.url,
            type: "GET",
            datatype: "json",
            colNames: params.colNames,
            colModel: params.colModel,
            autowidth: true,
            expandToFit: true,
            shrinkToFit: true,
            forceFit: true,
            rowNum: 10,
            rowList: [5, 10, 30],
            sortname: 1,
            pager: '#pager',
            viewrecords: true,
            loadonce: true,
            sortorder: "asc",
            search:true,
            stringResult: true,
            searchOperators: true,
            toolbar: ['search'],
            onSelectRow: onRowSelect,
            beforeSelectRow: onRowUnselect,
            caption: params.caption,
            postData: {
                'tableId': params.tableId
            },
            ajaxGridOptions: {
                beforeSend: function(xhr) {
                    xhr.setRequestHeader('tableId', params.tableId);
                }
            }

        })
        $("#stdTable").jqGrid('navGrid', '#pager', { edit: false, add: false, del: false });
        $("#stdTable").jqGrid("filterToolbar", {
             autosearch: true,
             searchOnEnter: false,
             defaultSearch: "cn",
             sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'bw', 'bn', 'in', 'ni', 'ew', 'en'], 
            });
          
    });
}