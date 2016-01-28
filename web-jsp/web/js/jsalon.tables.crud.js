$(document).ready(function() {
    var table = $('#datatable');
    var form = $('form');
    var hiddenForm = $('#hiddenForm');
    var actMessage = $('#actionMessage');
    var radios = $('input:radio[name="radio"]');
    var colDefs = [];
    var fields = [];
    var ajaxUrl = getAjaxUrlCrud();
    var mappingData = function(){};
    var mappingRow = function(){};

    switch (getLastPathFromHref(window.location.pathname, true)) {
        case 'discounts':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1,2], className: "dt-col-center" } ];
            fields = [$('#discountID'), $('#discountName'), $('#discountValue')];
            break;
        case 'posts':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1], className: "dt-col-center" } ];
            fields = [$('#postID'), $('#postName')];
            break;
        case 'services':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1,2,3,4], className: "dt-col-center" } ];
            fields = [$('#serviceID'), $('#serviceName'), $('#serviceCost'), $('#serviceDuration'), $('#serviceDescription')];
            break;
        case 'user':
            colDefs = [
                { targets: [0], width: "15px", searchable: false },
                { targets: [0,1,2,3,4,5], className: "dt-col-center" } ];
            fields = [$('#userID'), $('#userLogin'), $('#userLastname'), $('#userFirstname'), $('#userMiddlename'), $('#userRole')];
            break;
        case 'master':
            colDefs = [
                { targets: [0], className: "details-control", orderable: false, searchable: false, data: null, defaultContent: "" },
                { targets: [1,2,3,4,5,6,7,8], className: "dt-col-center" },
                { targets: [1], data: "id", width: "15px", searchable: false  },
                { targets: [2], data: "surname" },
                { targets: [3], data: "name" },
                { targets: [4], data: "patronymic" },
                { targets: [5], data: "birthDate" },
                { targets: [6], data: "hiringDate" },
                { targets: [7], data: "postInfo" },
                { targets: [8], data: "busy", render: function(data){
                    if (data === undefined || data === null) return '';
                    if (data === true) return '<i class="fa fa-lg fa-check-square-o"></i>';
                    else return '<i class="fa fa-lg fa-square-o"></i>';
                }} ];
            mappingRow = function(){
                return {
                    surname: $('#Lastname').val(), name: $('#Firstname').val(), patronymic: $('#Middlename').val(),
                    birthDate: $('#Birthdate').val(), hiringDate: $('#HiringDate').val(),
                    postInfo: $('#Post').find('option:selected').text(), busy: $('#Busy').prop('checked')
                };
            };
            mappingData = function(data){
                $('#masterID').val(data.id);
                $('#Lastname').val(data.surname);
                $('#Firstname').val(data.name);
                $('#Middlename').val(data.patronymic);
                $('#Birthdate').val(data.birthDate);
                $('#HiringDate').val(data.hiringDate);
                $('#Post').find(':contains("'+data.postInfo+'")').attr('selected', 'selected');
                $('#Busy').prop('checked', data.busy);
                $('#passportID').val(data.passportId);
                $('#Series').val(data.passportSeries);
                $('#Number').val(data.passportNumber);
                $('#IssuedBy').val(data.passportIssuedBy);
                $('#IssueDate').val(data.passportIssueDate);
                $('#Subdivision').val(data.passportSubdivision);
                $('#IssueCountry').find(':contains("'+data.passportCountry+'")').attr('selected', 'selected');
                $('#regAddressID').val(data.regId);
                $('#RegCountry').find(':contains("'+data.regCountry+'")').attr('selected', 'selected');
                $('#RegZip').val(data.regZip);
                $('#RegDistrict').val(data.regDistrict);
                $('#RegCity').val(data.regCity);
                $('#RegStreet').val(data.regStreet);
                $('#RegHouse').val(data.regHouse);
                $('#RegSection').val(data.regSection);
                $('#RegFlat').val(data.regFlat);
                $('#liveAddressID').val(data.liveId);
                $('#LiveCountry').find(':contains("'+data.liveCountry+'")').attr('selected', 'selected');
                $('#LiveZip').val(data.liveZip);
                $('#LiveDistrict').val(data.liveDistrict);
                $('#LiveCity').val(data.liveCity);
                $('#LiveStreet').val(data.liveStreet);
                $('#LiveHouse').val(data.liveHouse);
                $('#LiveSection').val(data.liveSection);
                $('#LiveFlat').val(data.liveFlat);
                $('#contactID').val(data.contactId);
                $('#Phone').val(data.contactPhone);
                $('#Email').val(data.contactEmail);
                $('#ICQ').val(data.contactIcq);
                $('#VK').val(data.contactVk);
                $('#Skype').val(data.contactSkype);
                $('#Facebook').val(data.contactFacebook);
                $('#Twitter').val(data.contactTwitter);
            };
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
            fillForm(row);
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
            fillForm(datatable.$('tr.selected'));
            $('.create').css('display', 'none');
            $('.edit').css('display', 'inherit');
        }
    });

    form.on('submit', function(event){
        event.preventDefault();
        sendAjaxQuery();
    });

    table.find('tbody').on('click', 'tr', function(){
        if ($(this).parents('table').hasClass('nested-table') ||
            $(this).find('table').hasClass('nested-table')) return;
        rowSelectHandler(datatable, $(this));
        if (radios.filter(':checked').attr('id') == "editRadio") {
            fillForm($(this));
        }
    });

    table.find('tbody').on('click', 'td.details-control', function (){
        var tr = $(this).closest('tr');
        var row = datatable.row(tr);
        if (row.child.isShown()) {
            row.child.hide();
            tr.removeClass('shown');
        } else {
            row.child(getDetailInfo(ajaxUrl, row.data())).show();
            tr.addClass('shown');
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
                            var rowData = getRowData(datatable.$('tr.selected'), data.id);
                            if (currRadioID == 'newRadio') {
                                if (!$('table').is('#search')) {
                                    datatable.row.add(rowData).draw(false);
                                }
                            } else if (currRadioID == 'editRadio') {
                                datatable.row('.selected').data(rowData).draw(false);
                            } else if (currRadioID == 'delRadio') {
                                datatable.row('.selected').remove().draw(false);
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

    function fillForm(row) {
        if (row.children('td').hasClass('details-control')) {
            mappingData(datatable.row(row).data());
        } else {
            fields.forEach(function(item, i){
                item.val(row.children('td:eq('+i+')').text());
            });
        }
    }

    function getRowData(row, id) {
        var rowData;
        if (row.children('td').hasClass('details-control')) {
            rowData = mappingRow();
            rowData.id = id;
        } else {
            rowData = [];
            fields.forEach(function(item, i){
                rowData[i] = item.val();
                if (i==0) rowData[i] = id;
            });
        }
        return rowData;
    }

    function rowSelectHandler(datatable, row) {
        if (row.hasClass('selected')) {
            row.removeClass('selected');
        } else {
            datatable.$('tr.selected').removeClass('selected');
            row.addClass('selected');
        }
    }
});