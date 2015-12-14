$(document).ready(function() {

    var table = $('#datatable');
    var form = $('form');
    var hiddenForm = $('#hiddenForm');
    var actMessage = $('#actionMessage');
    var radios = $('input:radio[name="radio"]');

    var colDefs = [];
    var fields = [];
    var ajaxUrl;

    switch (getLastPathFromHref(window.location.pathname, true)) {
        case 'discounts':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1,2], className: "dt-col-center" } ];
            fields = getDiscountFields();
            ajaxUrl = "/jsalon/admin/discounts/ajax";
            break;
        case 'posts':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1], className: "dt-col-center" } ];
            fields = getPostFields();
            ajaxUrl = "/jsalon/admin/posts/ajax";
            break;
        case 'services':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1,2,3,4], className: "dt-col-center" } ];
            fields = getServiceFields();
            ajaxUrl = "/jsalon/admin/services/ajax";
            break;
        case 'user':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1,2,3,4,5], className: "dt-col-center" } ];
            fields = getUserFields();
            ajaxUrl = "/jsalon/admin/user/ajax";
            break;
    }

    var datatable = table.DataTable({
        processing: true,
        autoWidth: false,
        language: { url: "/jsalon/json/datatables_ru.json" },
        columnDefs: colDefs
    });

    hiddenForm.hide();
    $('#buttonset').buttonset();

    radios.click(function(){
        if ($(this).attr('id') == 'delRadio') {
            var row = datatable.$('tr.selected');
            fillForm(fields, row);
            hiddenForm.hide();
            if (!row.length) {
                alert('Выберите строку для удаления!');
            } else {
                $('#deleteConfirm').dialog({
                    resizable: false,
                    height: 180,
                    modal: true,
                    buttons: {
                        "Удалить": function(){
                            form.trigger('submit');
                            $(this).dialog('close');
                        },
                        Cancel: function(){
                            $(this).dialog('close');
                        }
                    }
                });
            }
        }
    });

    radios.change(function(){
        var id = $(this).attr('id');
        if (id == 'delRadio') return false;
        hiddenForm.show();
        actMessage.empty();
        if (id == 'newRadio') {
            form.trigger('reset');
            $('.create').css('display', 'inherit');
            $('.edit').css('display', 'none');
        } else if (id == 'editRadio') {
            fillForm(fields, datatable.$('tr.selected'));
            $('.create').css('display', 'none');
            $('.edit').css('display', 'inherit');
        }
    });

    form.on('submit', function(event){
        event.preventDefault();
        sendAjaxQuery();
    });

    table.find('tbody').on('click', 'tr', function(){
        rowSelectHandler(datatable, $(this));
        if (radios.filter(':checked').attr('id') == "editRadio") {
            fillForm(fields, $(this));
        }
    });

    function sendAjaxQuery() {
        var currRadioID = radios.filter(':checked').attr('id');
        $('input[name="radioID"]').val(currRadioID);
        actMessage.empty();
        actMessage.removeClass('err norm warn');
        $.ajax({
            url: ajaxUrl,
            type: "POST",
            data: form.serialize(),
            success: function(data){
                if (data.hasOwnProperty('err')) {
                    data.err.forEach(function(item){
                        actMessage.append(item + '<br/>');
                    });
                    actMessage.addClass('err');
                }
                if (data.hasOwnProperty('message') && data.hasOwnProperty('code')) {
                    actMessage.html(data.message);
                    switch (data.code) {
                        case -1: actMessage.addClass('err'); break;
                        case 0:
                            actMessage.addClass('norm');
                            var rowData = [];
                            fields.forEach(function(item, i){
                                rowData[i] = item.val();
                                if (i==0) rowData[i] = data.id;
                            });
                            if (currRadioID == 'newRadio') {
                                addTableRow(datatable, rowData);
                            } else if (currRadioID == 'editRadio') {
                                updateTableRow(datatable, rowData);
                            } else if (currRadioID == 'delRadio') {
                                deleteTableRow(datatable);
                            }
                            break;
                        case 1: actMessage.addClass('warn'); break;
                    }
                }
            },
            error: function(jqXHR, status, thrown){
                actMessage.html('' + status + ': ' + thrown);
                actMessage.addClass('err');
            }
        });
    }

    function fillForm(fields, row) {
        fields.forEach(function(item, i){
            item.val(row.children('td:eq('+i+')').text());
        });
    }

    function rowSelectHandler(datatable, row) {
        if (row.hasClass('selected')) {
            row.removeClass('selected');
        } else {
            datatable.$('tr.selected').removeClass('selected');
            row.addClass('selected');
        }
    }
    function addTableRow(datatable, data) {
        datatable.row.add(data).draw(false);
    }
    function updateTableRow(datatable, data) {
        datatable.row('.selected').data(data).draw(false);
    }
    function deleteTableRow(datatable) {
        datatable.row('.selected').remove().draw(false);
    }

    function getDiscountFields() {
        return [ $('#discountID'), $('#discountName'), $('#discountValue') ];
    }
    function getPostFields() {
        return [ $('#postID'), $('#postName') ];
    }
    function getServiceFields() {
        return [ $('#serviceID'), $('#serviceName'), $('#serviceCost'), $('#serviceDuration'), $('#serviceDescription') ];
    }
    function getUserFields() {
        return [ $('#userID'), $('#userLogin'), $('#userLastname'), $('#userFirstname'), $('#userMiddlename'), $('#userRole') ];
    }
});