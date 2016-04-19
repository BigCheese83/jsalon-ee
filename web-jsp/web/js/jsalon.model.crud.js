$(function(){
    var $form = $('form'),
        $submit = $form.find('input[type="submit"]'),
        $table = $('#datatable'),
        $hiddenForm = $('#hiddenForm'),
        $actMessage = $('#actionMessage'),
        $search = $('#search'),
        $searchMessage = $('#searchMessage'),
        $searchButton = $('#searchButton'),
        $contentTable = $('.content-table'),
        $buttonset = $('#buttonset'),
        $dataset = $('#dataset'),
        $editButton = $('#editRadio'),
        $delButton = $('#delRadio'),
        $delDialog = $('#deleteConfirm'),
        $Identity = $('#Identity'),
        $radioID = $('#radioID'),
        $radios = $('input:radio[name="radio"]'),
        $formInputs = $form.find('input[type="text"],input[type="tel"],input[type="email"],input.objectID,select'),
        $formChecks = $form.find('input[type="checkbox"]');
    var currPage = getLastPathFromHref(window.location.pathname, true),
        ajaxUrl = getAjaxUrlCrud(currPage),
        crudWithSearch = $('table').is('#search'),
        selRowIndex,
        colDefs = [],
        dataSrc = {};

    if ($dataset.length) {
        dataSrc = $.parseJSON($dataset.val());
    }

    switch (currPage) {
        case 'discounts':
            colDefs = [
                { targets: [0,1,2], className: "dt-col-center" },
                { targets: [0], data: "id", width: "15px", searchable: false },
                { targets: [1], data: "name" },
                { targets: [2], data: "value" } ];
            break;
        case 'posts':
            colDefs = [
                { targets: [0,1], className: "dt-col-center" },
                { targets: [0], data: "id", width: "15px", searchable: false },
                { targets: [1], data: "name" } ];
            break;
        case 'services':
            colDefs = [
                { targets: [0,1,2,3,4], className: "dt-col-center" },
                { targets: [0], data: "id", width: "15px", searchable: false },
                { targets: [1], data: "name" },
                { targets: [2], data: "cost" },
                { targets: [3], data: "duration" },
                { targets: [4], data: "description" } ];
            break;
        case 'postservice':
            colDefs = [
                { targets: [0,1,2], className: "dt-col-center" },
                { targets: [0], data: "id", width: "15px", searchable: false },
                { targets: [1], data: "postName" },
                { targets: [2], data: "serviceName" } ];
            break;
        case 'user':
            colDefs = [
                { targets: [0,1,2,3,4], className: "dt-col-center" },
                { targets: [0], data: "login" },
                { targets: [1], data: "lastName" },
                { targets: [2], data: "firstName" },
                { targets: [3], data: "middleName" },
                { targets: [4], data: "role"} ];
            break;
        case 'master':
            colDefs = [
                { targets: [1,2,3,4,5,6,7], className: "dt-col-center" },
                { targets: [0], className: "details-control", orderable: false, searchable: false, data: null, defaultContent: "" },
                { targets: [1], data: "surname" },
                { targets: [2], data: "name" },
                { targets: [3], data: "patronymic" },
                { targets: [4], data: "birthDate", searchable: false },
                { targets: [5], data: "hiringDate", searchable: false },
                { targets: [6], data: "post.id", render: function(data, type, row){return row.post.name;} },
                { targets: [7], data: "busy", searchable: false, render: renderCheckbox } ];
            break;
        case 'client':
            colDefs = [
                { targets: [1,2,3,4,5,6,7], className: "dt-col-center" },
                { targets: [0], className: "details-control", orderable: false, searchable: false, data: null, defaultContent: "" },
                { targets: [1], data: "surname" },
                { targets: [2], data: "name" },
                { targets: [3], data: "patronymic" },
                { targets: [4], data: "birthDate", searchable: false },
                { targets: [5], data: "registrationDate", searchable: false },
                { targets: [6], data: "discount.id",
                    render: function(data, type, row){
                        return row.discount ? row.discount.name : null;
                    } },
                { targets: [7], data: "inBlackList", searchable: false, render: renderCheckbox } ];
            break;
    }

    var $datatable = $table.DataTable({
        processing: true,
        autoWidth: false,
        data: dataSrc,
        language: { url: "/jsalon/json/datatables_ru.json" },
        columnDefs: colDefs
    });

    $hiddenForm.hide();
    $buttonset.buttonset();
    if (crudWithSearch) {
        hideElements();
    }

    $delButton.on('click', function(){
        fillForm();
        $hiddenForm.hide();
        if ($datatable.$('tr.selected').length) {
            $delDialog.dialog({
                resizable: false, modal: true, height: 180,
                buttons: {
                    "Удалить": function(){
                        $form.trigger('submit');
                        $(this).dialog('close');
                    },
                    "Cancel": function(){
                        $(this).dialog('close');
                    }
                }
            });
        } else {
            alert('Выберите строку для удаления!');
        }
    });

    $radios.on('change', function(){
        var id = $(this).attr('id');
        if (id == 'delRadio') return false;
        $hiddenForm.show();
        $actMessage.empty();
        if (id == 'newRadio') {
            $form.trigger('reset');
            $('.create').css('display', 'inherit');
            $('.edit').css('display', 'none');
        } else if (id == 'editRadio') {
            fillForm();
            $('.create').css('display', 'none');
            $('.edit').css('display', 'inherit');
        }
    });

    $table.find('tbody').on('click', 'tr', function(){
        if ($(this).parents('table').hasClass('nested-table') ||
            $(this).find('table').hasClass('nested-table')) {
            return false;
        }
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $datatable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            if ($radios.filter(':checked').attr('id') == "editRadio") {
                fillForm();
            }
        }
    });

    $table.find('tbody').on('click', 'td.details-control', function(){
        var tr = $(this).closest('tr');
        var row = $datatable.row(tr);
        if (row.child.isShown()) {
            row.child.hide();
            tr.removeClass('shown');
        } else {
            row.child(getDetailInfo(ajaxUrl, row.data())).show();
            tr.addClass('shown');
        }
    });

    $form.on('submit', function(event){
        event.preventDefault();
        var currRadioID = $radios.filter(':checked').attr('id');
        var rowIndex = selRowIndex;
        $radioID.val(currRadioID);
        $actMessage.empty();
        $actMessage.removeClass('err norm warn');
        $submit.prop('disabled', true);
        $.ajax({
            url: ajaxUrl,
            type: "POST",
            data: $form.serialize(),
            success: function(data) {
                if (!data) return;
                if (data.hasOwnProperty('err')) {
                    data.err.forEach(function(item){
                        $actMessage.append(item + '<br/>');
                    });
                    $actMessage.addClass('err');
                }
                if (data.hasOwnProperty('message') && data.hasOwnProperty('code')) {
                    $actMessage.html(data.message);
                    switch (data.code) {
                        case -1: $actMessage.addClass('err'); break;
                        case 0:
                            $actMessage.addClass('norm');
                            if (currRadioID == 'newRadio') {
                                if (!crudWithSearch) {
                                    $datatable.row.add(data.result).draw(false);
                                }
                            } else if (currRadioID == 'editRadio') {
                                var row = $datatable.row(rowIndex);
                                if (row.child !== undefined && row.child.isShown()) {
                                    row.child.hide();
                                    $(row.node()).removeClass('shown');
                                }
                                row.data(data.result).draw(false);
                            } else if (currRadioID == 'delRadio') {
                                $datatable.row(rowIndex).remove().draw(false);
                            }
                            break;
                        case 1: $actMessage.addClass('warn'); break;
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

    $searchButton.button().on('click', function(){
        var postParams = "";
        var isAllEmpty = true;
        hideElements();
        $searchMessage.empty();

        $search.find('input[type="text"]').each(function(i, elem){
            postParams = postParams + $(elem).serialize() + "&";
            if($(elem).val() != "") {
                isAllEmpty = false;
            }
        });
        if (isAllEmpty) {
            alert('Введите минимум один параметр для поиска!');
            return false;
        }
        postParams = postParams + "searchRequest=true";
        $searchButton.button("option", "disabled", true);

        $.ajax({
            url: ajaxUrl,
            type: "POST",
            data: postParams,
            success: function(data){
                if (data.hasOwnProperty('err')) {
                    data.err.forEach(function(item){
                        $searchMessage.append(item + '<br/>');
                    });
                }
                if (data.length) {
                    showElements();
                    $datatable.clear();
                    $datatable.rows.add(data).draw();
                } else {
                    $searchMessage.append('Ничего не найдено :(' + '<br/>');
                }
            },
            error: function(jqXHR, status, thrown){
                $searchMessage.html('' + status + ': ' + thrown);
            },
            complete: function(jqXHR, status) {
                $searchButton.button("option", "disabled", false);
            }
        });
    });

    function fillForm() {
        selRowIndex = $datatable.row('.selected').index();
        var rowData = $datatable.row('.selected').data();
        if (rowData) {
            $formInputs.each(function(){
                $(this).val(getPropertyOfObject(rowData, $(this).attr('name')));
            });
            $formChecks.each(function(){
                $(this).prop('checked', getPropertyOfObject(rowData, $(this).attr('name')));
            });
        }
        $Identity.prop('checked', false);
    }

    function hideElements() {
        $contentTable.hide();
        $editButton.button("option", "disabled", true);
        $delButton.button("option", "disabled", true);
    }
    function showElements() {
        $contentTable.show();
        $editButton.button("option", "disabled", false);
        $delButton.button("option", "disabled", false);
    }
});