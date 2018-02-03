var baseUrl = $('#baseUrl').attr('content');

function updateArticle() {

    var articleId = $('#id').val();
    var articleTitle = $('#title').val();
    var articleText = $('#text').val();
    var articleDate = $('#date').val();

    $.ajax({
        url: baseUrl + 'article/update',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            id: articleId,
            title: articleTitle,
            text: articleText,
            date: articleDate
        }),
        success: function (data) {
            appendArticleErrors(data);
        }
    });
}

function removeArticle(articleId) {

    $.ajax({
        url: baseUrl + 'article/delete/' + articleId,
        type: 'POST',
        contentType: 'application/json',
        success: function () {
            window.location.href = baseUrl;
        }
    });
}

function appendArticleErrors(data) {

    clearArticleErrors();
    //add errors if they preset or redirect to article page
    if ($.isNumeric(data)) {
        window.location.href = baseUrl + 'article/' + data;
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

function clearArticleErrors() {
    //remove all errors before update
    $('.form-control-feedback').each(function () {
        $(this).empty();
    });
    $('.form-group').each(function () {
        $(this).removeClass('has-danger');
        $(this).find('input').removeClass('form-control-danger');
    });
}
