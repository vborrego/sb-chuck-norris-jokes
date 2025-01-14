$(document).ready(function () {
    console.log("App ready");
});

$(".get-joke-button").on("click", function (event) {
    //console.log("Get joke button clicked");

    $.get("chucknorris")
        .done(function (data) {
            //console.log(data.response);
            $(".joke").text(data.response);
        });
});