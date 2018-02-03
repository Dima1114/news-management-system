var baseUrl = $('#baseUrl').attr('content');

function addComment(articleId) {

    var user = $('#username').val();
    var commentText = $('#text').val();

    $.ajax({
        url: baseUrl + 'comment/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            articleId: articleId,
            username: user,
            text: commentText
        }),
        success: function (data) {
            appendCommentErrors(data);
        }
    });
}

function appendCommentErrors(data) {

    clearCommentErrors();
    //add errors if they preset or reload page
    if (data.length === 0) {
        window.location.reload();
    } else {
        $.each(data, function (k, v) {
            var field = $('#' + k);
            field.parent().addClass('has-danger');
            field.addClass('form-control-danger');
            var label = $('#for-' + k);
            label.text(v);
        });
    }
}

function clearCommentErrors() {
    //remove all errors before update
    $('.form-control-feedback').each(function () {
        $(this).empty();
    });
    $('.form-group').each(function () {
        $(this).removeClass('has-danger');
        $(this).find('input').removeClass('form-control-danger');
    });
}
