$(function(){
    var $form = $('form'),
        $autocompletes = $('.autocomplete'),
        $actMessage = $('#actionMessage'),
        $submit = $form.find('input[type="submit"]');
    var defWidth = $autocompletes.eq(0).width();
    //var cache = {};

    $('#send-btn').button();

    $('#dateAppoint').datepicker({
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true,
        dateFormat: $.datepicker.ISO_8601
    });

    $.widget("ui.timespinner", $.ui.spinner, {
        options: {
            step: 60 * 1000, // seconds
            page: 60  // hours
        },
        _parse: function(value){
            if (typeof value === "string") {
                // already a timestamp
                if (Number(value) == value) {
                    return Number(value);
                }
                return +Globalize.parseDate(value);
            }
            return value;
        },
        _format: function(value){
            return Globalize.format(new Date(value), "t");
        }
    });

    Globalize.culture("de-DE");
    $('#timeAppoint').timespinner();

    $autocompletes.autocomplete({
        minLength: 2,
        source: function(request, response) {
            var currElem = this.element[0];
            request.id = currElem.id;
            extendObjectFromFormParams(request, $form);
            //var term = request.term;
            //if (term in cache) {
            //    response(cache[term]);
            //    return;
            //}
            $.post("/jsalon/user/autocomplete/ajax", request, function(data){
                //cache[term] = data;
                response(data);
            });
            var icon = this.element.siblings('i');
            icon.removeClass();
            icon.addClass('fa fa-minus-square fa-lg err');
            this.element.siblings('input[type="hidden"]').val('');
            this.element.width(defWidth - 20);

        },
        select: function(event, ui) {
            if (ui.item) {
                var icon = $(this).siblings('i');
                icon.removeClass();
                icon.addClass('fa fa-check-square fa-lg norm');
                $(this).siblings('input[type="hidden"]').val(ui.item.id);
                $(this).width(defWidth - 20);
            }
        }
    });

    $form.on('submit', function(event){
        event.preventDefault();
        $actMessage.empty();
        $actMessage.removeClass('err norm warn');
        $submit.prop('disabled', true);
        $.ajax({
            url: '/jsalon/user/appointment/ajax',
            type: "POST",
            data: $form.serialize(),
            success: function(data) {
                if (data && data.err) {
                    data.err.forEach(function(item){
                        $actMessage.append(item + '<br/>');
                    });
                    $actMessage.addClass('err');
                }
                if (data && data.message && ('code' in data)) {
                    resetForm();
                    $actMessage.html(data.message);
                    switch (data.code) {
                        case -1: $actMessage.addClass('err');  break;
                        case 0:  $actMessage.addClass('norm'); break;
                        case 1:  $actMessage.addClass('warn'); break;
                    }
                }
            },
            error: function(jqXHR, status, thrown) {
                $actMessage.html('' + status + ': ' + thrown);
                $actMessage.addClass('err');
            },
            complete: function(jqXHR, status) {
                $submit.prop('disabled', false);
            }
        });
    });

    function resetForm() {
        var $icons = $('.form-field i');
        $form[0].reset();
        $icons.removeClass();
        $icons.prev('input').width(defWidth);
        $('.form-field input[type="hidden"]').val('');
    }
});
