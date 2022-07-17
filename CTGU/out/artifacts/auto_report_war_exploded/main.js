$(document).ready(function () {
    let click = $("#Button").click(function () {
        let username = $("#User").val();
        let password = $('#Pwd').val();
        let email = $('#Email').val();
        // if(username == "" || password == "" || email == ""){
        //     alert("请输入信息");
        // }
        // else {
        $.ajax({
            url: "servlet",
            type: "POST",
            timeout: 5000,
            data: {
                "username": username,
                "password": password,
                "email": email
            },
            dataType: "text",
            success: function (data) {
                // alert("请求成功");
            },
            error: function () {
                // alert("请求失败");
            },
        })
        // }
    });
});


