function onRowSelect(rowId){}
function onRowUnselect() {}
function fillField(key, value){
    var type = $('.infoBox #' + key).prop('type')
    if(type=='text' || type== 'email'){
        $('.infoBox #' + key).val(value)
    } else if(type =='date'){
        var dtINput = document.getElementById(key);
        var date = value.split(".")
        var formattedDate = `${date[2]}-${date[1]}-${date[0]}`
        dtINput.value = formattedDate
    } else if(type =='select-one'){
        // $('.infoBox #' + key).val(value)
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
    // console.log(key, value, type)

}
var sourceButton;

$('.std').on('click', function () {

    var rowId = $("#stdTable").jqGrid('getGridParam', 'selrow');
    var rowData = $("#stdTable").jqGrid('getRowData', rowId);

    // var model = $(this).attr('modelId')
    sourceButton = $(this).data('source')
    $('.warningBox').attr('hidden','hidden')

    if (sourceButton == 1) {

    }
    if (rowId != null) {
        if (sourceButton == 2) {    //model
            $.each(rowData, function (key, value) {
                fillField(key, value)
            })
        }
        if (sourceButton == 3) {    //model
            if(confirm("Confirm deletion")){
                var dataLine = {"ID":rowData.ID}
                var tableId = $('#stdTable').jqGrid('getGridParam','postData').tableId
                if(tableId == 1){
                    studentCall(tableId, dataLine, sourceButton, "")
                } else if(tableId==2){
                    courseCall(tableId, dataLine, sourceButton)
                }
                setTimeout(function () {
                    $("#stdTable").jqGrid('GridUnload')
                    fillTable(params1)
                }, 500)
            } 
        }
        if (sourceButton == 4) { //model
            if(confirm("Confirm graduation")){
                var dataLine = {"ID":rowData.ID}
                studentCall(3, dataLine, sourceButton, "")
            }

        }
    }
})


$('.prt').on('click', function () {
    var gridData = $("#stdTable").jqGrid('getGridParam', 'data');
})

$('.modal').on('show.bs.modal', function (e) {

    var button = e.relatedTarget;
    var selectedRows = $('#stdTable').jqGrid('getGridParam', 'selrow');
    if (button.id == "editstd" && selectedRows == null) {
        e.preventDefault();
    }
    // if (button.getAttribute('modelid') == 2 && selectedRows == null) {
    //     e.preventDefault();
    // }
});

$('.btn').on("click", function () {

    var tableId = $(this).attr('tableId')

    if (tableId == 3 || tableId == 0) {
        $.each($('.std'), function (key, value) {
            value.setAttribute('hidden', "hidden")
        })
    } else {
        $.each($('.std'), function (key, value) {
            value.removeAttribute('hidden')
        })
    }

    if (tableId != 1) {
        $('#gradstd')[0].setAttribute('hidden', "hidden")
    } else {
        $('#gradstd')[0].removeAttribute('hidden')
    }

    $('.btn').removeClass('selected');
    $(this).addClass('selected');

})

$(document).ready(function () {
    // $("#topdf").on("click", function () {
    //     let page = $('.ui-widget-content.ui-pg-input').val()
    //     let rowsPerPage = $('.ui-widget-content.ui-pg-selbox').val()
        // console.log(page , rowsPerPage)
    // })
    getLecturers()
    getCourses()

});
function studentCall(tableId, dataLine ,action , imageEncoded ){
    var formData = new FormData();
    formData.append('image',imageEncoded)
    formData.append('dataLine', JSON.stringify(dataLine))
    formData.append('tableId', tableId)
    formData.append('action', action)
    $.ajax(
        {
            url: "/students",
            type: "POST",
            // datatype: 'json',
            data:formData,
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            success: function (response) {
                console.log(response.err)
                if(response.pass == true){
                    $('.generalWarning').attr('hidden','hidden')
                    $('#cancelSt').trigger('click')
                } else {
                    $('.generalWarning').removeAttr('hidden')
                }
            },
            error: function (error) {
                console.log(error);
            }
        })
}
function courseCall(tableId, dataLine , action){
    $.ajax(
        {
            url: "/courses",
            type: "GET",
            datatype: 'json',
            data: {
                'tableId': tableId,
                'dataLine': JSON.stringify(dataLine),
                'action' : action
            },
            success: function (response) {
                console.log(response)
                // if(response.pass == true){
                //     $('.generalWarning').attr('hidden','hidden')
                //     $('#cancelCRS').trigger('click')
                // } else {
                //     $('.generalWarning').removeAttr('hidden')
                // }
            },
            error: function (error) {
                console.log(error);
            }
        })
}

function getCourses() {
    $.ajax(
        {
            url: "/combo",
            type: "GET",
            datatype: 'json',
            data: {
                'tableId': 2,
            },
            success: function (data) {
                // console.log(data)
                $.each(data, function(key, value){
                    var selectElement = document.getElementById("COURSE");
                    var newOption = document.createElement("option");
                    newOption.value = value.ID;
                    newOption.text = value.CRSNAME;
                    newOption.setAttribute('txt',value.CRSNAME)
                    selectElement.appendChild(newOption);
                })
            },
            error: function (error) {
                console.log(error);
            }
        })
}

function getLecturers() {
    $.ajax(
        {
            url: "/combo",
            type: "GET",
            datatype: 'json',
            data: {
                'tableId': 0,
            },
            success: function (data) {
                // console.log(data)
                $.each(data, function(key, value){
                    var selectElement = document.getElementById("LECTURER");
                    var newOption = document.createElement("option");
                    newOption.value = value.ID;
                    newOption.text = value.NAME;
                    newOption.setAttribute('txt',value.NAME)
                    selectElement.appendChild(newOption);
                })
            },
            error: function (error) {
                console.log(error);
            }
        })
}