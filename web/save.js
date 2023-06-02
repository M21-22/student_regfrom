
$('#IMAGE')[0].addEventListener("change",(event)=>{
    const selectedFile = event.target.files;
    if (selectedFile.length > 0) {
        const [imageFile] = selectedFile;
        const fileReader = new FileReader();
        fileReader.onload = () => {
            const srcData = fileReader.result;
            imageEncoded = srcData;
        };
        fileReader.readAsDataURL(imageFile);
    }
})

$('.saveButton').on('click', function () {

    var modalType = $(this).closest('.modal')[0].id;
    var dataLine
    if (modalType == 'stdModal') {
        dataLine = {
            "ID":dataId,
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
        studentRequest( dataLine, actionButton, imageEncoded)
    }
    if (modalType == 'crsModal') {
        dataLine = {
            "ID":dataId,
            "CRSNAME": $('#CRSNAME').val(),
            "CRSCODE": $('#CRSCODE').val(),
            "LECTURER": $('#LECTURER').val(),
        }
        courseRequest(dataLine, actionButton)
    }
    setTimeout(function () {
        $('.btn.selected').trigger('click')
    }, 500)
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