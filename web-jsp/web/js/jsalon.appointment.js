$(function(){
    var $form = $('form');
    var $autocompletes = $('.autocomplete');
    var defWidth = $autocompletes.eq(0).width();
    var cache = {};

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
            var term = request.term;
            if (term in cache) {
                response(cache[term]);
                return;
            }
            $.post("/jsalon/user/autocomplete/ajax", request, function(data){
                cache[term] = data;
                response(data);
            });
            var icon = this.element.siblings('i');
            icon.removeClass();
            icon.addClass('fa fa-minus-square fa-lg err');
            this.element.siblings('input[type="hidden"]').val(false);
            this.element.width(defWidth - 20);

        },
        select: function(event, ui) {
            if (ui.item) {
                var icon = $(this).siblings('i');
                icon.removeClass();
                icon.addClass('fa fa-check-square fa-lg norm');
                $(this).siblings('input[type="hidden"]').val(true);
                $(this).width(defWidth - 20);
            }
        }
    });

});
