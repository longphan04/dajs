function register() {
    document.querySelector("#registerForm").submit();
}
function signin(){
    document.querySelector("#registerForm").setAttribute("action", "/login");
    document.querySelector("#registerForm").setAttribute("method", "get");
    document.querySelector("#registerForm").submit();
}