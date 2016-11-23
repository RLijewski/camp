$( document ).ready(function() {
    $(".dropdown-button").dropdown({
        hover: true
    });

    $("#logoutButton").click(function(){
        $("#logoutForm").submit();
    });

    // $("#logoutForm").submit(function(){
    //     $.post("/logout", $("#logoutForm").serialize());
    // });
    //
    // $("logoutButton").click(function(e) {
    //     $("#logoutForm").submit(); // calls the submit handler
    //     e.preventDefault();  // Prevents the default behavior of the link
    // });
});