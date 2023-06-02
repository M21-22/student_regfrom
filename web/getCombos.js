$(document).ready(function () {
    getLecturers()
    getCourses()
});

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
                    let selectElement = document.getElementById("COURSE");
                    let newOption = document.createElement("option");
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