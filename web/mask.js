document.getElementById('MOBILE').addEventListener('input', function (e) {
    var isValid = /\+994(50|51|55|70|77)(\d{7})/.test($('#MOBILE').val())
    if(isValid){
        $('#mobileWarning').attr('hidden', 'hidden')
    } else {
        $('#mobileWarning').removeAttr('hidden')
    }
});

document.getElementById('EMAIL').addEventListener('input', function (e) {
    var isValid = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}/.test($('#EMAIL').val())
    if(isValid){
        $('#emailWarning').attr('hidden', 'hidden')
    } else {
        $('#emailWarning').removeAttr('hidden')
    }
});

document.getElementById('NAME').addEventListener('input', function (e) {
    var isValid = /\b([A-Za-z-']+\b)/.test($('#NAME').val())
    if(isValid){
        $('#nameWarning').attr('hidden', 'hidden')
    } else {
        $('#nameWarning').removeAttr('hidden')
    }
});

document.getElementById('SURNAME').addEventListener('input', function (e) {
    var isValid = /\b([A-Za-z-']+\b)/.test($('#SURNAME').val())
    if(isValid){
        $('#surnameWarning').attr('hidden', 'hidden')
    } else {
        $('#surnameWarning').removeAttr('hidden')
    }
});