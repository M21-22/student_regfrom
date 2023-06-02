function onRowSelect(rowId){}
function onRowUnselect() {}
function fillField(key, value){
    var type = $('.infoBox #' + key).prop('type')
    if(type=='text' || type== 'email' || type=='number'){
        $('.infoBox #' + key).val(value)
    } else if(type =='date'){
        var dtINput = document.getElementById(key);
        var date = value.split(".")
        var formattedDate = `${date[2]}-${date[1]}-${date[0]}`
        dtINput.value = formattedDate
    } else if(type =='select-one'){
        let opt = document.querySelector('#'+key+' option[txt="'+value+'"]');
        opt.setAttribute('selected',"selected")

    } else if(key=="GENDER_ID"){
        var radioButtons = document.querySelectorAll('input[name="gender"]');
        for (var i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].value === String(value)) {
                radioButtons[i].checked = true;
                break;
            }
        }
    } else if(key=="IMAGE"){
        var decImg = atob(value.split(',')[1])
        $('#IMAGE').src = "data:image/jpeg;base64," + btoa(decImg)
    }
}

$('.act').on('click', function () {

    var rowId = $("#stdTable").jqGrid('getGridParam', 'selrow');
    var rowData = $("#stdTable").jqGrid('getRowData', rowId);
    dataId = rowData.ID
    actionButton = $(this).data('source')
    $('.warningBox').attr('hidden','hidden')

    if (rowId != null) {
        if (actionButton == 2) {    //model
            $.each(rowData, function (key, value) {
                fillField(key, value)
            })
        }
        if (actionButton == 3) {    //model
            if(confirm("Confirm deletion")){
                if(tableId == 1){
                    studentRequest({"ID":dataId}, actionButton, "")
                } else if(tableId==2){
                    courseRequest({"ID":dataId}, actionButton)
                }
                setTimeout(function () {
                    $('.btn.selected').trigger('click')
                }, 500)
            } 
        }
        if (actionButton == 4) { //model
            if(confirm("Confirm graduation")){
                studentRequest({"ID":dataId}, actionButton, "")
                setTimeout(function () {
                    $('.btn.selected').trigger('click')
                }, 500)
            }
        }
    }
})

$('.modal').on('show.bs.modal', function (e) {
    var button = e.relatedTarget;
    var selectedRows = $('#stdTable').jqGrid('getGridParam', 'selrow');
    if (button.id == "actionEdit" && selectedRows == null) {
        e.preventDefault();
    }
});

$('.btn').on("click", function () {

    tableId = $(this).attr('tableId')

    if (tableId == 3 || tableId == 0) {
        $.each($('.act'), function (key, value) {
            value.setAttribute('hidden', "hidden")
        })
    } else {
        $.each($('.act'), function (key, value) {
            value.removeAttribute('hidden')
        })
    }

    if (tableId != 1) {
        $('#actionGraduate')[0].setAttribute('hidden', "hidden")
    } else {
        $('#actionGraduate')[0].removeAttribute('hidden')
    }

    $('.btn').removeClass('selected');
    $(this).addClass('selected');
})
