var imageEncoded
$('#IMAGE')[0].addEventListener("change",(event)=>{
    const selectedfile = event.target.files;
    if (selectedfile.length > 0) {
        const [imageFile] = selectedfile;
        const fileReader = new FileReader();
        fileReader.onload = () => {
            const srcData = fileReader.result;
            // console.log('base64:', srcData)
            imageEncoded = srcData;
        };
        fileReader.readAsDataURL(imageFile);
    }
})

$('.saveButton').on('click', function () {

    var rowId = $("#stdTable").jqGrid('getGridParam', 'selrow');
    var rowData = $("#stdTable").jqGrid('getRowData', rowId);

    var modalType = $(this).closest('.modal')[0].id;
    var tableId = null
    if(modalType == 'stdModal') tableId = 1
    if(modalType == 'crsModal') tableId = 2
    var dataLine
    if (modalType == 'stdModal') {
        dataLine = {
            "ID":rowData.ID,
            "NAME": $('#NAME').val(),
            "SURNAME": $('#SURNAME').val(),
            "COURSE": $('#COURSE').val(),
            "BIRTH_DATE": $('#BIRTH_DATE').val(),
            "GENDER_ID": document.querySelector('input[name="gender"]:checked').value,
            "EMAIL": $('#EMAIL').val(),
            "MOBILE": $('#MOBILE').val(),
            "SCHOOL": $('#SCHOOL').val(),
            "ENTRANCE_SCORE": $('#ENTRANCE_SCORE').val(),
            "COMPLETION_DATE": $('#COMPLETION_DATE').val(),
            // "IMAGE": document.getElementById('IMAGE').files[0],
        }
        var fileInput = $('#IMAGE')[0].files[0];
        var reader = new FileReader();

        // reader.onload = function (event) {
        //     const base64Image = event.target.result;
        //     var imageData = {'base64Image': base64Image}

        // }
        // reader.readAsDataURL(fileInput);
        studentCall(tableId, dataLine, sourceButton, imageEncoded)

        setTimeout(function () {
            $("#stdTable").jqGrid('GridUnload')
            fillTable(params1)
        }, 500)
    }
    if (modalType == 'crsModal') {
        dataLine = {
            "ID":rowData.ID,
            "CRSNAME": $('#CRSNAME').val(),
            "CRSCODE": $('#CRSCODE').val(),
            "LECTURER": $('#LECTURER').val(),
        }
        courseCall(tableId, dataLine,sourceButton)
        setTimeout(function () {
            $("#stdTable").jqGrid('GridUnload')
            fillTable(params2)
        }, 500)
        // getLecturers()
        // getCourses()
    }
})

$('.modal').on("hidden.bs.modal", function() {
        let temp = $(' .infoBox input')
        $.each(temp, function (key, value) {
            let fld = $('.infoBox #' + value.id)[0]
            if(fld.type == 'radio'){
                fld.checked = false
            } else {
                $('.infoBox #' + value.id).val('')
            }
        })

        let selOpt = document.querySelector('.infoBox option[selected="selected"]')
        if(selOpt!=null) selOpt.removeAttribute('selected')
});