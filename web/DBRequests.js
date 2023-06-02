function studentRequest(dataLine, action, imageEncoded){
    var formData = new FormData();
    formData.append('image',imageEncoded)
    formData.append('dataLine', JSON.stringify(dataLine))
    formData.append('action', action)
    $.ajax(
        {
            url: "/students",
            type: "POST",
            data:formData,
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            success: function (response) {
                console.log(response.err)
                // console.log(dataLine)
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
function courseRequest(dataLine , action){
    let formData = new FormData();
    formData.append('dataLine', JSON.stringify(dataLine))
    formData.append('action', action)
    $.ajax(
        {
            url: "/courses",
            type: "GET",
            datatype: 'json',
            data: {
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