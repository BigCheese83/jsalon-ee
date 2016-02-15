$(function(){
    var currPage = getLastPathFromHref(window.location.pathname, true),
        ajaxUrl = "",
        colDef = [];

    switch (currPage) {
        case 'admin':   //Start page for admin console
        case 'users':
            ajaxUrl = "/jsalon/admin/users/ajax";
            colDef = [
                { targets: [0,1,2,3,4], className: "dt-col-center"},
                { targets: [0], data: "login" },
                { targets: [1], data: "lastName" },
                { targets: [2], data: "firstName" },
                { targets: [3], data: "middleName" },
                { targets: [4], data: "role",  searchable: false} ];
            break;
        case 'masters':
            ajaxUrl = "/jsalon/admin/masters/ajax";
            colDef = [
                { targets: [0], className: "details-control", orderable: false, searchable: false, data: null, defaultContent: "" },
                { targets: [1,2,3,4,5,6,7], className: "dt-col-center" },
                { targets: [1], data: "surname" },
                { targets: [2], data: "name" },
                { targets: [3], data: "patronymic" },
                { targets: [4], data: "birthDate", searchable: false },
                { targets: [5], data: "hiringDate", searchable: false },
                { targets: [6], data: "post", searchable: false },
                { targets: [7], data: "busy", searchable: false, render: renderCheckbox } ];
            break;
        case '/jsalon/':    //Start page
        case 'clients':
            ajaxUrl = "/jsalon/user/clients/ajax";
            colDef = [
                { targets: [0], className: "details-control", orderable: false, searchable: false, data: null, defaultContent: "" },
                { targets: [1,2,3,4,5,6,7], className: "dt-col-center" },
                { targets: [1], data: "surname" },
                { targets: [2], data: "name" },
                { targets: [3], data: "patronymic" },
                { targets: [4], data: "birthDate", searchable: false },
                { targets: [5], data: "registrationDate", searchable: false },
                { targets: [6], data: "discount", searchable: false },
                { targets: [7], data: "inBlack", searchable: false, render: renderCheckbox } ];
            break;
    }

    var $datatable = $('#datatable').DataTable({
        processing: true,
        autoWidth: false,
        serverSide: true,
        ajax: { url: ajaxUrl, type: "POST" },
        language: { url: "/jsalon/json/datatables_ru.json" },
        columnDefs: colDef
    });

    $datatable.on('click', 'td.details-control', function(){
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
});