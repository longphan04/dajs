function changeTab(tabName) {
    const tabcontent = document.getElementsByClassName("tabcontent");
    for (let i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    const tablinks = document.querySelectorAll(".tablinks[onclick^='changeTab']");
    for (let i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.querySelector(`#${tabName}Tab`).style.display = "block";
    document.querySelector(`#${tabName}Btn`).className += " active";
}


function toggleType(event, type) {
    const tabcontent = document.getElementsByClassName("tabcontent child");
    for (let i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    const tablinks = document.querySelectorAll(".tablinks[onclick^='toggleType']");
    for (let i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(type).style.display = "block";
    event.currentTarget.className += " active";
}

function submitMonthlyForm() {
    let date = document.querySelector("#monthlyCalendar").value;
    document.querySelector("#monthlyYear").value = date.split("-")[0];
    document.querySelector("#monthlyMonth").value = date.split("-")[1];
    document.querySelector("#monthlyForm").submit();
}

function submitAnnualForm() {
    document.querySelector("#annualDate").value = document.querySelector("#annualCalendar").value;
    document.querySelector("#annualForm").submit();
}

$(document).ready(function () {
    $('#annualCalendar').yearpicker();
});






