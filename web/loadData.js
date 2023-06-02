var params1 = {
    'url': "/data",
    'caption': "Student List",
    'colNames': ["ID","Name", "Surname", "Course", "Email","Mobile","Birth date", "Gender","School", "Entrance score","Completion date","Image"],
    'colModel': [
        { name:"ID", index: "ID", hidden:true},
        { name: "NAME", index: "NAME", width: "80px" },
        { name: "SURNAME", index: "SURNAME", width: "80px" },
        { name: "COURSE", index: "COURSE", width: "100px" },
        { name: "EMAIL", index: "EMAIL", width: "150px" },
        { name: 'MOBILE', index: "MOBILE", width: "120px" },
        { name: 'BIRTH_DATE', index: "BIRTH_DATE", hidden:true, formatter:"date", formatoptions: { srcformat: "Y-m-d", newformat: "d.m.Y" } },
        { name: 'GENDER_ID', index: "GENDER_ID", hidden:true },
        { name: 'SCHOOL', index: "SCHOOL",hidden:true },
        { name: 'ENTRANCE_SCORE', index: "ENTRANCE_SCORE", hidden:true },
        { name: 'COMPLETION_DATE', index: "COMPLETION_DATE", hidden:true, formatter:"date", formatoptions: { srcformat: "Y-m-d", newformat: "d.m.Y" }},
        { name: 'IMAGE', index: "IMAGE", hidden:true },
    ],
    'tableId': 1
}

var params2 = {
    'url': "/data",
    'caption': "Course List",
    'colNames': ["ID","Course name", "Course code", "Lecturer", "Students"],
    'colModel': [
        { name:"ID", index: "ID", hidden:true},
        { name: "CRSNAME", index: "CRSNAME", width: "100px" },
        { name: "CRSCODE", index: "CRSCODE", width: "60px" },
        { name: "LECTURER", index: "LECTURER", width: "100px" },
        { name: "STUDENTS", index: "STUDENTS", width: "80px" },
    ],
    'tableId': 2
}

$('.btn').on("click", async function () {

    tableId = $(this).attr('tableId')
    $("#stdTable").jqGrid('GridUnload')

    if (tableId == 1) {
        $('.act').attr('data-bs-target', '#stdModal')
        await new Promise(function (resolve) {
            resolve(fillTable(params1))
        })
    }
    if (tableId == 2) {
        $('.act').attr('data-bs-target', '#crsModal')
        await new Promise(function (resolve) {
            resolve(fillTable(params2))
        })
    }
    if (tableId == 3) {
        $('.act').attr('data-bs-target', '#stdModal')
        await new Promise(function (resolve) {
            let params3 = {...params1}
            params3.caption = 'Graduate List'
            params3.tableId = 3
            resolve(fillTable(params3))
        })
    } 
})

$(document).on('click', '#stdTable', function () {
    var rowId = $("#stdTable").jqGrid('getGridParam', 'selrow');
    var rowData = $("#stdTable").jqGrid('getRowData', rowId);
    // var title = $('.ui-jqgrid-title').text()
    if (rowId == null || tableId==2 ) {
        $('.infoColumn')[0].setAttribute('hidden', "hidden")
    } else {
        var decodedImage = atob(rowData.IMAGE.split(',')[1])
        $('.infoColumn .imageBox img')[0].src = "data:image/jpeg;base64," + btoa(decodedImage)
        $('.infoColumn .dataBox').html("")
        var arr = []

        $.each(params1.colModel, function (key, value){
            if(value.hidden == true && value.name != 'ID' && value.name != 'IMAGE'){
                arr.push(1)
            } else arr.push(0)
        })
        var i=0;
        $.each(rowData, function (key, value) {
            if(arr[i]==1){
                $('.infoColumn .dataBox').append('<span>' + key + ': ' + value + '</span>')
            }
            i++
        })
        $('.infoColumn')[0].removeAttribute('hidden')
    }
})

$(document).ready(function () {
    $('#stdbtn').trigger('click')
})