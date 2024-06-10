function submitForm() {
    document.querySelector("#date").value = document.querySelector("#datepicker").value.replace(/-/g, "/");
    document.querySelector("#withdrawForm").submit();
}
function selectCategory(e, categoryId) {
    document.querySelector("#categoryId").value = categoryId;
    document.querySelectorAll(".category-line>input").forEach(input => input.className = "");
    e.className = "active";
}