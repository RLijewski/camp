$( document ).ready(function() {
    $(".dropdown-button").dropdown({
        hover: false
    });

    $("#logoutButton").click(function(){
        $("#logoutForm").submit();
    });

    $("#close-flash").on("click", function () {
        $(".flash-fragment").remove();
    });

    $("#logoutButton").on("click", function () {
        $("#logoutForm").submit();
    });

    $(".deleteButton").on("click", function (e) {
        $(this).prev().submit();
    });

    $(".editButton").on("click", function (e) {
        $(this).prev().submit();
    });

    $('select').material_select();
});