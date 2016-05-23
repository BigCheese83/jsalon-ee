<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-calendar-check-o"></i> Запись</legend>
    <form>
        <div id="actionMessage" style="margin-bottom:10px;font-size:larger;text-align:center;"></div>
        <div class="center-block ui-widget border-gray">
        <div class="form-field">
            <label for="client-ac"><i class="fa fa-male fa-lg"></i>Клиент</label>
            <input type="text" id="client-ac" name="client" class="autocomplete" required>
            <i></i><input type="hidden" name="client-id">
        </div>
        <div class="form-field">
            <label for="master-ac"><i class="fa fa-user fa-lg"></i>Мастер</label>
            <input type="text" id="master-ac" name="master" class="autocomplete" required>
            <i></i><input type="hidden" name="master-id">
        </div>
        <div class="form-field">
            <label for="dateAppoint"><i class="fa fa-calendar fa-lg"></i>Дата</label>
            <input id="dateAppoint" name="dateAppoint" required>
        </div>
        <div class="form-field">
            <label for="timeAppoint"><i class="fa fa-clock-o fa-lg"></i>Время</label>
            <input id="timeAppoint" name="timeAppoint" style="width:278px" value="09:00" required>
        </div>
        <div class="form-field">
            <label for="service-ac"><i class="fa fa-scissors fa-lg"></i>Услуга</label>
            <input type="text" id="service-ac" name="service" class="autocomplete" required>
            <i></i><input type="hidden" name="service-id">
        </div>
        <div class="form-field">
            <label for="note"><i class="fa fa-edit fa-lg"></i>Примечание</label>
            <textarea id="note" name="note" rows="3"></textarea>
        </div>
        <input id="send-btn" type="submit" value="Записать">
        </div>
    </form>
    </fieldset>
</div><!-- End div content -->