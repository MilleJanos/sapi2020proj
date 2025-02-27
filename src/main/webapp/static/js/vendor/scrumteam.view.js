$(function() {

    $(".well").on("click", "#delete-story-link", function(e) {
        e.preventDefault();

        var storyDeleteDialogTempate = Handlebars.compile($("#template-delete-scumteam-confirmation-dialog").html());

        $("#view-holder").append(storyDeleteDialogTempate());
        $("#delete-scumteam-confirmation-dialog").modal();
    })

    $("#view-holder").on("click", "#cancel-story-button", function(e) {
        e.preventDefault();

        var deleteConfirmationDialog = $("#delete-scumteam-confirmation-dialog")
        deleteConfirmationDialog.modal('hide');
        deleteConfirmationDialog.remove();
    });

    $("#view-holder").on("click", "#delete-story-button", function(e) {
        e.preventDefault();
        window.location.href = "/story/delete/" + $("#story-id").text();
    });
});
